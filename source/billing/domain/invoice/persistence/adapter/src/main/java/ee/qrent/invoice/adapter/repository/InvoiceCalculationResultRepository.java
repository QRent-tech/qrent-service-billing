package ee.qrent.invoice.adapter.repository;

import ee.qrent.invoice.entity.jakarta.InvoiceCalculationResultJakartaEntity;

public interface InvoiceCalculationResultRepository {
  InvoiceCalculationResultJakartaEntity save(final InvoiceCalculationResultJakartaEntity entity);
}
