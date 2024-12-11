package ee.qrental.car.spring.config;

import ee.qrental.car.api.out.CarLinkLoadPort;
import ee.qrental.car.core.validator.CarLinkAddBusinessRuleValidator;
import ee.qrental.car.core.validator.CarLinkUpdateBusinessRuleValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkValidatorConfig {

  @Bean
  CarLinkAddBusinessRuleValidator getCarLinkAddBusinessRuleValidator(
      final CarLinkLoadPort loadPort) {
    return new CarLinkAddBusinessRuleValidator(loadPort);
  }

  @Bean
  CarLinkUpdateBusinessRuleValidator getCarLinkUpdateBusinessRuleValidator(
      final CarLinkLoadPort loadPort) {
    return new CarLinkUpdateBusinessRuleValidator(loadPort);
  }
}
