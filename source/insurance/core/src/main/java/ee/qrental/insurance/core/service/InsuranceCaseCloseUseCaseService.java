package ee.qrental.insurance.core.service;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;
import static java.lang.String.format;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.insurance.api.in.query.GetQKaskoQuery;
import ee.qrental.insurance.api.in.request.*;
import ee.qrental.insurance.api.in.usecase.InsuranceCaseCloseUseCase;
import ee.qrental.insurance.api.out.*;
import ee.qrental.insurance.core.validator.InsuranceCaseCloseBusinessRuleValidator;
import ee.qrental.insurance.domain.InsuranceCase;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.query.type.GetTransactionTypeQuery;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.in.response.TransactionResponse;
import ee.qrental.transaction.api.in.usecase.TransactionAddUseCase;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@Transactional(SUPPORTS)
@AllArgsConstructor
public class InsuranceCaseCloseUseCaseService implements InsuranceCaseCloseUseCase {

  private final InsuranceCaseUpdatePort updatePort;
  private final InsuranceCaseLoadPort loadPort;
  private final InsuranceCaseCloseBusinessRuleValidator closeRuleValidator;
  private final GetQKaskoQuery getQKaskoQuery;
  private final GetQWeekQuery qWeekQuery;
  private final GetDriverQuery driverQuery;
  private final GetTransactionQuery transactionQuery;
  private final GetTransactionTypeQuery transactionTypeQuery;
  private final QDateTime qDateTime;
  private final TransactionAddUseCase transactionAddUseCase;

  public InsuranceCasePreCloseResponse getPreCloseResponse(final Long insuranceCaseId) {
    final var toCloseInsuranceCase = loadPort.loadById(insuranceCaseId);
    final var driverId = toCloseInsuranceCase.getDriverId();
    final var driverInfo = driverQuery.getObjectInfo(driverId);
    final var qWeekId = qWeekQuery.getCurrentWeek().getId();
    final var withQKasko = getQKaskoQuery.hasQKasko(driverId, qWeekId);
    final var amountToPayAdjustedByQKasko =
        getAmountToPayAdjustedByQKasko(toCloseInsuranceCase, withQKasko);
    final var paidAmount = getAmountPaid(toCloseInsuranceCase);
    final var paymentAmount = amountToPayAdjustedByQKasko.subtract(paidAmount);

    return InsuranceCasePreCloseResponse.builder()
        .insuranceCaseId(insuranceCaseId)
        .driverId(driverId)
        .driverInfo(driverInfo)
        .withQKasko(withQKasko)
        .paymentAmount(paymentAmount)
        .paidAmount(paidAmount)
        .originalAmount(toCloseInsuranceCase.getDamageAmount())
        .build();
  }

  @Override
  public void close(final InsuranceCaseCloseRequest request) {
    final var violationsCollector = closeRuleValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var insuranceCaseId = request.getId();
    final var preCloseResponse = getPreCloseResponse(insuranceCaseId);
    final var damageTransaction = getDamageTransaction(preCloseResponse);
    transactionAddUseCase.add(damageTransaction);
    final var toCloseInsuranceCase = loadPort.loadById(insuranceCaseId);
    toCloseInsuranceCase.setActive(false);
    updatePort.update(toCloseInsuranceCase);
  }

  private TransactionAddRequest getDamageTransaction(
      final InsuranceCasePreCloseResponse preCloseResponse) {
    final var transaction = new TransactionAddRequest();
    transaction.setAmount(preCloseResponse.getPaymentAmount());
    transaction.setDriverId(preCloseResponse.getInsuranceCaseId());
    transaction.setDate(qDateTime.getToday());
    final var transactionTypeNameForDamage = "damage payment";
    transaction.setTransactionTypeId(getTransactionTypeIdByName(transactionTypeNameForDamage));
    transaction.setComment(
        "Close payment for insurance case with id: " + preCloseResponse.getInsuranceCaseId());
    return transaction;
  }

  private BigDecimal getAmountPaid(final InsuranceCase toCloseInsuranceCase) {
    return transactionQuery.getAllByInsuranceCaseId(toCloseInsuranceCase.getId()).stream()
        .map(TransactionResponse::getRealAmount)
        .map(BigDecimal::abs)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal getAmountToPayAdjustedByQKasko(
      final InsuranceCase insuranceCase, final boolean withQKasko) {
    if (withQKasko) {
      final var damage =
          insuranceCase.getDamageAmount().divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);

      return damage.add(BigDecimal.valueOf(500));
    }

    return insuranceCase.getDamageAmount();
  }

  private Long getTransactionTypeIdByName(final String transactionTypeName) {
    final var transactionType = transactionTypeQuery.getByName(transactionTypeName);
    if (transactionType == null) {
      throw new RuntimeException(
          format(
              "Transaction Type with name: %s, does not exist. "
                  + "Please create it, before Insurance Case Damage calculation",
              transactionTypeName));
    }
    return transactionType.getId();
  }
}
