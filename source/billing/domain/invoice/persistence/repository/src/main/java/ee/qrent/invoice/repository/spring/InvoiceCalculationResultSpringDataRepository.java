package ee.qrent.invoice.repository.spring;

import ee.qrent.billing.invoice.persistence.entity.jakarta.InvoiceCalculationResultJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceCalculationResultSpringDataRepository
    extends JpaRepository<InvoiceCalculationResultJakartaEntity, Long> {}
