package ee.qrental.insurance.core.service.balance;

import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.insurance.domain.InsuranceCase;
import ee.qrental.insurance.domain.InsuranceCaseBalance;

public interface InsuranceCaseBalanceCalculationStrategy {

  boolean canApply(final Long qWeekId, final Long driverId);

  InsuranceCaseBalance calculate(
      final InsuranceCase insuranceCase, final QWeekResponse requestedQWeek);

  void setRemainingAndSelfResponsibilityFirstTime(
      final InsuranceCase insuranceCase, final InsuranceCaseBalance insuranceCaseBalance);
}
