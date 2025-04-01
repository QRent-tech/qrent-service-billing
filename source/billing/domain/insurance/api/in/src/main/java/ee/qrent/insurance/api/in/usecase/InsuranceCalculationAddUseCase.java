package ee.qrent.insurance.api.in.usecase;

import ee.qrent.common.in.usecase.AddUseCase;
import ee.qrent.insurance.api.in.request.InsuranceCalculationAddRequest;

public interface InsuranceCalculationAddUseCase extends AddUseCase<InsuranceCalculationAddRequest> {
  Long add(final InsuranceCalculationAddRequest request);
}
