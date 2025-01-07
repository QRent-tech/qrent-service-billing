package ee.qrental.contract.spring.config;

import ee.qrental.contract.api.out.ContractLoadPort;
import ee.qrental.contract.core.validator.ContractDeleteRequestValidator;
import ee.qrental.contract.core.validator.ContractCloseRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractValidatorConfig {

  @Bean
  ContractDeleteRequestValidator getContractBusinessRuleValidator(
      final ContractLoadPort contractLoadPort) {
    return new ContractDeleteRequestValidator(contractLoadPort);
  }

  @Bean
  ContractCloseRequestValidator getContractCloseBusinessRuleValidator(
      final ContractLoadPort contractLoadPort) {
    return new ContractCloseRequestValidator(contractLoadPort);
  }
}
