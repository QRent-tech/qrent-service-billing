package ee.qrental.contract.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.request.AbsenceAddRequest;
import ee.qrental.contract.api.in.request.AbsenceDeleteRequest;
import ee.qrental.contract.api.in.request.AbsenceUpdateRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.domain.Absence;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class AbsenceBusinessRuleValidator {

  private final GetBalanceQuery balanceQuery;
  private final GetQWeekQuery qWeekQuery;
  private final AbsenceLoadPort loadPort;

  public ViolationsCollector validateAdd(final AbsenceAddRequest addRequest) {
    final var driverId = addRequest.getDriverId();
    final var dateStart = addRequest.getDateStart();
    final var dateEnd = addRequest.getDateEnd();

    return collectViolationsForAddOrUpdate(driverId, dateStart, dateEnd);
  }

  public ViolationsCollector validateUpdate(final AbsenceUpdateRequest updateRequest) {
    final var driverId = updateRequest.getDriverId();
    final var dateStart = updateRequest.getDateStart();
    final var dateEnd = updateRequest.getDateEnd();

    return collectViolationsForAddOrUpdate(driverId, dateStart, dateEnd);
  }

  public ViolationsCollector validateDelete(final AbsenceDeleteRequest deleteRequest) {
    final var violationsCollector = new ViolationsCollector();
    final var absenceToDelete = loadPort.loadById(deleteRequest.getId());
    final var absenceToDeleteDateStart = absenceToDelete.getDateStart();
    collectViolationsForOverlappingWithBalanceCalculation(
        absenceToDeleteDateStart, violationsCollector);

    return violationsCollector;
  }

  private ViolationsCollector collectViolationsForAddOrUpdate(
      final Long driverId, final LocalDate dateStart, final LocalDate dateEnd) {
    final var violationsCollector = new ViolationsCollector();
    collectViolationsForTimeOverlappingWithExistingAbsences(
        driverId, dateStart, dateEnd, violationsCollector);
    collectViolationsForOverlappingWithBalanceCalculation(dateStart, violationsCollector);

    return violationsCollector;
  }

  private void collectViolationsForTimeOverlappingWithExistingAbsences(
      final Long driverId,
      final LocalDate dateStart,
      final LocalDate dateEnd,
      final ViolationsCollector violationCollector) {
    List<Absence> overlappedAbsences;
    if (dateEnd == null) {
      overlappedAbsences = loadPort.loadByDriverIdAndDateStart(driverId, dateStart);
    } else {
      overlappedAbsences =
          loadPort.loadByDriverIdAndDateStartAndDateEnd(driverId, dateStart, dateEnd);
    }
    if (overlappedAbsences.isEmpty()) {

      return;
    }
    violationCollector.collect(
        format(
            "Absences for the driver id: %d  and time interval: [%s ... %s ] already exists.",
            driverId, dateStart.toString(), dateEnd == null ? "empty" : dateEnd.toString()));
  }

  private void collectViolationsForOverlappingWithBalanceCalculation(
      final LocalDate dateStart, final ViolationsCollector violationCollector) {
    final var latestBalance = balanceQuery.getLatest();
    if (latestBalance == null) {
      return;
    }

    final var latestBalanceQWeekId = latestBalance.getQWeekId();
    final var latestQWeek = qWeekQuery.getById(latestBalanceQWeekId);
    if (latestQWeek.getEnd().equals(dateStart) || latestQWeek.getEnd().isAfter(dateStart)) {
      violationCollector.collect(
          "Absences can not be modified. Balance has been calculated for the related time period");
    }
  }
}
