package ee.qrental.driver.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.validation.*;
import ee.qrental.driver.api.in.request.CallSignAddRequest;
import ee.qrental.driver.api.in.request.CallSignDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignUpdateRequest;
import ee.qrental.driver.api.out.CallSignLinkLoadPort;
import ee.qrental.driver.api.out.CallSignLoadPort;

import java.util.Objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignRequestValidator
    implements AddRequestValidator<CallSignAddRequest>,
        UpdateRequestValidator<CallSignUpdateRequest>,
        DeleteRequestValidator<CallSignDeleteRequest> {

  private final CallSignLoadPort loadPort;
  private final CallSignLinkLoadPort callSignLinkLoadPort;
  private final AttributeChecker attributeChecker;

  private static final int LENGTH_MAX_COMMENT = 200;
  private static final Integer DECIMAL_MIN_CALL_SIGN = 1;
  private static final Integer DECIMAL_MAX_CALL_SIGN = 999;

  @Override
  public ViolationsCollector validate(final CallSignAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkValidNumberForAdd(request, violationsCollector);
    checkUniquenessForAdd(request, violationsCollector);
    checkCommentForAdd(request, violationsCollector);

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

  private void checkValidNumberForAdd(
      final CallSignAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Call Sign";
    final var attributeValue = request.getCallSign();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkDecimalValueRange(
        attributeName,
        attributeValue,
        DECIMAL_MIN_CALL_SIGN,
        DECIMAL_MAX_CALL_SIGN,
        violationsCollector);
  }

  private void checkCommentForAdd(
      final CallSignAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Comment", request.getComment(), null, LENGTH_MAX_COMMENT, violationsCollector);
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
