package ee.qrental.driver.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.driver.api.in.request.CallSignAddRequest;
import ee.qrental.driver.api.in.request.CallSignDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignUpdateRequest;
import ee.qrental.driver.api.out.CallSignLinkLoadPort;
import ee.qrental.driver.api.out.CallSignLoadPort;
import java.util.Objects;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignAddUpdateDeleteRequestValidator
    implements AddRequestValidator<CallSignAddRequest>,
        UpdateRequestValidator<CallSignUpdateRequest>,
        DeleteRequestValidator<CallSignDeleteRequest> {

  private final CallSignLoadPort loadPort;
  private final CallSignLinkLoadPort callSignLinkLoadPort;

  @Override
  public ViolationsCollector validate(final CallSignAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkUniquenessForAdd(request, violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final CallSignUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request.getId(), violationsCollector);
    checkUniquenessForUpdate(request, violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final CallSignDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkReferences(request.getId(), violationsCollector);

    return violationsCollector;
  }

  private void checkUniquenessForAdd(
      final CallSignAddRequest request, final ViolationsCollector violationCollector) {
    final var callSign = request.getCallSign();
    final var domainFromDb = loadPort.loadByCallSign(callSign);
    if (domainFromDb == null) {
      return;
    }

    violationCollector.collect(format("Call Sign %d already exists", callSign));
  }

  private void checkUniquenessForUpdate(
      final CallSignUpdateRequest request, final ViolationsCollector violationCollector) {
    final var callSign = request.getCallSign();
    final var domainFromDb = loadPort.loadByCallSign(callSign);
    if (domainFromDb == null) {
      return;
    }
    if (Objects.equals(domainFromDb.getId(), request.getId())) {
      return;
    }
    violationCollector.collect(
        format("Call Sign %d can not be updated, because such Number already in use", callSign));
  }

  private void checkReferences(final Long id, final ViolationsCollector violationCollector) {

    final var callSignLinks = callSignLinkLoadPort.loadByCallSignId(id);
    if (callSignLinks.isEmpty()) {
      return;
    }
    violationCollector.collect(
        format(
            "Call Sign with id: %d can not be deleted, because it uses in %d Call Sign Links",
            id, callSignLinks.size()));
  }

  private void checkExistence(final Long id, final ViolationsCollector violationCollector) {
    if (loadPort.loadById(id) == null) {
      violationCollector.collect("Update of CallSign Link failed. No Record with id = " + id);
    }
  }
}
