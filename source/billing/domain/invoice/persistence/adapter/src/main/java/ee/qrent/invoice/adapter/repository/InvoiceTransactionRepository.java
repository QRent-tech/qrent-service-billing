package ee.qrent.invoice.adapter.repository;

import ee.qrent.billing.invoice.persistence.entity.jakarta.InvoiceTransactionJakartaEntity;

public interface InvoiceTransactionRepository {
  InvoiceTransactionJakartaEntity save(final InvoiceTransactionJakartaEntity entity);
}
