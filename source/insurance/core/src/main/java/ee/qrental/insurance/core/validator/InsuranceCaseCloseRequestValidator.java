package ee.qrental.insurance.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.CloseRequestValidator;
import ee.qrental.insurance.api.in.request.InsuranceCaseCloseRequest;
import ee.qrental.insurance.api.out.InsuranceCaseLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceCaseCloseRequestValidator implements CloseRequestValidator<InsuranceCaseCloseRequest> {

  private final InsuranceCaseLoadPort loadPort;

  public ViolationsCollector validate(final InsuranceCaseCloseRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var requestId = request.getId();

    final var insuranceCaseToClose = loadPort.loadById(requestId);

    if (!insuranceCaseToClose.getActive()) {
      violationsCollector.collect("Insurance Case already closed");
    }
    return violationsCollector;
  }
}
