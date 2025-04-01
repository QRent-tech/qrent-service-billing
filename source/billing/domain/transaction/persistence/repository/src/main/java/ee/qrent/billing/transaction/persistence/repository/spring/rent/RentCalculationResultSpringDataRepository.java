package ee.qrent.billing.transaction.persistence.repository.spring.rent;

import ee.qrent.billing.transaction.entity.jakarta.rent.RentCalculationResultJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCalculationResultSpringDataRepository
    extends JpaRepository<RentCalculationResultJakartaEntity, Long> {}
