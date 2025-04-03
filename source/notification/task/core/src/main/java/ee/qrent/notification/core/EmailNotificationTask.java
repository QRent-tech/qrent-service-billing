package ee.qrent.notification.core;

import ee.qrent.common.in.usecase.RunTaskUseCase;
import ee.qrent.notification.email.api.in.request.EmailSendRequest;
import ee.qrent.notification.email.api.in.request.EmailType;
import ee.qrent.notification.email.api.in.usecase.EmailSendUseCase;
import ee.qrent.queue.api.in.QueueEntryPullUseCase;
import ee.qrent.queue.api.in.QueuePullResponse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor
public class EmailNotificationTask {

  private final EmailSendUseCase emailSendUseCase;
  private final RunTaskUseCase runTaskUseCase;
  private final QueueEntryPullUseCase queuePullUseCase;

  // seconds minutes hours day-of-month month day-of-week
  //   0       0      8        *         *        ?
  // For example, 25 10 8 * * ?, means that the task is executed at 08:10:25 every day.
  // For example, 25 10 8 * * ?, means that the task is executed at 08:10:25 every day.

  @Scheduled(cron = "5 5 0 * * MON") // means that the task is executed at 00:05:05 every Monday.
  public void scheduleTask() {
    final var executorName = EmailNotificationTask.class.getSimpleName();
    runTaskUseCase.run(
        () -> {
          queuePullUseCase.pull().stream()
              .map(this::mapToRequest)
              .forEach(emailSendUseCase::sendEmail);
        },
        executorName);
  }

  private EmailSendRequest mapToRequest(final QueuePullResponse queuePullResponse) {
    return EmailSendRequest.builder()
        .type(EmailType.valueOf(queuePullResponse.getPayloadType()))
        .recipients(queuePullResponse.getPayloadRecipients())
        .attachment(queuePullResponse.getPayloadAttachment())
        .properties(queuePullResponse.getPayloadProperties())
        .build();
  }
}
