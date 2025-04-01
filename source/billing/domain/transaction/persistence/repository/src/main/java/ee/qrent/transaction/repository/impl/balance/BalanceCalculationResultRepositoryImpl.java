package ee.qrent.transaction.repository.impl.balance;

import ee.qrent.transaction.adapter.repository.balance.BalanceCalculationResultRepository;
import ee.qrent.transaction.entity.jakarta.balance.BalanceCalculationResultJakartaEntity;
import ee.qrent.transaction.repository.spring.balance.BalanceCalculationResultSpringDataRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BalanceCalculationResultRepositoryImpl implements BalanceCalculationResultRepository {

  private final BalanceCalculationResultSpringDataRepository springDataRepository;

  @Override
  public BalanceCalculationResultJakartaEntity save(
      final BalanceCalculationResultJakartaEntity entity) {
    return springDataRepository.save(entity);
  }
}
