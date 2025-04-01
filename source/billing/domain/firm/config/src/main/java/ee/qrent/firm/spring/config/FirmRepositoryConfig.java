package ee.qrent.firm.spring.config;

import ee.qrent.firm.adapter.repository.FirmRepository;
import ee.qrent.firm.repository.impl.FirmRepositoryImpl;
import ee.qrent.firm.repository.spring.FirmSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmRepositoryConfig {

  @Bean
  FirmRepository getFirmRepository(final FirmSpringDataRepository springDataRepository) {
    return new FirmRepositoryImpl(springDataRepository);
  }
}
