package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.repository.FirmLinkRepository;
import ee.qrent.driver.repository.impl.FirmLinkRepositoryImpl;
import ee.qrent.driver.repository.spring.FirmLinkSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmLinkRepositoryConfig {

  @Bean
  FirmLinkRepository getFirmLinkRepository(
      final FirmLinkSpringDataRepository springDataRepository) {
    return new FirmLinkRepositoryImpl(springDataRepository);
  }
}
