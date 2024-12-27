package ee.qrental.invoice.spring.config;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.invoice.api.in.request.InvoiceCalculationAddRequest;
import ee.qrental.invoice.api.out.InvoiceLoadPort;
import ee.qrental.invoice.core.validator.InvoiceRequestAddUpdateDeleteValidator;
import ee.qrental.invoice.core.validator.InvoiceCalculationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceValidatorConfig {

  @Bean
  InvoiceRequestAddUpdateDeleteValidator getInvoiceRequestAddUpdateDeleteValidator(
      final InvoiceLoadPort invoiceLoadPort, final GetQWeekQuery qWeekQuery) {
    return new InvoiceRequestAddUpdateDeleteValidator(invoiceLoadPort, qWeekQuery);
  }

  @Bean
  AddRequestValidator<InvoiceCalculationAddRequest> getInvoiceCalculationAddRequestValidator(
      final GetQWeekQuery qWeekQuery) {
    return new InvoiceCalculationAddRequestValidator(qWeekQuery);
  }
}
