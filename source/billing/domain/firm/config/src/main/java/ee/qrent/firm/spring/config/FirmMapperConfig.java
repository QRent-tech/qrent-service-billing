package ee.qrent.firm.spring.config;

import ee.qrent.firm.core.mapper.FirmAddRequestMapper;
import ee.qrent.firm.core.mapper.FirmResponseMapper;
import ee.qrent.firm.core.mapper.FirmUpdateRequestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmMapperConfig {
  @Bean
  FirmAddRequestMapper getFirmAddRequestMapper() {
    return new FirmAddRequestMapper();
  }

  @Bean
  FirmResponseMapper getFirmResponseMapper() {
    return new FirmResponseMapper();
  }

  @Bean
  FirmUpdateRequestMapper getFirmUpdateRequestMapper() {
    return new FirmUpdateRequestMapper();
  }
}
