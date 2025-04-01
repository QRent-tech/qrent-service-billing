package ee.qrent.contract.api.in.usecase;

import ee.qrent.contract.api.in.request.AuthorizationSendByEmailRequest;

public interface AuthorizationSendByEmailUseCase {

  void sendByEmail(final AuthorizationSendByEmailRequest request);
}