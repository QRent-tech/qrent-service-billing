package ee.qrent.contract.spring.config;

import ee.qrent.contract.adapter.repository.AuthorizationRepository;
import ee.qrent.contract.repository.impl.AuthorizationRepositoryImpl;
import ee.qrent.contract.repository.spring.AuthorizationSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationRepositoryConfig {

  @Bean
  AuthorizationRepository getAuthorizationRepository(
      final AuthorizationSpringDataRepository springDataRepository) {
    return new AuthorizationRepositoryImpl(springDataRepository) {};
  }
}
