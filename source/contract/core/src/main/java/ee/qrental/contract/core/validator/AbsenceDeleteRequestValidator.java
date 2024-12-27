package ee.qrental.contract.core.validator;

import ee.qrental.common.core.validation.DeleteRequestValidator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.request.AbsenceDeleteRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;

public class AbsenceDeleteRequestValidator extends AbstractAbsenceRequestValidator
    implements DeleteRequestValidator<AbsenceDeleteRequest> {

  public AbsenceDeleteRequestValidator(
      final GetBalanceQuery balanceQuery,
      final GetQWeekQuery qWeekQuery,
      final AbsenceLoadPort loadPort) {
    super(balanceQuery, qWeekQuery, loadPort);
  }

  public ViolationsCollector validate(final AbsenceDeleteRequest deleteRequest) {
    final var violationsCollector = new ViolationsCollector();
    final var absenceToDelete = getLoadPort().loadById(deleteRequest.getId());
    final var absenceToDeleteDateStart = absenceToDelete.getDateStart();

    return collectViolationsForOverlappingWithBalanceCalculation(
        absenceToDeleteDateStart, violationsCollector);
  }
}
