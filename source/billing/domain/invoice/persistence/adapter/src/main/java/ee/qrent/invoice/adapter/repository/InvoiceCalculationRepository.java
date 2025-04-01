package ee.qrent.invoice.adapter.repository;

import ee.qrent.invoice.entity.jakarta.InvoiceCalculationJakartaEntity;

import java.util.List;

public interface InvoiceCalculationRepository {
  InvoiceCalculationJakartaEntity save(final InvoiceCalculationJakartaEntity entity);

  List<InvoiceCalculationJakartaEntity> findAll();

  InvoiceCalculationJakartaEntity getReferenceById(final Long id);

  InvoiceCalculationJakartaEntity getLastCalculation();
}
