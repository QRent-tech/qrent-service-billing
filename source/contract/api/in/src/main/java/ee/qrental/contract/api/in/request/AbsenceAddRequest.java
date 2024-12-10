package ee.qrental.contract.api.in.request;

import ee.qrent.common.in.request.AbstractAddRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AbsenceAddRequest extends AbstractAddRequest {
  private Long driverId;
  private Long qWeekId;
  private String comment;
}
