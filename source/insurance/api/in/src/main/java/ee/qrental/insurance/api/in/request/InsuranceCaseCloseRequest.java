package ee.qrental.insurance.api.in.request;

import ee.qrent.common.in.request.AbstractRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InsuranceCaseCloseRequest extends AbstractRequest {
  private Long id;
}
