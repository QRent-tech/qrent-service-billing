package ee.qrent.transaction.adapter.repository.balance;


import ee.qrent.transaction.entity.jakarta.balance.BalanceCalculationResultJakartaEntity;

public interface BalanceCalculationResultRepository {
  BalanceCalculationResultJakartaEntity save(final BalanceCalculationResultJakartaEntity entity);
}
