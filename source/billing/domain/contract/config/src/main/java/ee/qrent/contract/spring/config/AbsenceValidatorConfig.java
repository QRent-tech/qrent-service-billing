package ee.qrent.contract.spring.config;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.contract.api.out.AbsenceLoadPort;
import ee.qrent.contract.core.validator.AbsenceUpdateRequestValidator;
import ee.qrent.transaction.api.in.query.balance.GetBalanceQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceValidatorConfig {

  @Bean
  AbsenceUpdateRequestValidator getAbsenceUpdateRequestValidator(
      final GetBalanceQuery balanceQuery,
      final GetQWeekQuery qWeekQuery,
      final AbsenceLoadPort loadPort) {
    return new AbsenceUpdateRequestValidator(balanceQuery, qWeekQuery, loadPort);
  }
}
