package ee.qrental.insurance.api.in.usecase;

import ee.qrental.insurance.api.in.request.InsuranceCaseCloseRequest;
import ee.qrental.insurance.api.in.request.InsuranceCasePreCloseResponse;

public interface InsuranceCaseCloseUseCase {
  InsuranceCasePreCloseResponse getPreCloseResponse(final Long insuranceCaseId);

  void close(final InsuranceCaseCloseRequest request);
}
