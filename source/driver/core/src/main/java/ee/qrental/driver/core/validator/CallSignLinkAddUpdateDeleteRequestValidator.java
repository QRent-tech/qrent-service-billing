package ee.qrental.driver.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.driver.api.in.request.CallSignLinkAddRequest;
import ee.qrental.driver.api.in.request.CallSignLinkDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignLinkUpdateRequest;
import ee.qrental.driver.api.out.CallSignLinkLoadPort;
import ee.qrental.driver.api.out.CallSignLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignLinkAddUpdateDeleteRequestValidator
    implements AddRequestValidator<CallSignLinkAddRequest>,
        UpdateRequestValidator<CallSignLinkUpdateRequest>,
        DeleteRequestValidator<CallSignLinkDeleteRequest> {

  private final CallSignLinkLoadPort loadPort;
  private final CallSignLoadPort callSignLoadPort;

  @Override
  public ViolationsCollector validate(final CallSignLinkAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkIsCallSignFree(request, violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final CallSignLinkUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request.getId(), violationsCollector);
    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final CallSignLinkDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    return violationsCollector;
  }

  private void checkIsCallSignFree(
      final CallSignLinkAddRequest request, final ViolationsCollector violationCollector) {
    final var callSignId = request.getCallSignId();
    final var activeCallSignLink = loadPort.loadActiveByCallSignId(callSignId);
    if (activeCallSignLink != null) {
      final var callSign = callSignLoadPort.loadById(callSignId);
      violationCollector.collect(
          format(
              "Call Sign: '%d' already uses by active Link and can not be linked.",
              callSign.getCallSign()));
    }
  }

  private void checkExistence(final Long id, final ViolationsCollector violationCollector) {
    if (loadPort.loadById(id) == null) {
      violationCollector.collect("Update of CallSign Link failed. No Record with id = " + id);
    }
  }
}
