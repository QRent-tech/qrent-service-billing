package ee.qrental.transaction.core.service.rent;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.car.api.in.query.GetCarQuery;
import ee.qrental.car.api.in.response.CarLinkResponse;
import ee.qrental.car.api.in.response.CarResponse;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.out.type.TransactionTypeLoadPort;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

import static ee.qrental.transaction.api.in.utils.TransactionTypeConstant.*;
import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;

@AllArgsConstructor
public class RentTransactionGenerator {

  private static final Integer ABSENCE_DAYS_COUNT_THRESHOLD_PER_WEEK = 2;
  private static final Integer NEW_CAR_AGE = 3;
  private static final BigDecimal OLD_CAR_RATE = valueOf(150L);
  private static final BigDecimal NEW_CAR_RATE = valueOf(240L);
  private static final BigDecimal RATE_DECREASE_STEP = valueOf(10L);
  private static final BigDecimal NO_LABEL_RATE = valueOf(20L);
  private static final BigDecimal DEFAULT_WEEKLY_WORKING_DAYS_COUNT = valueOf(6L);

  private final TransactionTypeLoadPort transactionTypeLoadPort;
  private final GetCarQuery carQuery;
  private final QDateTime qDateTime;

  public TransactionAddRequest getRentTransactionAddRequest(
      final QWeekResponse week, final CarLinkResponse carLink) {
    final var addRequest = new TransactionAddRequest();
    addRequest.setDate(week.getStart());
    final var transactionTpe =
        transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT);
    if (transactionTpe == null) {
      throw new RuntimeException(
          "Transaction type for weekly Rent Calculation is missing. Create a Transaction Type with name: "
              + TRANSACTION_TYPE_NAME_WEEKLY_RENT);
    }
    addRequest.setTransactionTypeId(transactionTpe.getId());
    addRequest.setWeekNumber(week.getNumber());
    addRequest.setDriverId(carLink.getDriverId());
    addRequest.setAmount(calculateRentTransactionAmount(carLink));
    addRequest.setComment(
        format(
            "Automatically crated 'Rent' Transaction for active Car Link %d. Week %d",
            carLink.getId(), week.getNumber()));

    return addRequest;
  }

  Optional<TransactionAddRequest> getNoLabelFineTransactionAddRequest(
      final QWeekResponse week, final CarLinkResponse activeCarLink) {
    if (isNoLabelFineRequired(activeCarLink.getCarId())) {
      final var transactionTpe = transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NO_LABEL_FINE);
      if (transactionTpe == null) {
        throw new RuntimeException(
            "Transaction type for No Label Fine is missing. Create a Transaction Type with name: "
                + TRANSACTION_TYPE_NO_LABEL_FINE);
      }
      final var addRequest = new TransactionAddRequest();
      addRequest.setDate(week.getStart());
      addRequest.setTransactionTypeId(transactionTpe.getId());
      addRequest.setWeekNumber(week.getNumber());
      addRequest.setDriverId(activeCarLink.getDriverId());
      addRequest.setAmount(NO_LABEL_RATE);
      addRequest.setComment(
          format(
              "Automatically crated 'No Label Fine' Transaction for active Car Link %d. Week %d",
              activeCarLink.getId(), week.getNumber()));
      return Optional.of(addRequest);
    }

    return Optional.empty();
  }

 private boolean isAbsenceAdjustmentRequired(final Long absenceDaysCount) {

    return absenceDaysCount >= ABSENCE_DAYS_COUNT_THRESHOLD_PER_WEEK;
  }

  private boolean isNoLabelFineRequired(final Long carId) {
    final var car = carQuery.getById(carId);
    final var hasQLabel = car.getBrandingQrent();

    return !hasQLabel;
  }

  public Optional<TransactionAddRequest> getAbsenceAdjustmentTransactionAddRequest(
      final CarLinkResponse activeCarLink, final QWeekResponse week, final Long absenceDaysCount) {
    if (isAbsenceAdjustmentRequired(absenceDaysCount)) {

      final var addRequest = new TransactionAddRequest();
      addRequest.setDate(week.getStart());
      final var transactionTpe =
          transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_ABSENCE_ADJUSTMENT);
      if (transactionTpe == null) {
        throw new RuntimeException(
            "Transaction type for Absence Adjustment is missing. Create a Transaction Type with name: "
                + TRANSACTION_TYPE_ABSENCE_ADJUSTMENT);
      }
      final var absenceAdjustmentAmount =
          calculateAbsenceAdjustmentTransactionAmount(activeCarLink, absenceDaysCount);
      addRequest.setTransactionTypeId(transactionTpe.getId());
      addRequest.setWeekNumber(week.getNumber());
      addRequest.setDriverId(activeCarLink.getDriverId());
      addRequest.setAmount(absenceAdjustmentAmount);
      addRequest.setComment(
          format(
              "Automatically crated 'Absence Rent Adjustment' Transaction for Absence days count: %d, active Car Link %d, Week %d ",
              absenceDaysCount, activeCarLink.getId(), week.getNumber()));

      return Optional.of(addRequest);
    }
    return Optional.empty();
  }

  private BigDecimal calculateAbsenceAdjustmentTransactionAmount(
      final CarLinkResponse carLink, final Long absenceDaysCount) {

    final var rentAmount = calculateRentTransactionAmount(carLink);

    return rentAmount.divide(DEFAULT_WEEKLY_WORKING_DAYS_COUNT).multiply(valueOf(absenceDaysCount));
  }

  private BigDecimal calculateRentTransactionAmount(final CarLinkResponse carLink) {
    final var carId = carLink.getCarId();
    final var car = carQuery.getById(carId);
    final int carAge = getCarAge(car);
    if (carAge < NEW_CAR_AGE) {

      return NEW_CAR_RATE;
    }

    if (carAge == 3) {

      return NEW_CAR_RATE.subtract(RATE_DECREASE_STEP);
    }

    if (carAge == 4) {

      return NEW_CAR_RATE.subtract(RATE_DECREASE_STEP).subtract(RATE_DECREASE_STEP);
    }

    if (carAge == 5) {

      return NEW_CAR_RATE
          .subtract(RATE_DECREASE_STEP)
          .subtract(RATE_DECREASE_STEP)
          .subtract(RATE_DECREASE_STEP);
    }

    if (carAge == 6) {

      return NEW_CAR_RATE
          .subtract(RATE_DECREASE_STEP)
          .subtract(RATE_DECREASE_STEP)
          .subtract(RATE_DECREASE_STEP)
          .subtract(RATE_DECREASE_STEP);
    }

    return OLD_CAR_RATE;
  }

  private Integer getCarAge(final CarResponse car) {
    final var carReleaseYear = car.getReleaseDate().getYear();
    final var currentYear = qDateTime.getToday().getYear();

    return currentYear - carReleaseYear;
  }
}
