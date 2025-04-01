package ee.qrent.invoice.spring.config;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.billing.driver.api.in.query.GetDriverQuery;
import ee.qrent.billing.driver.api.in.query.GetFirmLinkQuery;
import ee.qrent.email.api.in.usecase.EmailSendUseCase;
import ee.qrent.billing.firm.api.in.query.GetFirmQuery;
import ee.qrent.invoice.api.in.query.GetInvoiceQuery;
import ee.qrent.invoice.api.in.request.InvoiceAddRequest;
import ee.qrent.invoice.api.in.request.InvoiceCalculationAddRequest;
import ee.qrent.invoice.api.in.usecase.InvoicePdfUseCase;
import ee.qrent.invoice.api.in.usecase.InvoiceSendByEmailUseCase;
import ee.qrent.invoice.api.out.*;
import ee.qrent.invoice.core.mapper.*;
import ee.qrent.invoice.core.service.*;
import ee.qrent.invoice.core.service.pdf.InvoiceToPdfConverter;
import ee.qrent.invoice.core.service.pdf.InvoiceToPdfModelMapper;
import ee.qrent.transaction.api.in.query.GetTransactionQuery;
import ee.qrent.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrent.transaction.api.in.query.type.GetTransactionTypeQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class InvoiceServiceConfig {

  @Bean
  GetInvoiceQuery getInvoiceQueryService(
      final InvoiceLoadPort loadPort,
      final InvoiceResponseMapper mapper,
      final InvoiceUpdateRequestMapper updateRequestMapper) {
    return new InvoiceQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  InvoiceUseCaseService getInvoiceUseCaseService(
      final InvoiceAddPort addPort,
      final InvoiceUpdatePort updatePort,
      final InvoiceDeletePort deletePort,
      final InvoiceLoadPort loadPort,
      final InvoiceAddRequestMapper addRequestMapper,
      final InvoiceUpdateRequestMapper updateRequestMapper,
      final AddRequestValidator<InvoiceAddRequest> addRequestValidator) {
    return new InvoiceUseCaseService(
        addPort,
        updatePort,
        deletePort,
        loadPort,
        addRequestMapper,
        updateRequestMapper,
        addRequestValidator);
  }

  @Bean
  InvoiceToPdfConverter getInvoiceToPdfConverter() {
    return new InvoiceToPdfConverter();
  }

  @Bean
  InvoiceToPdfModelMapper getInvoiceToPdfModelMapper(
      final InvoiceLoadPort loadPort, final GetQWeekQuery qWeekQuery) {
    return new InvoiceToPdfModelMapper(loadPort, qWeekQuery);
  }

  @Bean
  InvoiceCalculationQueryService getInvoiceCalculationQueryService(
      final GetQWeekQuery qWeekQuery,
      final InvoiceCalculationLoadPort loadPort,
      final InvoiceCalculationResponseMapper responseMapper) {
    return new InvoiceCalculationQueryService(qWeekQuery, loadPort, responseMapper);
  }

  @Bean
  InvoiceCalculationService getInvoiceCalculationService(
      final GetQWeekQuery qWeekQuery,
      final GetDriverQuery driverQuery,
      final GetFirmQuery firmQuery,
      final GetBalanceQuery balanceQuery,
      final GetTransactionQuery transactionQuery,
      final GetTransactionTypeQuery transactionTypeQuery,
      final GetFirmLinkQuery firmLinkQuery,
      final EmailSendUseCase emailSendUseCase,
      final InvoiceCalculationLoadPort loadPort,
      final InvoiceCalculationAddRequestMapper addRequestMapper,
      final AddRequestValidator<InvoiceCalculationAddRequest> addRequestValidator,
      final InvoiceCalculationAddPort invoiceCalculationAddPort,
      final InvoiceToPdfConverter invoiceToPdfConverter,
      final InvoiceToPdfModelMapper invoiceToPdfModelMapper) {
    return new InvoiceCalculationService(
        qWeekQuery,
        driverQuery,
        firmQuery,
        balanceQuery,
        transactionQuery,
        transactionTypeQuery,
        firmLinkQuery,
        emailSendUseCase,
        loadPort,
        addRequestMapper,
        addRequestValidator,
        invoiceCalculationAddPort,
        invoiceToPdfConverter,
        invoiceToPdfModelMapper);
  }

  @Bean
  InvoiceSendByEmailUseCase getInvoiceSendByEmailUseCase(
      final EmailSendUseCase emailSendUseCase,
      final InvoiceLoadPort invoiceLoadPort,
      final GetDriverQuery driverQuery,
      InvoicePdfUseCase invoicePdfUseCase) {
    return new InvoiceSendByEmailService(
        emailSendUseCase, invoiceLoadPort, invoicePdfUseCase, driverQuery);
  }

  @Bean
  InvoicePdfUseCase getInvoicePdfUseCase(
      final InvoiceLoadPort loadPort,
      final InvoiceToPdfConverter converter,
      final InvoiceToPdfModelMapper mapper) {
    return new InvoicePdfUseCaseImpl(loadPort, converter, mapper);
  }
}
