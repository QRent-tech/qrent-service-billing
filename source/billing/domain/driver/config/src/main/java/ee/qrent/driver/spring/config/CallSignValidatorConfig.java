package ee.qrent.driver.spring.config;

import ee.qrent.common.in.validation.AttributeChecker;
import ee.qrent.driver.api.out.*;
import ee.qrent.driver.core.validator.CallSignRequestValidator;
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
