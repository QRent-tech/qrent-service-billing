package ee.qrent.billing.contract.core.service;

import static java.util.Collections.singletonList;

import ee.qrent.billing.contract.api.in.request.AuthorizationSendByEmailRequest;
import ee.qrent.billing.contract.api.in.usecase.AuthorizationPdfUseCase;
import ee.qrent.billing.contract.api.in.usecase.AuthorizationSendByEmailUseCase;
import ee.qrent.billing.contract.api.out.AuthorizationLoadPort;
import ee.qrent.email.api.in.request.EmailSendRequest;
import ee.qrent.email.api.in.request.EmailType;
import ee.qrent.email.api.in.usecase.EmailSendUseCase;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class AuthorizationSendByEmailService implements AuthorizationSendByEmailUseCase {

  private final EmailSendUseCase emailSendUseCase;
  private final AuthorizationLoadPort loadPort;
  private final AuthorizationPdfUseCase pdfUseCase;

  @SneakyThrows
  @Override
  public void sendByEmail(final AuthorizationSendByEmailRequest request) {
    final var authorizationId = request.getId();
    final var authorization = loadPort.loadById(authorizationId);

    final var recipient = authorization.getDriverEmail();
    final var attachment = pdfUseCase.getPdfInputStreamById(authorizationId);
    final var properties = new HashMap<String, Object>();
    properties.put("isikukood", authorization.getDriverIsikukood());

    final var emailSendRequest =
        EmailSendRequest.builder()
            .type(EmailType.AUTHORIZATION)
            .recipients(singletonList(recipient))
            .attachment(attachment)
            .properties(properties)
            .build();

    emailSendUseCase.sendEmail(emailSendRequest);
  }
}
