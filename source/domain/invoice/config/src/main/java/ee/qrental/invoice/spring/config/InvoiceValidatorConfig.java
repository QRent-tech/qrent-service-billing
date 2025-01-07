package ee.qrental.invoice.spring.config;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.invoice.api.out.InvoiceCalculationLoadPort;
import ee.qrental.invoice.api.out.InvoiceLoadPort;
import ee.qrental.invoice.core.validator.InvoiceAddRequestValidator;
import ee.qrental.invoice.core.validator.InvoiceCalculationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceValidatorConfig {

  @Bean
  InvoiceAddRequestValidator getInvoiceBusinessRuleValidator(
      final InvoiceLoadPort invoiceLoadPort) {
    return new InvoiceAddRequestValidator(invoiceLoadPort);
  }

  @Bean
  InvoiceCalculationAddRequestValidator getInvoiceCalculationBusinessRuleValidator(
      final GetQWeekQuery qWeekQuery, final InvoiceCalculationLoadPort loadPort) {
    return new InvoiceCalculationAddRequestValidator(qWeekQuery, loadPort);
  }
}
