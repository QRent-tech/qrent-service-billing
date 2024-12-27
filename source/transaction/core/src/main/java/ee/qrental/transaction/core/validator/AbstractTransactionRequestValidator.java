package ee.qrental.transaction.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

import static java.lang.String.format;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor
@Getter(PROTECTED)
public abstract class AbstractTransactionRequestValidator {
  private final GetQWeekQuery qWeekQuery;
  private final TransactionLoadPort transactionLoadPort;
  private final BalanceLoadPort balanceLoadPort;

  protected void checkIfModificationAllowed(
      final LocalDate balanceLatestCalculatedDate,
      final Transaction fromDb,
      final ViolationsCollector violationCollector) {
    final var transactionDate = fromDb.getDate();
    final var transactionId = fromDb.getId();
    if (transactionDate.isBefore(balanceLatestCalculatedDate)
        || transactionDate.equals(balanceLatestCalculatedDate)) {
      violationCollector.collect(
          format(
              "Any modifications for the Transaction with id=%d is prohibited. Transaction is already calculated in Balance",
              transactionId));
    }
  }

  protected void checkExistence(
      final Long id, final Transaction fromDb, final ViolationsCollector violationCollector) {
    if (fromDb == null) {
      violationCollector.collect("Modification of Transaction failed. No Record with id = " + id);
    }
  }
}
