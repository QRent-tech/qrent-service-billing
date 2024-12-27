package ee.qrental.common.core.validation;

import ee.qrent.common.in.request.AbstractDeleteRequest;
import ee.qrent.common.in.request.ViolationsCollector;

public interface DeleteRequestValidator<R extends AbstractDeleteRequest> {
  ViolationsCollector validate(final R request);
}
