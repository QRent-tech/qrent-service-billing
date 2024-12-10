package ee.qrental.contract.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.contract.api.in.request.AbsenceAddRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsenceAddBusinessRuleValidator {

  private final AbsenceLoadPort loadPort;

  public ViolationsCollector validateAdd(final AbsenceAddRequest addRequest) {
    final var violationsCollector = new ViolationsCollector();
    checkUniqueness(addRequest, violationsCollector);

    return violationsCollector;
  }

  private void checkUniqueness(
      final AbsenceAddRequest addRequest, final ViolationsCollector violationCollector) {
    final var driverId = addRequest.getDriverId();
    final var qWeekId = addRequest.getQWeekId();
    final var contractFromDb = loadPort.loadByDriverIdAndQWekId(driverId, qWeekId);
    if (contractFromDb == null) {

      return;
    }
    violationCollector.collect(
        format(
            "Absence for the driver id: %d and for the week id: %d already exists.",
            driverId, qWeekId));
  }
}
