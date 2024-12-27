package ee.qrental.contract.spring.config;

import ee.qrental.common.core.validation.CloseRequestValidator;
import ee.qrental.contract.api.in.request.ContractCloseRequest;
import ee.qrental.contract.api.out.ContractLoadPort;
import ee.qrental.contract.core.validator.ContractAddUpdateRequestValidator;
import ee.qrental.contract.core.validator.ContractCloseRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractValidatorConfig {

  @Bean
  ContractAddUpdateRequestValidator getContractAddUpdateRequestValidator(
      final ContractLoadPort contractLoadPort) {
    return new ContractAddUpdateRequestValidator(contractLoadPort);
  }

  @Bean
  CloseRequestValidator<ContractCloseRequest> getContractCloseRequestValidator(
      final ContractLoadPort contractLoadPort) {
    return new ContractCloseRequestValidator(contractLoadPort);
  }
}
