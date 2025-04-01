package ee.qrent.transaction.repository.spring.balance;

import ee.qrent.transaction.entity.jakarta.balance.BalanceTransactionJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceTransactionSpringDataRepository
    extends JpaRepository<BalanceTransactionJakartaEntity, Long> {
    boolean existsTransactionBalanceJakartaEntityByTransactionId(final Long transactionId);

}
