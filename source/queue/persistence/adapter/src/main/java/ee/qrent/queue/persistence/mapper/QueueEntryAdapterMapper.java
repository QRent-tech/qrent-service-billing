package ee.qrent.queue.persistence.mapper;

import ee.qrent.queue.domain.QueueEntry;
import ee.qrent.queue.persistence.entity.jakarta.QueueEntryJakartaEntity;

public class QueueEntryAdapterMapper {

  public QueueEntry mapToDomain(final QueueEntryJakartaEntity entity) {

    return QueueEntry.builder()
        .id(entity.getId())
        .occurredAt(entity.getOccurredAt())
        .publishedAt(entity.getPublishedAt())
        .processed(entity.getProcessed())
        .processedAt(entity.getProcessedAt())
        .type(entity.getType())
        .payload(entity.getPayload())
        .build();
  }

  public QueueEntryJakartaEntity mapToEntity(final QueueEntry domain) {

    return QueueEntryJakartaEntity.builder()
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
