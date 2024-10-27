package ee.qrental.contract.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.usecase.ContractPdfUseCase;
import ee.qrental.contract.api.in.usecase.ContractSendByEmailUseCase;
import ee.qrental.contract.api.out.*;
import ee.qrental.contract.core.mapper.*;
import ee.qrental.contract.core.service.*;
import ee.qrental.contract.core.service.pdf.ContractToPdfConverter;
import ee.qrental.contract.core.service.pdf.ContractToPdfModelMapper;
import ee.qrental.contract.core.validator.ContractBusinessRuleValidator;
import ee.qrental.email.api.in.usecase.EmailSendUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractServiceConfig {

  @Bean
  GetContractQuery getContractQueryService(
      final ContractLoadPort loadPort,
      final ContractResponseMapper mapper,
      final ContractUpdateRequestMapper updateRequestMapper,
      final QDateTime qDateTime) {
    return new ContractQueryService(loadPort, mapper, updateRequestMapper, qDateTime);
  }

  @Bean
  ContractUseCaseService getContractUseCaseService(
      final ContractAddPort addPort,
      final ContractUpdatePort updatePort,
      final ContractDeletePort deletePort,
      final ContractLoadPort loadPort,
      final ContractAddRequestMapper addRequestMapper,
      final ContractUpdateRequestMapper updateRequestMapper,
      final ContractBusinessRuleValidator businessRuleValidator) {
    return new ContractUseCaseService(
        addPort,
        updatePort,
        deletePort,
        loadPort,
        addRequestMapper,
        updateRequestMapper,
        businessRuleValidator);
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
