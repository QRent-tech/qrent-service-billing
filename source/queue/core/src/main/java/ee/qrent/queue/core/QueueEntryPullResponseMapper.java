package ee.qrent.queue.core;

import ee.qrent.queue.api.in.QueuePullResponse;
import ee.qrent.queue.domain.QueueEntry;

public class QueueEntryPullResponseMapper {
  QueuePullResponse toResponse(final QueueEntry domain) {
    return QueuePullResponse.builder()
        .id(domain.getId())
        .occurredAt(domain.getOccurredAt())
        .publishedAt(domain.getPublishedAt())
        .processed(domain.getProcessed())
        .processedAt(domain.getProcessedAt())
        .payloadRecipients(domain.getPayload().getRecipients())
        .payloadType(domain.getPayload().getType())
        .payloadAttachment(domain.getPayload().getAttachment())
        .payloadProperties(domain.getPayload().getProperties())
        .build();
  }
}
