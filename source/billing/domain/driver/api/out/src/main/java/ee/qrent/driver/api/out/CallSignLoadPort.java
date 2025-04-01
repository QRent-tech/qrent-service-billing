package ee.qrent.driver.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrent.driver.domain.CallSign;
import java.util.List;

public interface CallSignLoadPort extends LoadPort<CallSign> {
  CallSign loadByCallSign(final Integer callSign);

  List<CallSign> loadAvailable();
}
