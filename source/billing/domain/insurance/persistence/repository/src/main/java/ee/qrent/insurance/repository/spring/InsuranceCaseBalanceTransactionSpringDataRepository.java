package ee.qrent.insurance.repository.spring;

import ee.qrent.insurance.entity.jakarta.InsuranceCaseBalanceTransactionJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceCaseBalanceTransactionSpringDataRepository
    extends JpaRepository<InsuranceCaseBalanceTransactionJakartaEntity, Long> {}
