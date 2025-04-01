package ee.qrent.queue.persistence.entity.jakarta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "q_queue")
@Audited
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QueueEntryJakartaEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "occurred_at")
  private LocalDateTime occurredAt;

  @Column(name = "published_at")
  private LocalDateTime publishedAt;

  @Column(name = "processed")
  private Boolean processed;

  @Column(name = "processed_at")
  private LocalDateTime processedAt;

  @Column(name = "type")
  private String type;

  @Column(name = "payload")
  private String payload;
}
