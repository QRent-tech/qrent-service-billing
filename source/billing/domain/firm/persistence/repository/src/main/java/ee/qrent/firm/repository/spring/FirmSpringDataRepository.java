package ee.qrent.firm.repository.spring;

import ee.qrent.firm.entity.jakarta.FirmJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmSpringDataRepository
        extends JpaRepository<FirmJakartaEntity, Long> {
}
