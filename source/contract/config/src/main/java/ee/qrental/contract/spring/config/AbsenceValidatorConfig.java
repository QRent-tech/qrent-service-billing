package ee.qrental.contract.spring.config;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.core.validator.AbsenceAddBusinessRuleValidator;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceValidatorConfig {

  @Bean
  AbsenceAddBusinessRuleValidator getAbsenceAddBusinessRuleValidator(
      final GetBalanceQuery balanceQuery,
      final GetQWeekQuery qWeekQuery,
      final AbsenceLoadPort loadPort) {
    return new AbsenceAddBusinessRuleValidator(balanceQuery, qWeekQuery, loadPort);
  }
}
