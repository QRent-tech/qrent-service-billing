package ee.qrental.contract.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrental.contract.domain.Absence;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceLoadPort extends LoadPort<Absence> {
  List<Absence> loadByDriverIdAndDateStartAndDateEnd(
      final Long driverId, final LocalDate dteStart, final LocalDate dteEnd);

  List<Absence> loadByDriverIdAndDateStart(
          final Long driverId, final LocalDate dteStart);
}
