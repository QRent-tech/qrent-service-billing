package ee.qrent.insurance.api.in.query;

import ee.qrent.insurance.api.in.response.*;

public interface GetInsuranceCaseBalanceQuery {

  InsuranceBalanceTotalResponse getInsuranceBalanceTotalByDriverForCurrentWeek(final Long driverId);

  InsuranceBalanceTotalResponse getInsuranceBalanceTotalByDriverIdAndQWeekId(final Long driverId, final Long qWeekId);
}
