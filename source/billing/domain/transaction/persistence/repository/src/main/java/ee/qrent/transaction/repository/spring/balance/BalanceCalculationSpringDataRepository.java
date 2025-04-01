package ee.qrent.transaction.repository.spring.balance;

import ee.qrent.transaction.entity.jakarta.balance.BalanceCalculationJakartaEntity;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BalanceCalculationSpringDataRepository
    extends JpaRepository<BalanceCalculationJakartaEntity, Long> {

  @Query(
      value = "select end_date from balance_calculation order by end_date desc limit 1",
      nativeQuery = true)
  LocalDate getLastCalculationDate();
}
