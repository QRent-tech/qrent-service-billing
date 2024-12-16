package ee.qrental.contract.api.in.request;

import ee.qrent.common.in.request.AbstractUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class AbsenceUpdateRequest extends AbstractUpdateRequest {
  private Long id;
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private Boolean withCar;
  private String reason;
  private Long driverId;
  private String comment;
}
