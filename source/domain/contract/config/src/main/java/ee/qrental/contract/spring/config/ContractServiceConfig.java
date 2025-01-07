package ee.qrental.contract.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.request.ContractAddRequest;
import ee.qrental.contract.api.in.request.ContractUpdateRequest;
import ee.qrental.contract.api.in.usecase.ContractCloseUseCase;
import ee.qrental.contract.api.in.usecase.ContractPdfUseCase;
import ee.qrental.contract.api.in.usecase.ContractSendByEmailUseCase;
import ee.qrental.contract.api.out.*;
import ee.qrental.contract.core.mapper.*;
import ee.qrental.contract.core.service.*;
import ee.qrental.contract.core.service.pdf.ContractToPdfConverter;
import ee.qrental.contract.core.service.pdf.ContractToPdfModelMapper;
import ee.qrental.contract.core.service.ContractEndDateCalculator;
import ee.qrental.contract.core.validator.ContractDeleteRequestValidator;
import ee.qrental.contract.core.validator.ContractCloseRequestValidator;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.email.api.in.usecase.EmailSendUseCase;
import ee.qrental.insurance.api.in.query.GetInsuranceCaseQuery;
import ee.qrental.insurance.api.in.usecase.InsuranceCaseCloseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractServiceConfig {

  @Bean
  GetContractQuery getContractQueryService(
      final ContractEndDateCalculator endDateCalculator,
      final ContractLoadPort loadPort,
      final ContractResponseMapper mapper,
      final ContractUpdateRequestMapper updateRequestMapper,
      final QDateTime qDateTime) {

    return new ContractQueryService(
        endDateCalculator, loadPort, mapper, updateRequestMapper, qDateTime);
  }

  @Bean
  ContractEndDateCalculator getContractEndDateCalculator(final QDateTime qDateTime) {
    return new ContractEndDateCalculator(qDateTime);
  }

  @Bean
  ContractUseCaseService getContractUseCaseService(
      final ContractAddPort addPort,
      final ContractUpdatePort updatePort,
      final ContractLoadPort loadPort,
      final ContractAddRequestMapper addRequestMapper,
      final AddRequestValidator<ContractAddRequest> addRequestValidator,
      final UpdateRequestValidator<ContractUpdateRequest> updateRequestValidator,
      final QDateTime qDateTime) {

    return new ContractUseCaseService(
        addPort,
        updatePort,
        loadPort,
        addRequestMapper,
        addRequestValidator,
        updateRequestValidator,
        qDateTime);
  }

  @Bean
  ContractCloseUseCase getContractCloseUseCaseService(
      final ContractLoadPort contractLoadPort,
      final ContractUpdatePort contractUpdatePort,
      final GetDriverQuery driverQuery,
      final GetInsuranceCaseQuery insuranceCaseQuery,
      final InsuranceCaseCloseUseCase insuranceCaseCloseUseCase,
      final ContractCloseRequestValidator closeRuleValidator,
      final QDateTime qDateTime) {

    return new ContractCloseUseCaseService(
        contractLoadPort,
        contractUpdatePort,
        driverQuery,
        insuranceCaseQuery,
        insuranceCaseCloseUseCase,
        closeRuleValidator,
        qDateTime);
  }

  @Bean
  ContractToPdfConverter getContractToPdfConverter() {

    return new ContractToPdfConverter();
  }

  @Bean
  ContractToPdfModelMapper getContractToPdfModelMapper() {

    return new ContractToPdfModelMapper();
  }

  @Bean
  ContractSendByEmailUseCase getContractSendByEmailUseCase(
      final EmailSendUseCase emailSendUseCase,
      final ContractLoadPort invoiceLoadPort,
      final ContractPdfUseCase invoicePdfUseCase) {

    return new ContractSendByEmailService(emailSendUseCase, invoiceLoadPort, invoicePdfUseCase);
  }

  @Bean
  ContractPdfUseCase getContractPdfUseCase(
      final ContractLoadPort loadPort,
      final ContractToPdfConverter converter,
      final ContractToPdfModelMapper mapper) {

    return new ContractPdfUseCaseImpl(loadPort, converter, mapper);
  }
}
