package ee.qrental.contract.spring.config;

import ee.qrental.contract.api.out.AuthorizationLoadPort;
import ee.qrental.contract.core.validator.AuthorizationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationValidatorConfig {

  @Bean
  AuthorizationAddRequestValidator getAuthorizationAddBusinessRuleValidator(
      final AuthorizationLoadPort loadPort) {
    return new AuthorizationAddRequestValidator(loadPort);
  }

}
