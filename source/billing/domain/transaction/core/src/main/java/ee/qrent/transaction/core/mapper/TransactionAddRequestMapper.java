package ee.qrent.transaction.core.mapper;

import ee.qrent.common.in.mapper.AddRequestMapper;
import ee.qrent.transaction.api.in.request.TransactionAddRequest;
import ee.qrent.transaction.api.out.type.TransactionTypeLoadPort;
import ee.qrent.transaction.domain.Transaction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionAddRequestMapper
    implements AddRequestMapper<TransactionAddRequest, Transaction> {

  private final TransactionTypeLoadPort transactionTypeLoadPort;

  @Override
  public Transaction toDomain(TransactionAddRequest request) {
    final var transactionType = transactionTypeLoadPort.loadById(request.getTransactionTypeId());

    return Transaction.builder()
        .id(null)
        .type(transactionType)
        .amount(request.getAmount())
        .driverId(request.getDriverId())
        .date(request.getDate())
        .comment(request.getComment())
        .build();
  }
}
