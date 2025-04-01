package ee.qrent.user.spring.config;

import ee.qrent.user.adapter.repository.RoleRepository;
import ee.qrent.user.repository.impl.RoleRepositoryImpl;
import ee.qrent.user.repository.spring.RoleSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleRepositoryConfig {

  @Bean
  RoleRepository getRoleRepository(final RoleSpringDataRepository springDataRepository) {
    return new RoleRepositoryImpl(springDataRepository);
  }
}
