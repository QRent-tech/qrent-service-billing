package ee.qrental.transaction.core.service.rent;

import static java.util.stream.Collectors.toList;

import ee.qrental.car.api.in.query.GetCarLinkQuery;
import ee.qrental.car.api.in.query.GetCarQuery;
import ee.qrental.car.api.in.response.CarLinkResponse;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.contract.api.in.query.GetAbsenceQuery;
import ee.qrental.email.api.in.request.EmailSendRequest;
import ee.qrental.email.api.in.request.EmailType;
import ee.qrental.email.api.in.usecase.EmailSendUseCase;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.request.rent.RentCalculationAddRequest;
import ee.qrental.transaction.api.in.usecase.rent.RentCalculationAddUseCase;
import ee.qrental.transaction.api.out.rent.RentCalculationAddPort;
import ee.qrental.transaction.core.mapper.rent.RentCalculationAddRequestMapper;
import ee.qrental.transaction.core.service.TransactionUseCaseService;
import ee.qrental.transaction.core.validator.RentCalculationAddBusinessRuleValidator;
import ee.qrental.transaction.domain.rent.RentCalculation;
import ee.qrental.transaction.domain.rent.RentCalculationResult;
import ee.qrental.user.api.in.query.GetUserAccountQuery;
import ee.qrental.user.api.in.response.UserAccountResponse;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;

// TODO add Unit Test
@AllArgsConstructor
public class RentCalculationService implements RentCalculationAddUseCase {

  private static final Integer ABSENCE_DAYS_COUNT_THRESHOLD_PER_WEEK = 2;

  private final RentTransactionGenerator rentTransactionGenerator;
  private final GetCarLinkQuery carLinkQuery;
  private final GetCarQuery carQuery;
  private final GetTransactionQuery transactionQuery;
  private final TransactionUseCaseService transactionUseCaseService;
  private final RentCalculationAddPort rentCalculationAddPort;
  private final RentCalculationAddRequestMapper addRequestMapper;
  private final RentCalculationAddBusinessRuleValidator addBusinessRuleValidator;
  private final EmailSendUseCase emailSendUseCase;
  private final GetUserAccountQuery userAccountQuery;
  private final GetQWeekQuery qWeekQuery;
  private final GetAbsenceQuery absenceQuery;

  @Transactional
  @Override
  public Long add(final RentCalculationAddRequest addRequest) {
    final var calculationStartTime = System.currentTimeMillis();
    final var violationsCollector = addBusinessRuleValidator.validate(addRequest);
    if (violationsCollector.hasViolations()) {
      addRequest.setViolations(violationsCollector.getViolations());

      return null;
    }
    final var domain = addRequestMapper.toDomain(addRequest);
    final var qWeek = qWeekQuery.getById(addRequest.getQWeekId());
    final var activeCarLinks = carLinkQuery.getActive();
    for (final var activeCarLink : activeCarLinks) {
      processRent(domain, activeCarLink, qWeek);
      processNoLabelFine(domain, activeCarLink, qWeek);
      processAbsenceAdjustment(domain, activeCarLink, qWeek);
    }
    final var savedCalculation = rentCalculationAddPort.add(domain);
    sendEmails(domain.getResults(), qWeek.getNumber());
    final var calculationEndTime = System.currentTimeMillis();
    final var calculationDuration = calculationEndTime - calculationStartTime;
    System.out.printf("----> Time: Rent Calculation took %d milli seconds \n", calculationDuration);

    return savedCalculation.getId();
  }

  private void processRent(
      final RentCalculation domain, final CarLinkResponse activeCarLink, QWeekResponse qWeek) {
    final var rentTransactionAddRequest =
        rentTransactionGenerator.getRentTransactionAddRequest(qWeek, activeCarLink);
    final var carLinkId = activeCarLink.getId();
    final var rentTransactionId = transactionUseCaseService.add(rentTransactionAddRequest);
    final var calculationResultForRent = getResult(carLinkId, rentTransactionId);
    domain.getResults().add(calculationResultForRent);
  }

  private void processAbsenceAdjustment(
      final RentCalculation domain, final CarLinkResponse activeCarLink, QWeekResponse qWeek) {
    final var driverId = activeCarLink.getDriverId();
    final var carLinkId = activeCarLink.getId();
    final var absencesDaysCount =
        absenceQuery.getAbsencesDayCountsByDriverIdAndQWeekId(driverId, qWeek.getId());
    if (absencesDaysCount < ABSENCE_DAYS_COUNT_THRESHOLD_PER_WEEK) {
      return;
    }
    final var absenceAdjustmentTransactionAddRequest =
        rentTransactionGenerator.getAbsenceAdjustmentTransactionAddRequest(
            activeCarLink, qWeek, absencesDaysCount);
    final var absenceAdjustmentTransactionId =
        transactionUseCaseService.add(absenceAdjustmentTransactionAddRequest);
    final var calculationResultForAbsenceAdjustment =
        getResult(carLinkId, absenceAdjustmentTransactionId);
    domain.getResults().add(calculationResultForAbsenceAdjustment);
  }

  private void processNoLabelFine(
      final RentCalculation domain,
      final CarLinkResponse activeCarLink,
      final QWeekResponse qWeek) {
    final var isNoLabelFineRequired = isNoLabelFineRequired(activeCarLink.getCarId());
    if (isNoLabelFineRequired) {
      final var noLabelFineTransactionAddRequest =
          rentTransactionGenerator.getNoLabelFineTransactionAddRequest(qWeek, activeCarLink);
      final var noLabelFineTransactionId =
          transactionUseCaseService.add(noLabelFineTransactionAddRequest);
      final var calculationResultForNoLabelFine =
          getResult(activeCarLink.getId(), noLabelFineTransactionId);
      domain.getResults().add(calculationResultForNoLabelFine);
    }
  }

  private boolean isNoLabelFineRequired(final Long carId) {
    final var car = carQuery.getById(carId);
    final var hasQLabel = car.getBrandingQrent();

    return !hasQLabel;
  }

  private RentCalculationResult getResult(final Long carLinkId, final Long transactionId) {

    return RentCalculationResult.builder()
        .carLinkId(carLinkId)
        .transactionId(transactionId)
        .build();
  }

  private void sendEmails(final List<RentCalculationResult> results, final Integer weekNumber) {
    final var operators = userAccountQuery.getAllOperators();
    final var recipients = operators.stream().map(UserAccountResponse::getEmail).collect(toList());
    final var emailProperties = new HashMap<String, Object>();
    emailProperties.put("calculationType", "Weekly Rent");
    emailProperties.put("calculationDate", LocalDate.now());
    emailProperties.put("weekNumber", weekNumber);
    emailProperties.put(
        "transactions",
        transactionQuery.getAllByIds(
            results.stream().map(RentCalculationResult::getTransactionId).collect(toList())));

    final var emailSendRequest =
        EmailSendRequest.builder()
            .type(EmailType.RENT_CALCULATION)
            .recipients(recipients)
            .properties(emailProperties)
            .build();

    emailSendUseCase.sendEmail(emailSendRequest);
  }
}
