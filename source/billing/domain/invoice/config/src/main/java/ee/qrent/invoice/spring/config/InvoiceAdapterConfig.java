package ee.qrent.invoice.spring.config;

import ee.qrent.invoice.adapter.adapter.InvoiceCalculationLoadAdapter;
import ee.qrent.invoice.adapter.adapter.InvoiceCalculationPersistenceAdapter;
import ee.qrent.invoice.adapter.adapter.InvoiceLoadAdapter;
import ee.qrent.invoice.adapter.adapter.InvoicePersistenceAdapter;
import ee.qrent.invoice.adapter.mapper.InvoiceAdapterMapper;
import ee.qrent.invoice.adapter.mapper.InvoiceCalculationAdapterMapper;
import ee.qrent.invoice.adapter.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceAdapterConfig {
  @Bean
  InvoiceAdapterMapper getInvoiceAdapterMapper() {
    return new InvoiceAdapterMapper();
  }

  @Bean
  InvoiceLoadAdapter getInvoiceLoadAdapter(
      final InvoiceRepository repository, final InvoiceAdapterMapper mapper) {
    return new InvoiceLoadAdapter(repository, mapper);
  }

  @Bean
  InvoicePersistenceAdapter getInvoicePersistenceAdapter(
      final InvoiceRepository repository,
      final InvoiceItemRepository itemRepository,
      final InvoiceAdapterMapper mapper) {
    return new InvoicePersistenceAdapter(repository, itemRepository, mapper);
  }

  @Bean
  InvoiceCalculationPersistenceAdapter getInvoiceCalculationPersistenceAdapter(
      final InvoiceCalculationRepository invoiceCalculationRepository,
      final InvoiceCalculationResultRepository invoiceCalculationResultRepository,
      final InvoiceRepository invoiceRepository,
      final InvoiceItemRepository invoiceItemRepository,
      final InvoiceTransactionRepository invoiceTransactionRepository,
      final InvoiceAdapterMapper invoiceMapper) {

    return new InvoiceCalculationPersistenceAdapter(
        invoiceCalculationRepository,
        invoiceCalculationResultRepository,
        invoiceRepository,
        invoiceItemRepository,
        invoiceTransactionRepository,
        invoiceMapper);
  }

  @Bean
  InvoiceCalculationLoadAdapter getInvoiceCalculationLoadAdapter(
      final InvoiceCalculationRepository repository, final InvoiceCalculationAdapterMapper mapper) {
    return new InvoiceCalculationLoadAdapter(repository, mapper);
  }
}
