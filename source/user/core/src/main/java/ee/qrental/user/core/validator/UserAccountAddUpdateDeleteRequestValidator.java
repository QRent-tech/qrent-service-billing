package ee.qrental.user.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.user.api.in.request.UserAccountAddRequest;
import ee.qrental.user.api.in.request.UserAccountDeleteRequest;
import ee.qrental.user.api.in.request.UserAccountUpdateRequest;
import ee.qrental.user.api.out.UserAccountLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAccountAddUpdateDeleteRequestValidator
    implements AddRequestValidator<UserAccountAddRequest>,
        UpdateRequestValidator<UserAccountUpdateRequest>,
        DeleteRequestValidator<UserAccountDeleteRequest> {

  private final UserAccountLoadPort loadPort;

  @Override
  public ViolationsCollector validate(final UserAccountAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkEmailUniquenessForAdd(request.getEmail(), violationsCollector);
    checkUsernameUniquenessForAdd(request.getUsername(), violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final UserAccountUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final UserAccountDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    // TODO check smth?
    return violationsCollector;
  }

  private void checkEmailUniquenessForAdd(
      final String email, final ViolationsCollector violationCollector) {
    final var domainFromDb = loadPort.loadByEmail(email);
    if (domainFromDb == null) {
      return;
    }

    violationCollector.collect(format("User with email: %d already exists", email));
  }

  private void checkUsernameUniquenessForAdd(
      final String username, final ViolationsCollector violationCollector) {
    final var domainFromDb = loadPort.loadByUsername(username);
    if (domainFromDb == null) {
      return;
    }

    violationCollector.collect(format("User Account with username: %s already exists", username));
  }
}
