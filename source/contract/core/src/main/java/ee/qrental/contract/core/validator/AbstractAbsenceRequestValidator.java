package ee.qrental.contract.core.validator;

import static lombok.AccessLevel.PROTECTED;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;

import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.domain.Absence;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class AbstractAbsenceRequestValidator {

  @Getter(PROTECTED)
  private final GetBalanceQuery balanceQuery;

  @Getter(PROTECTED)
  private final GetQWeekQuery qWeekQuery;

  @Getter(PROTECTED)
  private final AbsenceLoadPort loadPort;


  protected List<Absence> getOverlappedAbsences(
      final Long driverId, final LocalDate dateStart, final LocalDate dateEnd) {
    if (dateEnd == null) {
      return loadPort.loadByDriverIdAndDateStart(driverId, dateStart);
    }
    return loadPort.loadByDriverIdAndDateStartAndDateEnd(driverId, dateStart, dateEnd);
  }

  protected ViolationsCollector collectViolationsForOverlappingWithBalanceCalculation(
      final LocalDate dateStart, final ViolationsCollector violationCollector) {
    final var latestBalance = balanceQuery.getLatest();
    if (latestBalance == null) {
      return violationCollector;
    }

    final var latestBalanceQWeekId = latestBalance.getQWeekId();
    final var latestQWeek = qWeekQuery.getById(latestBalanceQWeekId);
    if (latestQWeek.getEnd().equals(dateStart) || latestQWeek.getEnd().isAfter(dateStart)) {
      violationCollector.collect(
          "Absences can not be modified. Balance has been calculated for the related time period");
    }
    return violationCollector;
  }
}
