package ee.qrent.invoice.repository.spring;

import ee.qrent.billing.invoice.persistence.entity.jakarta.InvoiceTransactionJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceTransactionSpringDataRepository
    extends JpaRepository<InvoiceTransactionJakartaEntity, Long> {}
