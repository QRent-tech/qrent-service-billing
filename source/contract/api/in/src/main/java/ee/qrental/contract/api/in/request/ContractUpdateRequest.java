package ee.qrental.contract.api.in.request;

import ee.qrent.common.in.request.AbstractUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class ContractUpdateRequest extends AbstractUpdateRequest {
  private Long id;
  private Long qFirmId;
}
