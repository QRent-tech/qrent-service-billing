package ee.qrent.invoice.repository.impl;

import ee.qrent.invoice.adapter.repository.InvoiceCalculationResultRepository;
import ee.qrent.billing.invoice.persistence.entity.jakarta.InvoiceCalculationResultJakartaEntity;
import ee.qrent.invoice.repository.spring.InvoiceCalculationResultSpringDataRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceCalculationResultRepositoryImpl implements InvoiceCalculationResultRepository {

  private final InvoiceCalculationResultSpringDataRepository springDataRepository;

  @Override
  public InvoiceCalculationResultJakartaEntity save(
      final InvoiceCalculationResultJakartaEntity entity) {
    return springDataRepository.save(entity);
  }
}
