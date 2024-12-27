package ee.qrental.transaction.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.transaction.api.in.request.rent.RentCalculationAddRequest;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.api.out.rent.RentCalculationLoadPort;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentCalculationAddRequestValidator
    implements AddRequestValidator<RentCalculationAddRequest> {

  private final RentCalculationLoadPort loadPort;
  private final BalanceLoadPort balanceLoadPort;
  private final GetQWeekQuery qWeekQuery;

  public ViolationsCollector validate(final RentCalculationAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var qWeek = qWeekQuery.getById(request.getQWeekId());
    // checkIfCalculationDayIsMonday(violationsCollector);
    checkIfWeeklyRentAlreadyCalculated(qWeek, violationsCollector);
    checkIfBalanceAlreadyCalculatedForRequestedWeek(qWeek, violationsCollector);
    // checkIfPreviousWeekHasCalculatedRent(qWeek, violationsCollector);
    return violationsCollector;
  }

  private void checkIfPreviousWeekHasCalculatedRent(
      final QWeekResponse qWeek, final ViolationsCollector violationsCollector) {
    final var previousWeek = qWeekQuery.getOneBeforeById(qWeek.getId());
    if (previousWeek == null) {
      System.out.println("First Rent Calculation");
      return;
    }
    final var latestCalculatedQWeekId = loadPort.loadLastCalculationQWeekId();
    if (previousWeek.getId() != latestCalculatedQWeekId) {
      final var violation =
          format(
              "Rent Calculation for previous week - %d was not calculated.",
              previousWeek.getNumber());
      System.out.println(violation);
      violationsCollector.collect(violation);
    }
  }

  private void checkIfBalanceAlreadyCalculatedForRequestedWeek(
      final QWeekResponse qWeek, final ViolationsCollector violationsCollector) {
    final var latestBalance = balanceLoadPort.loadLatest();
    if (latestBalance == null) {
      return;
    }

    final var qWeekYear = qWeek.getYear();
    final var qWeekNumber = qWeek.getNumber();
    final var latestBalanceQWeek = qWeekQuery.getById(latestBalance.getQWeekId());
    final var latestBalanceQWeekYear = latestBalanceQWeek.getYear();
    final var latestBalanceQWeekNumber = latestBalanceQWeek.getNumber();

    if (qWeekYear <= latestBalanceQWeekYear && qWeekNumber <= latestBalanceQWeekNumber) {
      final var violation =
          "Weekly Rent Calculation must be done only for the week without calculated Balance";
      System.out.println(violation);
      violationsCollector.collect(violation);
    }
  }

  private void checkIfCalculationDayIsMonday(final ViolationsCollector violationsCollector) {
    final var dayOfWeek = LocalDate.now().getDayOfWeek();
    if (dayOfWeek != DayOfWeek.MONDAY) {
      final var violation = "Weekly Rent Calculation must be started on Monday only";
      System.out.println(violation);
      violationsCollector.collect(violation);
    }
  }

  private void checkIfWeeklyRentAlreadyCalculated(
      final QWeekResponse qWeek, final ViolationsCollector violationsCollector) {
    final var year = qWeek.getYear();
    final var weekNumber = qWeek.getNumber();
    final var lastCalculationQWeekId = loadPort.loadLastCalculationQWeekId();
    if (lastCalculationQWeekId == null) {
      return;
    }
    final var lastCalculatedWeek = qWeekQuery.getById(lastCalculationQWeekId);

    if (lastCalculatedWeek.getYear() == year && lastCalculatedWeek.getNumber() == weekNumber) {
      final var violation =
          format("Calculation for the year %d and week %d already done.", year, weekNumber);
      System.out.println("Execution time: " + LocalTime.now() + " " + violation);
      violationsCollector.collect(violation);
    }
  }
}
