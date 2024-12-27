package ee.qrental.contract.core.validator;

import static java.lang.String.format;

import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.request.AbsenceUpdateRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;

public class AbsenceUpdateRequestValidator extends AbstractAbsenceRequestValidator
    implements UpdateRequestValidator<AbsenceUpdateRequest> {

  public AbsenceUpdateRequestValidator(
      final GetBalanceQuery balanceQuery,
      final GetQWeekQuery qWeekQuery,
      final AbsenceLoadPort loadPort) {
    super(balanceQuery, qWeekQuery, loadPort);
  }

  public ViolationsCollector validate(final AbsenceUpdateRequest updateRequest) {
    final var id = updateRequest.getId();
    final var driverId = updateRequest.getDriverId();
    final var dateStart = updateRequest.getDateStart();
    final var dateEnd = updateRequest.getDateEnd();
    final var violationsCollector = new ViolationsCollector();
    final var overlappedAbsences = getOverlappedAbsences(driverId, dateStart, dateEnd);
    final var overlappedAbsencesExceptUpdatedCount =
        overlappedAbsences.stream().filter(absence -> !absence.getId().equals(id)).count();

    if (overlappedAbsencesExceptUpdatedCount > 0L) {
      violationsCollector.collect(
          format(
              "Absences can not be updated for the driver id: %d  and time interval: [%s ... %s ], because it overlaps with existing.",
              driverId, dateStart.toString(), dateEnd == null ? "empty" : dateEnd.toString()));
    }

    return collectViolationsForOverlappingWithBalanceCalculation(dateStart, violationsCollector);
  }
}
