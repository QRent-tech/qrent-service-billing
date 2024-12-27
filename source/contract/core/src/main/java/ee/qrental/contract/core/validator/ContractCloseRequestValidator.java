package ee.qrental.contract.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.CloseRequestValidator;
import ee.qrental.contract.api.in.request.ContractCloseRequest;
import ee.qrental.contract.api.out.ContractLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContractCloseRequestValidator implements CloseRequestValidator<ContractCloseRequest> {

  private final ContractLoadPort contractLoadPort;

  public ViolationsCollector validate(final ContractCloseRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var requestId = request.getId();

    final var contractToClose = contractLoadPort.loadById(requestId);

    if (contractToClose == null) {
      violationsCollector.collect("Contract with id " + requestId + " not found");
    }
    return violationsCollector;
  }
}
