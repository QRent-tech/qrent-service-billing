package ee.qrent.transaction.repository.spring.balance;

import ee.qrent.transaction.entity.jakarta.balance.BalanceCalculationResultJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceCalculationResultSpringDataRepository
    extends JpaRepository<BalanceCalculationResultJakartaEntity, Long> {}
