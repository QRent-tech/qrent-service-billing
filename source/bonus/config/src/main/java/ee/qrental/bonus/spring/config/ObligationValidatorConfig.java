package ee.qrental.bonus.spring.config;

import ee.qrental.bonus.api.out.ObligationCalculationLoadPort;
import ee.qrental.bonus.core.validator.ObligationCalculationAddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObligationValidatorConfig {

  @Bean
  ObligationCalculationAddRequestValidator getObligationCalculationAddRequestValidator(
      final GetQWeekQuery qWeekQuery, final ObligationCalculationLoadPort loadPort) {
    return new ObligationCalculationAddRequestValidator(qWeekQuery, loadPort);
  }
}
