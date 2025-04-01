package ee.qrental.user.spring.config;

import ee.qrental.user.api.out.RoleLoadPort;
import ee.qrental.user.api.out.UserAccountLoadPort;
import ee.qrental.user.core.validator.RoleRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleValidatorConfig {

  @Bean
  RoleRequestValidator getRoleRequestValidator(
          final RoleLoadPort loadPort, 
          final UserAccountLoadPort userAccountLoadPort) {
    return new RoleRequestValidator(loadPort, userAccountLoadPort);
  }
}
