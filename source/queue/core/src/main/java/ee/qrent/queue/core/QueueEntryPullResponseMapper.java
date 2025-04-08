package ee.qrent.queue.core;

import ee.qrent.queue.api.in.EntryType;
import ee.qrent.queue.api.in.QueuePullResponse;
import ee.qrent.queue.domain.QueueEntry;

import java.io.ByteArrayInputStream;

public class QueueEntryPullResponseMapper {
  QueuePullResponse toResponse(final QueueEntry domain) {
    final var attachmentInputStream =
        new ByteArrayInputStream(domain.getPayload().getAttachmentBytes());

    return QueuePullResponse.builder()
        .id(domain.getId())
        .occurredAt(domain.getOccurredAt())
        .publishedAt(domain.getPublishedAt())
        .processed(domain.getProcessed())
        .processedAt(domain.getProcessedAt())
        .payloadRecipients(domain.getPayload().getRecipients())
        .payloadType(EntryType.valueOf(domain.getPayload().getType()))
        .payloadAttachment(attachmentInputStream)
        .payloadProperties(domain.getPayload().getProperties())
        .build();
  }
}
