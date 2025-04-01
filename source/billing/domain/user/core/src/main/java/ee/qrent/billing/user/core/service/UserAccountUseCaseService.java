package ee.qrent.billing.user.core.service;

import static ee.qrent.email.api.in.request.EmailType.USER_REGISTRATION;
import static java.util.Collections.singletonList;

import ee.qrent.billing.user.domain.UserAccount;
import ee.qrent.email.api.in.request.EmailSendRequest;
import ee.qrent.email.api.in.usecase.EmailSendUseCase;
import ee.qrent.security.api.in.usecase.PasswordUseCase;
import ee.qrent.billing.user.api.in.request.UserAccountAddRequest;
import ee.qrent.billing.user.api.in.request.UserAccountDeleteRequest;
import ee.qrent.billing.user.api.in.request.UserAccountUpdateRequest;
import ee.qrent.billing.user.api.in.usecase.UserAccountAddUseCase;
import ee.qrent.billing.user.api.in.usecase.UserAccountDeleteUseCase;
import ee.qrent.billing.user.api.in.usecase.UserAccountUpdateUseCase;
import ee.qrent.billing.user.api.out.UserAccountAddPort;
import ee.qrent.billing.user.api.out.UserAccountDeletePort;
import ee.qrent.billing.user.api.out.UserAccountLoadPort;
import ee.qrent.billing.user.api.out.UserAccountUpdatePort;
import ee.qrent.billing.user.core.mapper.UserAccountAddRequestMapper;
import ee.qrent.billing.user.core.mapper.UserAccountUpdateRequestMapper;
import ee.qrent.billing.user.core.validator.UserAccountRequestValidator;
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
  private final UserAccountLoadPort loadPort;
  private final UserAccountAddRequestMapper addRequestMapper;
  private final UserAccountUpdateRequestMapper updateRequestMapper;
  private final UserAccountRequestValidator requestValidator;
  private final EmailSendUseCase emailSendUseCase;
  private final PasswordUseCase passwordUseCase;

  @Transactional
  @Override
  public Long add(final UserAccountAddRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return null;
    }
    final var domain = addRequestMapper.toDomain(request);
    final var password = generatePassword();
    final var encodedPassword = passwordUseCase.encode(password);
    domain.setPassword(encodedPassword);
    sendEmailToUser(domain, password);
    final var savedDomain = addPort.add(domain);

    return savedDomain.getId();
  }

  private void sendEmailToUser(final UserAccount domain, final String password) {
    final var properties = new HashMap<String, Object>();
    properties.put("password", password);
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
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }
    updatePort.update(updateRequestMapper.toDomain(request));
  }

  @Override
  public void delete(final UserAccountDeleteRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }
    deletePort.delete(request.getId());
  }

  private void checkExistence(final Long id) {
    if (loadPort.loadById(id) == null) {
      throw new RuntimeException("Update of Driver failed. No Record with id = " + id);
    }
  }

  private String generatePassword() {
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
