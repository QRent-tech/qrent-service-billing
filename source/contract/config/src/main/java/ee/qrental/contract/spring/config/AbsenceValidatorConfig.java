package ee.qrental.contract.spring.config;

import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.core.validator.AbsenceAddBusinessRuleValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceValidatorConfig {

  @Bean
  AbsenceAddBusinessRuleValidator getAbsenceAddBusinessRuleValidator(
      final AbsenceLoadPort loadPort) {
    return new AbsenceAddBusinessRuleValidator(loadPort);
  }
}
