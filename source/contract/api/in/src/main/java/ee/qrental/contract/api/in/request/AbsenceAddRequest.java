package ee.qrental.contract.api.in.request;

import ee.qrent.common.in.request.AbstractAddRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class AbsenceAddRequest extends AbstractAddRequest {
  private Long driverId;
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private Boolean withCar;
  private String reason;
  private String comment;
}
