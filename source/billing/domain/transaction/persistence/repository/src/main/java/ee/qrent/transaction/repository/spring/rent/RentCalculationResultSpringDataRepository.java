package ee.qrent.transaction.repository.spring.rent;

import ee.qrent.transaction.entity.jakarta.rent.RentCalculationResultJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCalculationResultSpringDataRepository
    extends JpaRepository<RentCalculationResultJakartaEntity, Long> {}
