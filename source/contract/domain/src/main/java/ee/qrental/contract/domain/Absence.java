package ee.qrental.contract.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Absence {
  private Long id;
  private Long driverId;
  private Long qWeekId;
  private String comment;
}
