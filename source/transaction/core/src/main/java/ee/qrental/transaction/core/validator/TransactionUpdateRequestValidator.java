package ee.qrental.transaction.core.validator;

import static ee.qrental.common.utils.QTimeUtils.Q_DATE_FORMATTER;
import static java.lang.String.format;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.request.TransactionUpdateRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.domain.balance.Balance;
import java.time.LocalDate;

public class TransactionUpdateRequestValidator extends AbstractTransactionRequestValidator
    implements UpdateRequestValidator<TransactionUpdateRequest> {

  public TransactionUpdateRequestValidator(
      GetQWeekQuery qWeekQuery,
      TransactionLoadPort transactionLoadPort,
      BalanceLoadPort balanceLoadPort) {
    super(qWeekQuery, transactionLoadPort, balanceLoadPort);
  }

  @Override
  public ViolationsCollector validate(final TransactionUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var transactionId = request.getId();
    final var transactionFromDb = getTransactionLoadPort().loadById(transactionId);
    final var latestBalance = getBalanceLoadPort().loadLatest();
    if (latestBalance == null) {
      return violationsCollector;
    }

    final var latestBalanceCalculatedDate = getLatestBalanceDate(latestBalance);

    checkExistence(request.getId(), transactionFromDb, violationsCollector);
    checkIfModificationAllowed(latestBalanceCalculatedDate, transactionFromDb, violationsCollector);
    checkNewDate(latestBalanceCalculatedDate, request, violationsCollector);

    return violationsCollector;
  }

  private void checkNewDate(
      final LocalDate balanceLatestCalculatedDate,
      final TransactionUpdateRequest request,
      final ViolationsCollector violationCollector) {
    final var transactionDate = request.getDate();
    if (transactionDate.isBefore(balanceLatestCalculatedDate)
        || transactionDate.equals(balanceLatestCalculatedDate)) {
      final var formattedTransactionDate = Q_DATE_FORMATTER.format(transactionDate);
      final var formattedBalanceCalculatedDate =
          Q_DATE_FORMATTER.format(balanceLatestCalculatedDate);
      violationCollector.collect(
          format(
              "Transaction new date %s must be after the latest calculated Balance date: %s",
              formattedTransactionDate, formattedBalanceCalculatedDate));
    }
  }

  private LocalDate getLatestBalanceDate(final Balance balance) {
    final var latestBalanceQWeek = getQWeekQuery().getById(balance.getQWeekId());
    return latestBalanceQWeek.getEnd();
  }
}
