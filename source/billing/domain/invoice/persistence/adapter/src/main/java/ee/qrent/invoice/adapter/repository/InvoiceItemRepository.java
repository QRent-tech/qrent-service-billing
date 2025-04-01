package ee.qrent.invoice.adapter.repository;

import ee.qrent.invoice.entity.jakarta.InvoiceItemJakartaEntity;

public interface InvoiceItemRepository {
  InvoiceItemJakartaEntity save(final InvoiceItemJakartaEntity entity);

  void removeByInvoiceId(final Long invoiceId);
}
