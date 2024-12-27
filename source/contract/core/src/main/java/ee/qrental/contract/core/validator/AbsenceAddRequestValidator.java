package ee.qrental.contract.core.validator;

import static java.lang.String.format;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.request.AbsenceAddRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;

public class AbsenceAddRequestValidator extends AbstractAbsenceRequestValidator
    implements AddRequestValidator<AbsenceAddRequest> {

  public AbsenceAddRequestValidator(
      final GetBalanceQuery balanceQuery,
      final GetQWeekQuery qWeekQuery,
      final AbsenceLoadPort loadPort) {
    super(balanceQuery, qWeekQuery, loadPort);
  }

  public ViolationsCollector validate(final AbsenceAddRequest addRequest) {
    final var driverId = addRequest.getDriverId();
    final var dateStart = addRequest.getDateStart();
    final var dateEnd = addRequest.getDateEnd();
    final var violationsCollector = new ViolationsCollector();

    final var overlappedAbsences = getOverlappedAbsences(driverId, dateStart, dateEnd);
    if (!overlappedAbsences.isEmpty()) {
      final var dateStartString = dateStart.toString();
      final var dateEndString = dateEnd == null ? "empty" : dateEnd.toString();

      violationsCollector.collect(
          format(
              "Absences can not be added for the driver id: %d and time interval: [%s ... %s ] already exists.",
              driverId, dateStartString, dateEndString));
    }

    return collectViolationsForOverlappingWithBalanceCalculation(dateStart, violationsCollector);
  }
}
