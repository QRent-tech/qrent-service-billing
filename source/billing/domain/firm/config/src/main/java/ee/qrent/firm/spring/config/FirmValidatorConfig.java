package ee.qrent.firm.spring.config;

import ee.qrent.firm.core.validator.FirmRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmValidatorConfig {
  @Bean
  FirmRequestValidator getFirmRequestValidator() {
    return new FirmRequestValidator();
  }
}
