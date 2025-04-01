package ee.qrent.invoice.repository.impl;

import ee.qrent.invoice.adapter.repository.InvoiceTransactionRepository;
import ee.qrent.invoice.entity.jakarta.InvoiceTransactionJakartaEntity;
import ee.qrent.invoice.repository.spring.InvoiceTransactionSpringDataRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceTransactionRepositoryImpl implements InvoiceTransactionRepository {

  private final InvoiceTransactionSpringDataRepository springDataRepository;

  @Override
  public InvoiceTransactionJakartaEntity save(final InvoiceTransactionJakartaEntity entity) {
    return springDataRepository.save(entity);
  }
}
