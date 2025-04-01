package ee.qrent.driver.core.mapper;

import static java.lang.String.format;

import ee.qrent.common.in.mapper.ResponseMapper;
import ee.qrent.driver.api.in.response.CallSignResponse;
import ee.qrent.driver.domain.CallSign;

public class CallSignResponseMapper implements ResponseMapper<CallSignResponse, CallSign> {
  @Override
  public CallSignResponse toResponse(final CallSign domain) {
    return CallSignResponse.builder()
        .id(domain.getId())
        .callSign(domain.getCallSign())
        .comment(domain.getComment())
        .build();
  }

  @Override
  public String toObjectInfo(CallSign domain) {
    return format("%s", domain.getCallSign());
  }
}
