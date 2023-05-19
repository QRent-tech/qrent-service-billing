package ee.qrental.driver.core.mapper;

import ee.qrental.common.core.in.mapper.UpdateRequestMapper;
import ee.qrental.driver.api.in.request.CallSignUpdateRequest;
import ee.qrental.driver.domain.CallSign;

public class CallSignUpdateRequestMapper
    implements UpdateRequestMapper<CallSignUpdateRequest, CallSign> {

  @Override
  public CallSign toDomain(final CallSignUpdateRequest request) {
    return CallSign.builder()
        .id(request.getId())
        .callSign(request.getCallSign())
        .comment(request.getComment())
        .build();
  }

  @Override
  public CallSignUpdateRequest toRequest(final CallSign domain) {
    return CallSignUpdateRequest.builder()
        .id(domain.getId())
        .callSign(domain.getCallSign())
        .comment(domain.getComment())
        .build();
  }
}
