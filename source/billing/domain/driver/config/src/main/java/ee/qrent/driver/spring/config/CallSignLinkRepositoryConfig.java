package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.repository.CallSignLinkRepository;
import ee.qrent.driver.repository.impl.CallSignLinkRepositoryImpl;
import ee.qrent.driver.repository.spring.CallSignLinkSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignLinkRepositoryConfig {

  @Bean
  CallSignLinkRepository getCallSignLinkRepository(
      final CallSignLinkSpringDataRepository springDataRepository) {
    return new CallSignLinkRepositoryImpl(springDataRepository);
  }
}
