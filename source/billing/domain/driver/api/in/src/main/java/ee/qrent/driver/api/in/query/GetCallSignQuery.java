package ee.qrent.driver.api.in.query;

import ee.qrent.common.in.query.BaseGetQuery;
import ee.qrent.driver.api.in.request.CallSignUpdateRequest;
import ee.qrent.driver.api.in.response.CallSignResponse;

import java.util.List;

public interface GetCallSignQuery extends BaseGetQuery<CallSignUpdateRequest, CallSignResponse> {

  List<CallSignResponse> getAvailable();
}
