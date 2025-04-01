package ee.qrent.driver.core.mapper;

import ee.qrent.common.in.mapper.AddRequestMapper;
import ee.qrent.driver.api.in.request.CallSignAddRequest;
import ee.qrent.driver.domain.CallSign;

public class CallSignAddRequestMapper implements AddRequestMapper<CallSignAddRequest, CallSign> {

  @Override
  public CallSign toDomain(CallSignAddRequest request) {
    return CallSign.builder()
        .id(null)
        .callSign(request.getCallSign())
        .comment(request.getComment())
        .build();
  }
}
