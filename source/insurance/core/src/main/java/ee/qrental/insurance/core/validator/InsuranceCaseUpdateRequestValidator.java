package ee.qrental.insurance.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.insurance.api.in.request.InsuranceCaseUpdateRequest;
import ee.qrental.insurance.api.out.InsuranceCaseBalanceLoadPort;
import ee.qrental.insurance.api.out.InsuranceCaseLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceCaseUpdateRequestValidator
    implements UpdateRequestValidator<InsuranceCaseUpdateRequest> {

  private final InsuranceCaseLoadPort insuranceCaseLoadPort;
  private final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort;

  public ViolationsCollector validate(final InsuranceCaseUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var id = request.getId();

    if (insuranceCaseLoadPort.loadById(id) == null) {
      violationsCollector.collect("Update of InsuranceCase failed. No Record with id = " + id);

      return violationsCollector;
    }

    final var latestBalance = insuranceCaseBalanceLoadPort.loadLatestByInsuranceCaseId(id);
    if (latestBalance != null) {
      violationsCollector.collect(
          "Balance calculation for current Insurance Case already started. Modification is prohibited.");
    }

    return violationsCollector;
  }
}
