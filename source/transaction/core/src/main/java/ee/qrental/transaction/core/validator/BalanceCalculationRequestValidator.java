package ee.qrental.transaction.core.validator;

import static ee.qrental.common.utils.QTimeUtils.getLastSundayFromDate;
import static java.time.temporal.ChronoUnit.DAYS;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.transaction.api.in.request.balance.BalanceCalculationAddRequest;
import ee.qrental.transaction.api.out.balance.BalanceCalculationLoadPort;

import java.time.LocalDate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BalanceCalculationRequestValidator
    implements AddRequestValidator<BalanceCalculationAddRequest> {

  private final BalanceCalculationLoadPort loadPort;

  @Override
  public ViolationsCollector validate(final BalanceCalculationAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    if (isCalculationRequired(request.getActionDate())) {
      return violationsCollector;
    }
    violationsCollector.collect("No Data for Balance Calculation.");

    return violationsCollector;
  }

  public boolean isCalculationRequired(final LocalDate actionDate) {
    final var actionDateFormal = getLastSundayFromDate(actionDate);
    final var lastCalculationDate = loadPort.loadLastCalculatedDate();
    final long daysBetween = DAYS.between(lastCalculationDate, actionDateFormal);

    return daysBetween >= 7;
  }
}
