package ee.qrent.contract.spring.config;

import ee.qrent.contract.adapter.adapter.AuthorizationLoadAdapter;
import ee.qrent.contract.adapter.adapter.AuthorizationPersistenceAdapter;
import ee.qrent.contract.adapter.mapper.AuthorizationAdapterMapper;
import ee.qrent.contract.adapter.repository.AuthorizationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationAdapterConfig {
  @Bean
  AuthorizationAdapterMapper getCAuthorizationBoltAdapterMapper() {
    return new AuthorizationAdapterMapper();
  }

  @Bean
  AuthorizationLoadAdapter getAuthorizationBoltLoadAdapter(
          final AuthorizationRepository repository, final AuthorizationAdapterMapper mapper) {
    return new AuthorizationLoadAdapter(repository, mapper);
  }

  @Bean
  AuthorizationPersistenceAdapter getAuthorizationBoltPersistenceAdapter(
          final AuthorizationRepository repository, final AuthorizationAdapterMapper mapper) {
    return new AuthorizationPersistenceAdapter(repository, mapper);
  }
}
