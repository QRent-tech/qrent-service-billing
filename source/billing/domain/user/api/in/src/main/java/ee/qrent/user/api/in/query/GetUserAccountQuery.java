package ee.qrent.user.api.in.query;

import ee.qrent.common.in.query.BaseGetQuery;
import ee.qrent.user.api.in.request.UserAccountUpdateRequest;
import ee.qrent.user.api.in.response.UserAccountResponse;

import java.util.List;

public interface GetUserAccountQuery
    extends BaseGetQuery<UserAccountUpdateRequest, UserAccountResponse> {
  List<UserAccountResponse> getAllOperators();

  UserAccountResponse getUserAccountByUsername(final String emailOrUsername);
}
