package ee.qrent.email.core.service.messagestrategy;

import ee.qrent.email.api.in.request.EmailSendRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;

public interface LetterBuildStrategy {
    boolean canApply(final EmailSendRequest emailSendRequest);

  MimeMessage process(final EmailSendRequest emailSendRequest, final MimeMessage message)
      throws MessagingException, IOException;
}
