package ee.qrental.insurance.core.service.balance;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.insurance.api.in.query.GetQKaskoQuery;
import ee.qrental.insurance.api.out.InsuranceCaseBalanceLoadPort;
import ee.qrental.insurance.core.service.InsuranceCaseBalanceCalculator;
import ee.qrental.insurance.domain.InsuranceCase;
import ee.qrental.insurance.domain.InsuranceCaseBalance;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.query.type.GetTransactionTypeQuery;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.in.response.TransactionResponse;
import ee.qrental.transaction.api.in.usecase.TransactionAddUseCase;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ee.qrental.transaction.api.in.utils.TransactionTypeConstant.*;
import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
public class InsuranceCaseBalanceCalculatorService implements InsuranceCaseBalanceCalculator {

  private static final BigDecimal DEFAULT_SELF_RESPONSIBILITY = BigDecimal.valueOf(500);
  private static final BigDecimal PERCENTAGE_FROM_RENT_AMOUNT = BigDecimal.valueOf(0.25d);
  private static final BigDecimal Q_KASKO_DISCOUNT_RATE = BigDecimal.valueOf(2);

  private final GetQKaskoQuery qKaskoQuery;
  private final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort;
  private final GetTransactionQuery transactionQuery;
  private final GetTransactionTypeQuery transactionTypeQuery;
  private final InsuranceCaseBalanceDeriveService deriveService;
  private final TransactionAddUseCase transactionAddUseCase;
  private final GetQWeekQuery qWeekQuery;

  @Override
  public InsuranceCaseBalance calculateBalance(
      final InsuranceCase insuranceCase, final QWeekResponse requestedQWeek) {
    final var driverId = insuranceCase.getDriverId();
    final var requestedQWeekId = requestedQWeek.getId();
    final var requestedWeekBalance = getInsuranceCaseBalance(insuranceCase, requestedQWeekId);
    final var hasQKasko = qKaskoQuery.hasQKasko(driverId, requestedQWeekId);
    final var damageTransaction =
        getDamageTransaction(driverId, requestedQWeek, requestedWeekBalance, hasQKasko);
    final var selfResponsibilityTransaction =
        getSelfResponsibilityTransaction(driverId, requestedQWeek, requestedWeekBalance, hasQKasko);
    deriveService.derive(requestedWeekBalance, damageTransaction, selfResponsibilityTransaction);
    saveDamageTransaction(damageTransaction, requestedWeekBalance);
    saveSelfResponsibilityTransaction(selfResponsibilityTransaction, requestedWeekBalance);

    return requestedWeekBalance;
  }

  private void saveDamageTransaction(
      TransactionAddRequest damageTransaction, InsuranceCaseBalance requestedWeekBalance) {
    final var transactionId = transactionAddUseCase.add(damageTransaction);
    if (transactionId != null) {
      requestedWeekBalance.getTransactionIds().add(transactionId);
    }
  }

  private void saveSelfResponsibilityTransaction(
      final TransactionAddRequest selfResponsibilityTransaction,
      final InsuranceCaseBalance requestedWeekBalance) {
    if (selfResponsibilityTransaction.getAmount().compareTo(ZERO) == 0) {

      return;
    }
    final var transactionId = transactionAddUseCase.add(selfResponsibilityTransaction);
    if (transactionId != null) {
      requestedWeekBalance.getTransactionIds().add(transactionId);
    }
  }

  @Override
  public void setFirstRemainingAndSelfResponsibility(
      final InsuranceCase insuranceCase, final InsuranceCaseBalance insuranceCaseBalance) {
    final var originalDamage = insuranceCase.getDamageAmount();
    if (originalDamage.compareTo(DEFAULT_SELF_RESPONSIBILITY) <= 0) {
      insuranceCaseBalance.setSelfResponsibilityRemaining(ZERO);
      insuranceCaseBalance.setDamageRemaining(originalDamage);
    } else {
      var damageRemaining = originalDamage.subtract(DEFAULT_SELF_RESPONSIBILITY);
      final var hasQKaskoOnRequestedWeek =
          qKaskoQuery.hasQKasko(insuranceCase.getDriverId(), insuranceCaseBalance.getQWeekId());

      if (hasQKaskoOnRequestedWeek) {
        damageRemaining = damageRemaining.divide(Q_KASKO_DISCOUNT_RATE);
      }
      insuranceCaseBalance.setSelfResponsibilityRemaining(DEFAULT_SELF_RESPONSIBILITY);
      insuranceCaseBalance.setDamageRemaining(damageRemaining);
    }
  }

  private Long getPreviousWeekId(final Long qWeekId) {
    final var previousWeek = qWeekQuery.getOneBeforeById(qWeekId);
    if (previousWeek == null) {
      throw new RuntimeException("Previous week not found for qWeekId: " + qWeekId);
    }
    return previousWeek.getId();
  }

  private InsuranceCaseBalance getInsuranceCaseBalance(
      final InsuranceCase insuranceCase, final Long qWeekId) {
    final var result =
        InsuranceCaseBalance.builder()
            .insuranceCase(insuranceCase)
            .transactionIds(new ArrayList<>())
            .qWeekId(qWeekId)
            .build();

    final var previousWeekId = getPreviousWeekId(qWeekId);

    final var previousWeekBalance =
        insuranceCaseBalanceLoadPort.loadByInsuranceCaseIdAndQWeekId(
            insuranceCase.getId(), previousWeekId);

    if (previousWeekBalance == null) {
      setFirstRemainingAndSelfResponsibility(insuranceCase, result);

      return result;
    }

    result.setDamageRemaining(previousWeekBalance.getDamageRemaining());
    result.setSelfResponsibilityRemaining(previousWeekBalance.getSelfResponsibilityRemaining());

    return result;
  }

  private TransactionAddRequest getDamageTransaction(
      final Long driverId,
      final QWeekResponse qWeek,
      final InsuranceCaseBalance insuranceCaseBalance,
      final boolean hasActiveContract) {
    var amountForDamageCompensation = getDamageCompensationAmount(driverId, qWeek.getId());
    if (!hasActiveContract) {
      final var originalDamageAmount = insuranceCaseBalance.getInsuranceCase().getDamageAmount();
      final var insuranceCaseId = insuranceCaseBalance.getInsuranceCase().getId();
      final var paidAmount =
          transactionQuery.getAllByInsuranceCaseId(insuranceCaseId).stream()
              .map(TransactionResponse::getRealAmount)
              .reduce(BigDecimal.ZERO, BigDecimal::add);

      amountForDamageCompensation = originalDamageAmount.subtract(paidAmount);
    }

    final var damagePaymentTransaction = new TransactionAddRequest();
    damagePaymentTransaction.setComment(
        "Automatically created transaction for the damage compensation.");
    damagePaymentTransaction.setDriverId(driverId);
    damagePaymentTransaction.setAmount(amountForDamageCompensation);
    final var transactionTypeNameForDamage = "damage payment";
    damagePaymentTransaction.setTransactionTypeId(
        getTransactionTypeIdByName(transactionTypeNameForDamage));
    damagePaymentTransaction.setDate(qWeek.getStart());

    return damagePaymentTransaction;
  }

  private TransactionAddRequest getSelfResponsibilityTransaction(
      final Long driverId,
      final QWeekResponse qWeek,
      final InsuranceCaseBalance insuranceCaseBalance,
      final boolean hasActiveContract) {
    var selfResponsibilityAmount = ZERO;

    if (hasActiveContract) {
      selfResponsibilityAmount = getRequestedSelfResponsibilityAmountAbs(driverId, qWeek.getId());
    } else {
      selfResponsibilityAmount = insuranceCaseBalance.getSelfResponsibilityRemaining();
    }

    final var selfResponsibilityTransaction = new TransactionAddRequest();
    selfResponsibilityTransaction.setComment(
        "Automatically created transaction for the Self Responsibility Payment Request compensation.");
    selfResponsibilityTransaction.setDriverId(driverId);
    selfResponsibilityTransaction.setAmount(selfResponsibilityAmount);
    final var transactionTypeNameForSelfResponsibility = "self responsibility payment";
    selfResponsibilityTransaction.setTransactionTypeId(
        getTransactionTypeIdByName(transactionTypeNameForSelfResponsibility));
    selfResponsibilityTransaction.setDate(qWeek.getStart());

    return selfResponsibilityTransaction;
  }

  private BigDecimal getDamageCompensationAmount(final Long driverId, final Long qWeekId) {
    final var rentAndNonLabelFineTransactionsAmountAbs =
        getAbsRentAndNonLabelFineTransactionsAmountAbs(driverId, qWeekId);

    return rentAndNonLabelFineTransactionsAmountAbs.multiply(PERCENTAGE_FROM_RENT_AMOUNT);
  }

  private BigDecimal getAbsRentAndNonLabelFineTransactionsAmountAbs(
      final Long driverId, final Long qWeekId) {
    return transactionQuery.getAllByDriverIdAndQWeekId(driverId, qWeekId).stream()
        .filter(
            transaction ->
                List.of(TRANSACTION_TYPE_NAME_WEEKLY_RENT, TRANSACTION_TYPE_NO_LABEL_FINE)
                    .contains(transaction.getType()))
        .map(TransactionResponse::getRealAmount)
        .reduce(ZERO, BigDecimal::add)
        .abs();
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

  private BigDecimal getRequestedSelfResponsibilityAmountAbs(
      final Long driverId, final Long qWeekId) {
    return transactionQuery.getAllByDriverIdAndQWeekId(driverId, qWeekId).stream()
        .filter(transaction -> TRANSACTION_TYPE_SELF_RESPONSIBILITY.equals(transaction.getType()))
        .map(TransactionResponse::getRealAmount)
        .reduce(ZERO, BigDecimal::add)
        .abs();
  }
}
