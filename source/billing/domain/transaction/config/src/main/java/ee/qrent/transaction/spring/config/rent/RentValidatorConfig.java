package ee.qrent.transaction.spring.config.rent;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.transaction.api.in.request.rent.RentCalculationAddRequest;
import ee.qrent.transaction.api.out.balance.BalanceLoadPort;
import ee.qrent.transaction.api.out.rent.RentCalculationLoadPort;
import ee.qrent.transaction.core.validator.RentCalculationAddRequestValidator;
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
