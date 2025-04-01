package ee.qrent.driver.api.in.usecase;

import ee.qrent.driver.api.in.request.CallSignLinkCloseRequest;

public interface CallSignLinkCloseUseCase {
  void close(final CallSignLinkCloseRequest request);
}
