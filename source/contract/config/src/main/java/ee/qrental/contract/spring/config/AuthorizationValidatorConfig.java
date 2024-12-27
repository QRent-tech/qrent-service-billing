package ee.qrental.contract.spring.config;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.contract.api.in.request.AuthorizationAddRequest;
import ee.qrental.contract.api.out.AuthorizationLoadPort;
import ee.qrental.contract.core.validator.AuthorizationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationValidatorConfig {

  @Bean
  AddRequestValidator<AuthorizationAddRequest> getAuthorizationAddRequestValidator(
      final AuthorizationLoadPort loadPort) {
    return new AuthorizationAddRequestValidator(loadPort);
  }
}
