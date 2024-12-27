package ee.qrental.common.core.validation;

import ee.qrent.common.in.request.AbstractCloseRequest;
import ee.qrent.common.in.request.ViolationsCollector;

public interface CloseRequestValidator<R extends AbstractCloseRequest> {
  ViolationsCollector validate(final R request);
}
