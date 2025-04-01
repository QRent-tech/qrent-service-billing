package ee.qrent.invoice.spring.config;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.invoice.api.in.request.InvoiceAddRequest;
import ee.qrent.invoice.api.in.request.InvoiceCalculationAddRequest;
import ee.qrent.invoice.api.out.InvoiceCalculationLoadPort;
import ee.qrent.invoice.api.out.InvoiceLoadPort;
import ee.qrent.invoice.core.validator.InvoiceAddRequestValidator;
import ee.qrent.invoice.core.validator.InvoiceCalculationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceValidatorConfig {

  @Bean
  AddRequestValidator<InvoiceAddRequest> getInvoiceAddRequestValidator(
      final InvoiceLoadPort invoiceLoadPort) {
    return new InvoiceAddRequestValidator(invoiceLoadPort);
  }

  @Bean
  AddRequestValidator<InvoiceCalculationAddRequest> getInvoiceCalculationAddRequestValidator(
      final GetQWeekQuery qWeekQuery, final InvoiceCalculationLoadPort loadPort) {
    return new InvoiceCalculationAddRequestValidator(qWeekQuery, loadPort);
  }
}
