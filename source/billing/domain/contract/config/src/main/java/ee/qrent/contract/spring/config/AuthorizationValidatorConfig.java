package ee.qrent.contract.spring.config;

import ee.qrent.contract.api.out.AuthorizationLoadPort;
import ee.qrent.contract.core.validator.AuthorizationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationValidatorConfig {

  @Bean
  AuthorizationAddRequestValidator getAuthorizationAddRequestValidator(
      final AuthorizationLoadPort loadPort) {
    return new AuthorizationAddRequestValidator(loadPort);
  }

}
