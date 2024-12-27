package ee.qrental.common.core.validation;

import ee.qrent.common.in.request.AbstractUpdateRequest;
import ee.qrent.common.in.request.ViolationsCollector;

public interface UpdateRequestValidator<R extends AbstractUpdateRequest> {
  ViolationsCollector validate(final R request);
}
