package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.repository.CallSignRepository;
import ee.qrent.driver.repository.impl.CallSignRepositoryImpl;
import ee.qrent.driver.repository.spring.CallSignSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignRepositoryConfig {

  @Bean
  CallSignRepository getCallSignRepository(
      final CallSignSpringDataRepository springDataRepository) {
    return new CallSignRepositoryImpl(springDataRepository);
  }
}
