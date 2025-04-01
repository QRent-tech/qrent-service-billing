package ee.qrental.ui.service.insurance.impl;

import ee.qrental.insurance.api.in.query.GetInsuranceCaseQuery;
import ee.qrental.ui.service.insurance.InsuranceCounterService;
import ee.qrental.ui.service.insurance.InsuranceCountsResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceCounterServiceImpl implements InsuranceCounterService {

  private GetInsuranceCaseQuery insuranceCaseQuery;

  @Override
  public InsuranceCountsResponse getInsuranceCounts() {
    final var activeCounts = insuranceCaseQuery.getCountActive();
    final var closedCounts = insuranceCaseQuery.getCountClosed();

    return InsuranceCountsResponse.builder()
        .activeCasesCount(activeCounts)
        .closedCasesCount(closedCounts)
        .build();
  }
}
