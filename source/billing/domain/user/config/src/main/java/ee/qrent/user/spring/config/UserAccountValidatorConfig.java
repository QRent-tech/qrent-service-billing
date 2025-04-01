package ee.qrent.user.spring.config;

import ee.qrent.user.api.out.UserAccountLoadPort;
import ee.qrent.user.core.validator.UserAccountRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAccountValidatorConfig {

  @Bean
  UserAccountRequestValidator getUserAccountRequestValidator(final UserAccountLoadPort loadPort) {
    return new UserAccountRequestValidator(loadPort);
  }
}
