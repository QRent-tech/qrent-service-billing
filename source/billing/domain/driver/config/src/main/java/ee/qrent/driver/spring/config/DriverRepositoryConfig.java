package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.repository.DriverRepository;
import ee.qrent.driver.repository.impl.DriverRepositoryImpl;
import ee.qrent.driver.repository.spring.DriverSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverRepositoryConfig {

  @Bean
  DriverRepository getDriverRepository(final DriverSpringDataRepository springDataRepository) {
    return new DriverRepositoryImpl(springDataRepository);
  }
}
