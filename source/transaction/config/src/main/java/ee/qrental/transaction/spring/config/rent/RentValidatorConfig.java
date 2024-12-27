package ee.qrental.transaction.spring.config.rent;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.request.rent.RentCalculationAddRequest;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.api.out.rent.RentCalculationLoadPort;
import ee.qrental.transaction.core.validator.RentCalculationAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentValidatorConfig {
  @Bean
  AddRequestValidator<RentCalculationAddRequest> getRentCalculationAddRequestValidator(
      final RentCalculationLoadPort loadPort,
      final BalanceLoadPort balanceLoadPort,
      final GetQWeekQuery qWeekQuery) {
    return new RentCalculationAddRequestValidator(loadPort, balanceLoadPort, qWeekQuery);
  }
}
