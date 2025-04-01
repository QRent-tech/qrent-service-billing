package ee.qrent.contract.spring.config;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.CloseRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.contract.api.in.request.ContractAddRequest;
import ee.qrent.contract.api.in.request.ContractCloseRequest;
import ee.qrent.contract.api.in.request.ContractUpdateRequest;
import ee.qrent.contract.api.out.ContractLoadPort;
import ee.qrent.contract.core.validator.ContractAddRequestValidator;
import ee.qrent.contract.core.validator.ContractCloseRequestValidator;
import ee.qrent.contract.core.validator.ContractUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractValidatorConfig {

  @Bean
  AddRequestValidator<ContractAddRequest> getContractAddRequestValidator(
      final ContractLoadPort contractLoadPort) {
    return new ContractAddRequestValidator(contractLoadPort);
  }

  @Bean
  UpdateRequestValidator<ContractUpdateRequest> getContractUpdateRequestValidator(
      final ContractLoadPort contractLoadPort) {
    return new ContractUpdateRequestValidator(contractLoadPort);
  }

  @Bean
  CloseRequestValidator<ContractCloseRequest> getContractCloseRequestValidator(
      final ContractLoadPort contractLoadPort) {
    return new ContractCloseRequestValidator(contractLoadPort);
  }
}
