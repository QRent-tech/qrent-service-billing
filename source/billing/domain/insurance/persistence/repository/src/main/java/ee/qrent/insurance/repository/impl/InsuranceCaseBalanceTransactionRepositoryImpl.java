package ee.qrent.insurance.repository.impl;

import ee.qrent.insurance.adapter.repository.InsuranceCaseBalanceTransactionRepository;
import ee.qrent.insurance.entity.jakarta.InsuranceCaseBalanceTransactionJakartaEntity;
import ee.qrent.insurance.repository.spring.InsuranceCaseBalanceTransactionSpringDataRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceCaseBalanceTransactionRepositoryImpl
    implements InsuranceCaseBalanceTransactionRepository {
  private final InsuranceCaseBalanceTransactionSpringDataRepository springDataRepository;

  @Override
  public InsuranceCaseBalanceTransactionJakartaEntity save(
      final InsuranceCaseBalanceTransactionJakartaEntity entity) {
    return springDataRepository.save(entity);
  }
}
