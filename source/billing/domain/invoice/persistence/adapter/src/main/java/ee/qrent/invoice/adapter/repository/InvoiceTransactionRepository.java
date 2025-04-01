package ee.qrent.invoice.adapter.repository;

import ee.qrent.invoice.entity.jakarta.InvoiceTransactionJakartaEntity;

public interface InvoiceTransactionRepository {
  InvoiceTransactionJakartaEntity save(final InvoiceTransactionJakartaEntity entity);
}
