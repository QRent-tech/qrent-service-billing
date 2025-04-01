package ee.qrental.contract.core.validator;

import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.request.AbsenceDeleteRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.domain.Absence;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsenceDeleteRequestValidator implements DeleteRequestValidator<AbsenceDeleteRequest> {

  private final GetBalanceQuery balanceQuery;
  private final GetQWeekQuery qWeekQuery;
  private final AbsenceLoadPort loadPort;

  public ViolationsCollector validate(final AbsenceDeleteRequest deleteRequest) {
    final var violationsCollector = new ViolationsCollector();
    final var absenceToDelete = loadPort.loadById(deleteRequest.getId());
    final var absenceToDeleteDateStart = absenceToDelete.getDateStart();

    return collectViolationsForOverlappingWithBalanceCalculation(
        absenceToDeleteDateStart, violationsCollector);
  }

  private ViolationsCollector collectViolationsForOverlappingWithBalanceCalculation(
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
