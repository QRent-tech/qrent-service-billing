package ee.qrental.driver.core.service;

import static java.util.stream.Collectors.toList;

import ee.qrental.driver.api.in.query.GetCallSignQuery;
import ee.qrental.driver.api.in.request.CallSignUpdateRequest;
import ee.qrental.driver.api.in.response.CallSignResponse;
import ee.qrental.driver.api.out.CallSignLoadPort;
import ee.qrental.driver.core.mapper.CallSignResponseMapper;
import ee.qrental.driver.core.mapper.CallSignUpdateRequestMapper;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignQueryService implements GetCallSignQuery {

  private final CallSignLoadPort loadPort;
  private final CallSignResponseMapper mapper;
  private final CallSignUpdateRequestMapper updateRequestMapper;

  @Override
  public List<CallSignResponse> getAll() {
    return loadPort.loadAll().stream().map(mapper::toResponse).collect(toList());
  }

  @Override
  public CallSignResponse getById(final Long id) {
    return mapper.toResponse(loadPort.loadById(id));
  }

  @Override
  public String getObjectInfo(Long id) {
    return mapper.toObjectInfo(loadPort.loadById(id));
  }

  @Override
  public CallSignUpdateRequest getUpdateRequestById(Long id) {
    return updateRequestMapper.toRequest(loadPort.loadById(id));
  }
}
