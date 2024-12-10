package ee.qrental.contract.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrental.contract.domain.Absence;
import java.util.List;

public interface AbsenceLoadPort extends LoadPort<Absence> {
  Absence loadByDriverIdAndQWekId(final Long driverId, final Long qWeekId);

  List<Absence> loadByDriverIdAndStartQWeekId(final Long driverId, final Long startQWeekId);
}
