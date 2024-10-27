package ee.qrental.ui.service.impl;

import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.driver.api.in.query.GetCallSignLinkQuery;
import ee.qrental.ui.service.DriverCounterService;
import ee.qrental.ui.service.DriverCountsResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DriverCounterServiceImpl implements DriverCounterService {
  private final GetCallSignLinkQuery callSignLinkQuery;
  private final GetContractQuery contractQuery;

  @Override
  public DriverCountsResponse getDriverCounts() {
    final var activeLinksCount = callSignLinkQuery.getCountActive();
    final var closedLinksCount = callSignLinkQuery.getCountClosed();
    final var activeContractCount = contractQuery.getCountActive();
    final var closedContractCount = contractQuery.getCountClosed();

    return DriverCountsResponse.builder()
        .activeContractCount(activeContractCount)
        .closedContractCount(closedContractCount)
        .activeCallSignLinkCount(activeLinksCount)
        .closedCallSignLinkCount(closedLinksCount)
        .build();
  }
}
