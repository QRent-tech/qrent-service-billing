package ee.qrent.user.api.in.query;

import ee.qrent.common.in.query.BaseGetQuery;
import ee.qrent.user.api.in.request.RoleUpdateRequest;
import ee.qrent.user.api.in.response.RoleResponse;

public interface GetRoleQuery extends BaseGetQuery<RoleUpdateRequest, RoleResponse> {}
