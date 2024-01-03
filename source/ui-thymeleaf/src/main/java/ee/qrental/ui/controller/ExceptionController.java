package ee.qrental.ui.controller;

import ee.qrental.email.api.in.request.EmailSendRequest;
import ee.qrental.email.api.in.request.EmailType;
import ee.qrental.email.api.in.usecase.EmailSendUseCase;
import ee.qrental.user.api.in.query.GetUserAccountQuery;
import ee.qrental.user.api.in.response.UserAccountResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionController {

  private final GetUserAccountQuery userAccountQuery;
  private final EmailSendUseCase emailSendUseCase;

  @ExceptionHandler(Exception.class)
  public String handleException(final Exception exception, final Model model) {
    //TODO move to some service
    final var operators = userAccountQuery.getAllOperators();
    final var recipients = operators.stream().map(UserAccountResponse::getEmail).toList();
    final var emailProperties = new HashMap<String, Object>();
    final var uuid = UUID.randomUUID().toString();
    emailProperties.put("uuid", uuid);
    final var errorMessage = exception.getMessage();
    emailProperties.put("errorMessage", errorMessage);

    final var stringWriter = new StringWriter();
    exception.printStackTrace(new PrintWriter(stringWriter));
    final var stackTrace = stringWriter.toString();
    emailProperties.put("stackTrace", stackTrace);

    final var emailSendRequest =
        EmailSendRequest.builder()
            .type(EmailType.ERROR)
            .recipients(recipients)
            .properties(emailProperties)
            .build();

    emailSendUseCase.sendEmail(emailSendRequest);

    model.addAttribute("uuid", uuid);

    return "error";
  }
}
