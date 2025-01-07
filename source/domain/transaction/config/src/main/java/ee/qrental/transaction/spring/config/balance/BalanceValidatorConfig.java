package ee.qrental.transaction.spring.config.balance;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrental.transaction.api.in.request.balance.BalanceCalculationAddRequest;
import ee.qrental.transaction.api.out.balance.BalanceCalculationLoadPort;
import ee.qrental.transaction.core.validator.BalanceCalculationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BalanceValidatorConfig {
  @Bean
  AddRequestValidator<BalanceCalculationAddRequest> getBalanceCalculationAddRequestValidator(
      final BalanceCalculationLoadPort loadPort) {
    return new BalanceCalculationAddRequestValidator(loadPort);
  }
}
