package ee.qrental.contract.spring.config;

import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.request.AbsenceDeleteRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.core.validator.AbsenceDeleteRequestValidator;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceValidatorConfig {

  @Bean
  DeleteRequestValidator<AbsenceDeleteRequest> getAbsenceDeleteRequestValidator(
      final GetBalanceQuery balanceQuery,
      final GetQWeekQuery qWeekQuery,
      final AbsenceLoadPort loadPort) {
    return new AbsenceDeleteRequestValidator(balanceQuery, qWeekQuery, loadPort);
  }
}
