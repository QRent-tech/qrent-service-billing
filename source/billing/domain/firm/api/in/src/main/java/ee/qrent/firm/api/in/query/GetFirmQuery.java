package ee.qrent.firm.api.in.query;

import ee.qrent.common.in.query.BaseGetQuery;
import ee.qrent.firm.api.in.request.FirmUpdateRequest;
import ee.qrent.firm.api.in.response.FirmResponse;

public interface GetFirmQuery extends BaseGetQuery<FirmUpdateRequest, FirmResponse> {}
