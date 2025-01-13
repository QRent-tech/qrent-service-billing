package ee.qrental.driver.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.common.in.validation.ViolationsCollector;
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

  private static final int MAX_COMMENT_LENGTH = 200;
  private static final int MIN_CALL_SIGN_NUMBER = 1;
  private static final int MAX_CALL_SIGN_NUMBER = 999;

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

    final var callSign = request.getCallSign();

    if (callSign >= MIN_CALL_SIGN_NUMBER && callSign <= MAX_CALL_SIGN_NUMBER) {
      return;
    }

    violationsCollector.collect(
        format(
            "Invalid number call sign (Min %d and Max %d)",
            MIN_CALL_SIGN_NUMBER, MAX_CALL_SIGN_NUMBER));
  }

  private void checkCommentForAdd(
      final CallSignAddRequest request, final ViolationsCollector violationsCollector) {
    final var comment = request.getComment();

    if (comment.length() <= MAX_COMMENT_LENGTH) {
      return;
    }

    violationsCollector.collect(format("Too long comment (Max %d characters)", MAX_COMMENT_LENGTH));
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
