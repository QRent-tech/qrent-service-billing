package ee.qrent.user.spring.config;

import ee.qrent.user.api.out.UserAccountLoadPort;
import ee.qrent.user.core.mapper.UserAccountAddRequestMapper;
import ee.qrent.user.core.mapper.UserAccountResponseMapper;
import ee.qrent.user.core.mapper.UserAccountUpdateRequestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAccountMapperConfig {
  @Bean
  UserAccountAddRequestMapper getUserAddRequestMapper() {
    return new UserAccountAddRequestMapper();
  }

  @Bean
  UserAccountResponseMapper getUserResponseMapper() {
    return new UserAccountResponseMapper();
  }

  @Bean
  UserAccountUpdateRequestMapper getUserUpdateRequestMapper(final UserAccountLoadPort userAccountloadPort) {
    return new UserAccountUpdateRequestMapper(userAccountloadPort);
  }
}
