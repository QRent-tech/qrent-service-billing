package ee.qrent.contract.api.in.usecase;

import ee.qrent.contract.api.in.request.ContractSendByEmailRequest;

public interface ContractSendByEmailUseCase {

  void sendByEmail(final ContractSendByEmailRequest request);
}
