package ee.qrental.transaction.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.request.TransactionDeleteRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;

public class TransactionDeleteRequestValidator extends AbstractTransactionRequestValidator
    implements DeleteRequestValidator<TransactionDeleteRequest> {

  public TransactionDeleteRequestValidator(
      GetQWeekQuery qWeekQuery,
      TransactionLoadPort transactionLoadPort,
      BalanceLoadPort balanceLoadPort) {
    super(qWeekQuery, transactionLoadPort, balanceLoadPort);
  }

  @Override
  public ViolationsCollector validate(final TransactionDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    final var latestBalance = getBalanceLoadPort().loadLatest();
    if (latestBalance == null) {

      return violationsCollector;
    }
    final var latestBalanceQWeek = getQWeekQuery().getById(latestBalance.getQWeekId());
    final var latestBalanceCalculatedDate = latestBalanceQWeek.getEnd();

    final var transactionFromDb = getTransactionLoadPort().loadById(request.getId());
    checkExistence(request.getId(), transactionFromDb, violationsCollector);
    checkIfModificationAllowed(latestBalanceCalculatedDate, transactionFromDb, violationsCollector);

    return violationsCollector;
  }



}
