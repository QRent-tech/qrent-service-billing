package ee.qrental.transaction.core.validator;

import static ee.qrental.common.utils.QTimeUtils.Q_DATE_FORMATTER;
import static java.lang.String.format;

import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.request.TransactionDeleteRequest;
import ee.qrental.transaction.api.in.request.TransactionUpdateRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.domain.Transaction;
import ee.qrental.transaction.domain.balance.Balance;
import java.time.LocalDate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionRequestValidator
    implements UpdateRequestValidator<TransactionUpdateRequest>,
        DeleteRequestValidator<TransactionDeleteRequest> {

  private final GetQWeekQuery qWeekQuery;
  private final TransactionLoadPort transactionLoadPort;
  private final BalanceLoadPort balanceLoadPort;

  @Override
  public ViolationsCollector validate(final TransactionUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var transactionId = request.getId();
    final var transactionFromDb = transactionLoadPort.loadById(transactionId);
    final var latestBalance = balanceLoadPort.loadLatest();
    if (latestBalance == null) {
      return violationsCollector;
    }

    final var latestBalanceCalculatedDate = getLatestBalanceDate(latestBalance);

    checkExistence(request.getId(), transactionFromDb, violationsCollector);
    checkIfUpdateAllowed(latestBalanceCalculatedDate, transactionFromDb, violationsCollector);
    checkNewDate(latestBalanceCalculatedDate, request.getDate(), violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final TransactionDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var latestBalance = balanceLoadPort.loadLatest();
    if (latestBalance == null) {

      return violationsCollector;
    }
    final var latestBalanceQWeek = qWeekQuery.getById(latestBalance.getQWeekId());
    final var latestBalanceCalculatedDate = latestBalanceQWeek.getEnd();

    final var transactionFromDb = transactionLoadPort.loadById(request.getId());
    checkIfDeleteAllowed(latestBalanceCalculatedDate, transactionFromDb, violationsCollector);

    return violationsCollector;
  }

  private void checkIfUpdateAllowed(
      final LocalDate balanceLatestCalculatedDate,
      final Transaction fromDb,
      final ViolationsCollector violationCollector) {
    final var transactionDate = fromDb.getDate();
    final var transactionId = fromDb.getId();
    if (transactionDate.isBefore(balanceLatestCalculatedDate)
        || transactionDate.equals(balanceLatestCalculatedDate)) {
      violationCollector.collect(
          format(
              "Update for the Transaction with id=%d is prohibited. Transaction is already calculated in Balance",
              transactionId));
    }
  }

  private void checkNewDate(
      final LocalDate balanceLatestCalculatedDate,
      final LocalDate transactionDate,
      final ViolationsCollector violationCollector) {
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

  private void checkIfDeleteAllowed(
      final LocalDate balanceLatestCalculatedDate,
      final Transaction fromDb,
      final ViolationsCollector violationCollector) {
    final var transactionDate = fromDb.getDate();
    final var transactionId = fromDb.getId();
    if (transactionDate.isBefore(balanceLatestCalculatedDate)
        || transactionDate.equals(balanceLatestCalculatedDate)) {
      violationCollector.collect(
          format(
              "Delete for the Transaction with id=%d is prohibited. Transaction is already calculated in Balance",
              transactionId));
    }
  }

  private void checkExistence(
      final Long id, final Transaction fromDb, final ViolationsCollector violationCollector) {
    if (fromDb == null) {
      violationCollector.collect("Update of Transaction failed. No Record with id = " + id);
    }
  }

  private LocalDate getLatestBalanceDate(final Balance balance) {
    final var latestBalanceQWeek = qWeekQuery.getById(balance.getQWeekId());
    return latestBalanceQWeek.getEnd();
  }
}
