package ee.qrental.ui.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DriverCountsResponse {
  private Long activeCallSignLinkCount;
  private Long closedCallSignLinkCount;
  private Long activeContractCount;
  private Long closedContractCount;
}
