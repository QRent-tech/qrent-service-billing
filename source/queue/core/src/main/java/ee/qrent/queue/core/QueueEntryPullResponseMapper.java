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
        .type(domain.getType())
        .payload(domain.getPayload())
        .build();
  }
}
