package ee.qrent.user.spring.config;

import ee.qrent.user.adapter.repository.UserAccountRepository;
import ee.qrent.user.repository.impl.UserAccountRepositoryImpl;
import ee.qrent.user.repository.spring.UserAccountSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAccountRepositoryConfig {

  @Bean
  UserAccountRepository getUserAccountRepository(final UserAccountSpringDataRepository springDataRepository) {
    return new UserAccountRepositoryImpl(springDataRepository);
  }
}
