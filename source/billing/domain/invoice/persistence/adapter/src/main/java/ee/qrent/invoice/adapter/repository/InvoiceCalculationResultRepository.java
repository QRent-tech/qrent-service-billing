package ee.qrent.invoice.adapter.repository;

import ee.qrent.billing.invoice.persistence.entity.jakarta.InvoiceCalculationResultJakartaEntity;

public interface InvoiceCalculationResultRepository {
  InvoiceCalculationResultJakartaEntity save(final InvoiceCalculationResultJakartaEntity entity);
}
