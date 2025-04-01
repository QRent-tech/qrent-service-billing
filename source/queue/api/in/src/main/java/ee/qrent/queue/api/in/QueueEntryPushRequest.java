package ee.qrent.queue.api.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@SuperBuilder
@Getter
public class QueueEntryPushRequest {
  private final LocalDateTime occurredAt;
  private final String payload;
  private final EntryType type;
}
