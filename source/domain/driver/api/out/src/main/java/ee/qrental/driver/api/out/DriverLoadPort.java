package ee.qrental.driver.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrental.driver.domain.Driver;

import java.util.Collection;
import java.util.List;

public interface DriverLoadPort extends LoadPort<Driver> {
  List<Driver> loadByMatchCountAndQWeekId(
      final Integer matchCount, final Long latestCalculatedQWeekId);
}
