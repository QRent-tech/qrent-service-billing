package ee.qrental.driver.spring.config;

import ee.qrent.common.in.validation.AttributeChecker;
import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.validator.CallSignRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignValidatorConfig {

  @Bean
  CallSignRequestValidator getCallSignRequestValidator(
      final CallSignLoadPort callSignLoadPort,
      final CallSignLinkLoadPort callSignLinkLoadPort,
      final AttributeChecker attributeChecker) {

    return new CallSignRequestValidator(callSignLoadPort, callSignLinkLoadPort, attributeChecker);
  }
}
