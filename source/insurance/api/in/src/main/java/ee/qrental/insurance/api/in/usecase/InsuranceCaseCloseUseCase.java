package ee.qrental.insurance.api.in.usecase;

import ee.qrental.insurance.api.in.request.InsuranceCaseCloseRequest;
import ee.qrental.insurance.api.in.request.InsuranceCasePreCloseResponse;

import java.util.List;

public interface InsuranceCaseCloseUseCase {

  InsuranceCasePreCloseResponse getPreCloseResponse(final Long insuranceCaseId);

  List<InsuranceCasePreCloseResponse> getPreCloseResponses(final List<Long> insuranceCaseIds);

  void close(final InsuranceCaseCloseRequest request);
}
