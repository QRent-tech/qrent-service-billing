package ee.qrental.user.core.service;

import static ee.qrental.email.api.in.request.EmailType.USER_REGISTRATION;
import static java.util.Collections.singletonList;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.driver.domain.UserAccount;
import ee.qrental.email.api.in.request.EmailSendRequest;
import ee.qrental.email.api.in.usecase.EmailSendUseCase;
import ee.qrental.user.api.in.request.UserAccountAddRequest;
import ee.qrental.user.api.in.request.UserAccountDeleteRequest;
import ee.qrental.user.api.in.request.UserAccountUpdateRequest;
import ee.qrental.user.api.in.usecase.UserAccountAddUseCase;
import ee.qrental.user.api.in.usecase.UserAccountDeleteUseCase;
import ee.qrental.user.api.in.usecase.UserAccountUpdateUseCase;
import ee.qrental.user.api.out.UserAccountAddPort;
import ee.qrental.user.api.out.UserAccountDeletePort;
import ee.qrental.user.api.out.UserAccountLoadPort;
import ee.qrental.user.api.out.UserAccountUpdatePort;
import ee.qrental.user.core.mapper.UserAccountAddRequestMapper;
import ee.qrental.user.core.mapper.UserAccountUpdateRequestMapper;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Random;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAccountUseCaseService
    implements UserAccountAddUseCase, UserAccountUpdateUseCase, UserAccountDeleteUseCase {

  private final UserAccountAddPort addPort;
  private final UserAccountUpdatePort updatePort;
  private final UserAccountDeletePort deletePort;
  private final UserAccountAddRequestMapper addRequestMapper;
  private final UserAccountUpdateRequestMapper updateRequestMapper;
  private final AddRequestValidator<UserAccountAddRequest> addRequestValidator;
  private final UpdateRequestValidator<UserAccountUpdateRequest> updateRequestValidator;
  private final DeleteRequestValidator<UserAccountDeleteRequest> deleteRequestValidator;

  private final EmailSendUseCase emailSendUseCase;

  @Transactional
  @Override
  public Long add(final UserAccountAddRequest request) {
    final var violationsCollector = addRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return null;
    }
    final var domain = addRequestMapper.toDomain(request);
    domain.setPassword(getRandomString());
    sendEmailToUser(domain);
    final var savedDomain = addPort.add(domain);

    return savedDomain.getId();
  }

  private void sendEmailToUser(final UserAccount domain) {
    final var properties = new HashMap<String, Object>();
    properties.put("password", domain.getPassword());
    properties.put("username", domain.getUsername());
    properties.put("firstName", domain.getFirstName());
    properties.put("lastName", domain.getLastName());

    final var registrationNewUserEmailRequest =
        EmailSendRequest.builder()
            .type(USER_REGISTRATION)
            .recipients(singletonList(domain.getEmail()))
            .properties(properties)
            .build();
    emailSendUseCase.sendEmail(registrationNewUserEmailRequest);
  }

  @Override
  public void update(final UserAccountUpdateRequest request) {
    final var violationsCollector = updateRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }
    updatePort.update(updateRequestMapper.toDomain(request));
  }

  @Override
  public void delete(final UserAccountDeleteRequest request) {
    final var violationsCollector = deleteRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }

    deletePort.delete(request.getId());
  }


  private String getRandomString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    final var random = new Random();
    return random
        .ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(8)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
