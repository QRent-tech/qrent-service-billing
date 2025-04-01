package ee.qrent.transaction.adapter.repository.balance;


import ee.qrent.transaction.entity.jakarta.balance.BalanceTransactionJakartaEntity;

public interface BalanceTransactionRepository {
  BalanceTransactionJakartaEntity save(final BalanceTransactionJakartaEntity entity);
  boolean isTransactionCalculatedInBalance(final Long transactionId);
}
