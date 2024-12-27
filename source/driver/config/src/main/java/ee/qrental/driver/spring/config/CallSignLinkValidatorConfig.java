package ee.qrental.driver.spring.config;

import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.validator.CallSignLinkAddUpdateDeleteRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignLinkValidatorConfig {

  @Bean
  CallSignLinkAddUpdateDeleteRequestValidator getCallSignLinkRequestValidator(
      final CallSignLinkLoadPort loadPort, final CallSignLoadPort callSignLoadPort) {
    return new CallSignLinkAddUpdateDeleteRequestValidator(loadPort, callSignLoadPort);
  }
}
