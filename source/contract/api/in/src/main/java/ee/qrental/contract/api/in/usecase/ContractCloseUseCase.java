package ee.qrental.contract.api.in.usecase;

import ee.qrental.contract.api.in.request.ContractCloseRequest;
import ee.qrental.contract.api.in.response.ContractPreCloseResponse;

public interface ContractCloseUseCase {
  ContractPreCloseResponse getPreCloseResponse(final Long contractId);

  void close(final ContractCloseRequest request);
}
