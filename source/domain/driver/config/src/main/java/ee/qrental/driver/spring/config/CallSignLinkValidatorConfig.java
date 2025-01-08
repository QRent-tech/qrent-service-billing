package ee.qrental.driver.spring.config;

import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.validator.CallSignLinkRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignLinkValidatorConfig {

  @Bean
  CallSignLinkRequestValidator getCallSignLinkRequestValidator(
      final CallSignLinkLoadPort loadPort) {
    return new CallSignLinkRequestValidator(loadPort);
  }
}
