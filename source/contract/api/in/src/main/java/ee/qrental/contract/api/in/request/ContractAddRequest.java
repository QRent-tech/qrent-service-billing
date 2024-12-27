package ee.qrental.contract.api.in.request;

import ee.qrent.common.in.request.AbstractAddRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ContractAddRequest extends AbstractAddRequest {
  private LocalDateTime dateStart;
  private Long driverId;
  private Long qFirmId;
  private String contractDuration;
}
