package ee.qrent.insurance.adapter.repository;

import ee.qrent.insurance.entity.jakarta.InsuranceCaseBalanceTransactionJakartaEntity;

public interface InsuranceCaseBalanceTransactionRepository {
    InsuranceCaseBalanceTransactionJakartaEntity save(final InsuranceCaseBalanceTransactionJakartaEntity entity);
}
