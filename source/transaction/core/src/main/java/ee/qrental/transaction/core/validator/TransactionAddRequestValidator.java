package ee.qrental.transaction.core.validator;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofLocalizedDate;
import static java.time.format.FormatStyle.MEDIUM;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.domain.balance.Balance;
import java.time.LocalDate;

public class TransactionAddRequestValidator extends AbstractTransactionRequestValidator
    implements AddRequestValidator<TransactionAddRequest> {

  public TransactionAddRequestValidator(
      final GetQWeekQuery qWeekQuery,
      final TransactionLoadPort transactionLoadPort,
      final BalanceLoadPort balanceLoadPort) {
    super(qWeekQuery, transactionLoadPort, balanceLoadPort);
  }

  public ViolationsCollector validate(final TransactionAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkTransactionTypeForAdd(request, violationsCollector);

    final var latestBalance = getBalanceLoadPort().loadLatestByDriverId(request.getDriverId());
    if (latestBalance == null) {
      return violationsCollector;
    }
    final var latestBalanceDate = getLatestBalanceDate(latestBalance);
    checkDateForAdd(latestBalanceDate, request, violationsCollector);

    return violationsCollector;
  }

  private void checkTransactionTypeForAdd(
      final TransactionAddRequest request, final ViolationsCollector violationCollector) {
    if (request.getTransactionTypeId() == null) {
      violationCollector.collect("Transaction Type mus be selected");
    }
  }

  private void checkDateForAdd(
      final LocalDate balanceLatestCalculatedDate,
      final TransactionAddRequest request,
      final ViolationsCollector violationCollector) {
    final var transactionDate = request.getDate();

    if (transactionDate.isBefore(balanceLatestCalculatedDate)
        || transactionDate.equals(balanceLatestCalculatedDate)) {
      final var formattedTransactionDate = transactionDate.format(ofLocalizedDate(MEDIUM));
      final var formattedBalanceCalculatedDate =
          balanceLatestCalculatedDate.format(ofLocalizedDate(MEDIUM));
      violationCollector.collect(
          format(
              "Transaction date %s must be after the latest calculated Balance date: %s",
              formattedTransactionDate, formattedBalanceCalculatedDate));
    }
  }

  private LocalDate getLatestBalanceDate(final Balance balance) {
    final var latestBalanceQWeek = getQWeekQuery().getById(balance.getQWeekId());
    return latestBalanceQWeek.getEnd();
  }
}
