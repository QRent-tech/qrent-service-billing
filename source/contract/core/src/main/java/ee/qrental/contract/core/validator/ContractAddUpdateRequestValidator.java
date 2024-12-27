package ee.qrental.contract.core.validator;

import static ee.qrental.contract.core.ContractUtils.generateContractNumber;
import static java.lang.String.format;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.contract.api.in.request.ContractAddRequest;
import ee.qrental.contract.api.in.request.ContractUpdateRequest;
import ee.qrental.contract.api.out.ContractLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContractAddUpdateRequestValidator
    implements AddRequestValidator<ContractAddRequest>,
        UpdateRequestValidator<ContractUpdateRequest>{

  private final ContractLoadPort loadPort;

  @Override
  public ViolationsCollector validate(final ContractAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkUniqueness(request, violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final ContractUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request.getId(), violationsCollector);

    return violationsCollector;
  }

  private void checkUniqueness(
      final ContractAddRequest request, final ViolationsCollector violationCollector) {
    final var number = generateContractNumber(request.getDriverId(), request.getDateStart());
    final var contractFromDb = loadPort.loadByNumber(number);
    if (contractFromDb == null) {

      return;
    }
    violationCollector.collect(format("Contract with number: %s already exists.", number));
  }

  private void checkExistence(final Long id, final ViolationsCollector violationCollector) {
    if (loadPort.loadById(id) == null) {
      violationCollector.collect("Update of Contract failed. No Record with id = " + id);
    }
  }
}
