package ee.qrental.insurance.core.service.balance;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.insurance.api.in.query.GetQKaskoQuery;
import ee.qrental.insurance.api.out.InsuranceCaseBalanceLoadPort;
import ee.qrental.insurance.domain.InsuranceCase;
import ee.qrental.insurance.domain.InsuranceCaseBalance;
import ee.qrental.transaction.api.in.query.type.GetTransactionTypeQuery;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.in.usecase.TransactionAddUseCase;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.math.BigDecimal.ZERO;

public class InsuranceCaseBalanceWithoutQKaskoCalculationStrategy
    extends AbstractInsuranceCaseBalanceCalculationStrategy {

  public InsuranceCaseBalanceWithoutQKaskoCalculationStrategy(
      final GetQWeekQuery qWeekQuery,
      final GetQKaskoQuery qKaskoQuery,
      final GetTransactionTypeQuery transactionTypeQuery,
      final TransactionAddUseCase transactionAddUseCase,
      final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort) {
    super(
        qWeekQuery,
        qKaskoQuery,
        transactionTypeQuery,
        transactionAddUseCase,
        insuranceCaseBalanceLoadPort);
  }

  @Override
  public boolean canApply(final Long driverId, final Long qWeekId) {

    return !getQKaskoQuery().hasQKasko(driverId, qWeekId);
  }

  @Override
  public InsuranceCaseBalance calculate(
      final InsuranceCase insuranceCase, final QWeekResponse requestedQWeek) {
    final var requestedQWeekId = requestedQWeek.getId();
    final var driverId = insuranceCase.getDriverId();
    final var previousWeekBalance =
        getPreviousWeekInsuranceCaseBalance(insuranceCase, requestedQWeekId);
    var damageAmount = ZERO;
    if (previousWeekBalance == null) {
      damageAmount = insuranceCase.getDamageAmount();
    } else {
      final var damageRemaining = previousWeekBalance.getDamageRemaining();
      final var selfResponsibilityRemaining = previousWeekBalance.getSelfResponsibilityRemaining();
      damageAmount = selfResponsibilityRemaining.add(damageRemaining);
    }
    final var damageTransaction = getDamageTransaction(driverId, requestedQWeek, damageAmount);
    final var requestedBalance =
        InsuranceCaseBalance.builder()
            .insuranceCase(insuranceCase)
            .transactionIds(new ArrayList<>())
            .qWeekId(requestedQWeekId)
            .withQKasko(FALSE)
            .damageRemaining(ZERO)
            .selfResponsibilityRemaining(ZERO)
            .build();
    saveTransactionAndAddToBalance(damageTransaction, requestedBalance);

    return requestedBalance;
  }

  @Override
  public void setRemainingAndSelfResponsibilityFirstTime(
      final InsuranceCase insuranceCase, final InsuranceCaseBalance insuranceCaseBalance) {
    insuranceCaseBalance.setDamageRemaining(insuranceCase.getDamageAmount());
    insuranceCaseBalance.setSelfResponsibilityRemaining(ZERO);
  }

  private TransactionAddRequest getDamageTransaction(
      final Long driverId, final QWeekResponse qWeek, final BigDecimal damageRemaining) {
    final var addRequest = new TransactionAddRequest();
    addRequest.setComment(
        "No Kasko. Leak amount from Insurance Case or Previous Balance. Automatically created transaction.");
    addRequest.setDriverId(driverId);
    addRequest.setAmount(damageRemaining);
    final var transactionTypeNameForDamage = "damage payment";
    addRequest.setTransactionTypeId(getTransactionTypeIdByName(transactionTypeNameForDamage));
    addRequest.setDate(qWeek.getStart());

    return addRequest;
  }
}
