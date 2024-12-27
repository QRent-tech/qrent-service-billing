package ee.qrental.bonus.spring.config;

import ee.qrental.bonus.api.in.request.BonusCalculationAddRequest;
import ee.qrental.bonus.api.out.BonusCalculationLoadPort;
import ee.qrental.bonus.core.validator.BonusCalculationAddRequestValidator;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BonusValidatorConfig {
  @Bean
  AddRequestValidator<BonusCalculationAddRequest> getBonusCalculationAddRequestValidator(
      final GetQWeekQuery qWeekQuery, final BonusCalculationLoadPort loadPort) {
    return new BonusCalculationAddRequestValidator(qWeekQuery, loadPort);
  }
}
