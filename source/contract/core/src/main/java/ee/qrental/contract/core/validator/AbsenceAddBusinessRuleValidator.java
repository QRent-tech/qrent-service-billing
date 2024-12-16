package ee.qrental.contract.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.request.AbsenceAddRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.domain.Absence;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AbsenceAddBusinessRuleValidator {

  private final GetBalanceQuery balanceQuery;
  private final GetQWeekQuery qWeekQuery;
  private final AbsenceLoadPort loadPort;

  public ViolationsCollector validateAdd(final AbsenceAddRequest addRequest) {
    final var violationsCollector = new ViolationsCollector();
    checkTimeOverlapping(addRequest, violationsCollector);
    checkBalanceCalculation(addRequest, violationsCollector);

    return violationsCollector;
  }

  private void checkTimeOverlapping(
      final AbsenceAddRequest addRequest, final ViolationsCollector violationCollector) {
    final var driverId = addRequest.getDriverId();
    final var dateStart = addRequest.getDateStart();
    final var dateEnd = addRequest.getDateEnd();
    List<Absence> overlappedAbsences = null;
    if (dateEnd == null) {
      overlappedAbsences = loadPort.loadByDriverIdAndDateStart(driverId, dateStart);
    } else {
      overlappedAbsences =
          loadPort.loadByDriverIdAndDateStartAndDateEnd(driverId, dateStart, dateEnd);
    }
    if (overlappedAbsences == null) {

      return;
    }
    violationCollector.collect(
        format(
            "Absences for the driver id: %d  and time interval: [%s ... %s ] already exists.",
            driverId, dateStart.toString(), dateEnd == null ? "empty" : dateEnd.toString()));
  }

  private void checkBalanceCalculation(
      final AbsenceAddRequest addRequest, final ViolationsCollector violationCollector) {
    final var latestBalance = balanceQuery.getLatest();
    if (latestBalance == null) {
      return;
    }

    final var latestBalanceQWeekId = latestBalance.getQWeekId();
    final var latestQWeek = qWeekQuery.getById(latestBalanceQWeekId);
    if (latestQWeek.getEnd().equals(addRequest.getDateStart())
        || latestQWeek.getEnd().isAfter(addRequest.getDateStart())) {
      violationCollector.collect(
          "Absences Can not be added. Balance was calulated for the mentioned time period");
    }
  }
}
