package ee.qrent.queue.api.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@SuperBuilder
@Getter
public class QueuePullResponse {
  private Long id;
  private LocalDateTime occurredAt;
  private LocalDateTime publishedAt;
  private Boolean processed;
  private LocalDateTime processedAt;
  private final String type;
  private final String payload;
}
