package ee.qrent.email.api.in.usecase;

import ee.qrent.email.api.in.request.EmailSendRequest;

public interface EmailSendUseCase {

  void sendEmail(final EmailSendRequest request);
}
