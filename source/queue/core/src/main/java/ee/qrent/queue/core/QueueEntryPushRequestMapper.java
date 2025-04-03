package ee.qrent.queue.core;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.queue.api.in.QueueEntryPushRequest;
import ee.qrent.queue.domain.NotificationPayload;
import ee.qrent.queue.domain.QueueEntry;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueueEntryPushRequestMapper {

  private static final boolean DEFAULT_PROCESSED = false;

  private final QDateTime qDateTime;

  public QueueEntry toDomain(final QueueEntryPushRequest pushRequest) {
    return QueueEntry.builder()
        .id(null)
        .occurredAt(pushRequest.getOccurredAt())
        .processed(DEFAULT_PROCESSED)
        .processedAt(null)
        .publishedAt(qDateTime.getNow())
        .payload(mapPayload(pushRequest))
        .build();
  }

  private NotificationPayload mapPayload(final QueueEntryPushRequest pushRequest) {

    return NotificationPayload.builder()
        .recipients(pushRequest.getPayloadRecipients())
        .type(pushRequest.getType().name())
        .attachment(pushRequest.getPayloadAttachment())
        .properties(pushRequest.getPayloadProperties())
        .build();
  }
}
