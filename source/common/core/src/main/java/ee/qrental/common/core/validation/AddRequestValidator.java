package ee.qrental.common.core.validation;

import ee.qrent.common.in.request.AbstractAddRequest;
import ee.qrent.common.in.request.ViolationsCollector;

public interface AddRequestValidator<R extends AbstractAddRequest> {
  ViolationsCollector validate(final R request);
}
