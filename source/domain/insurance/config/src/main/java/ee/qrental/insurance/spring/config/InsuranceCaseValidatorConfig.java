package ee.qrental.insurance.spring.config;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.insurance.api.in.request.InsuranceCalculationAddRequest;
import ee.qrental.insurance.api.out.InsuranceCaseBalanceLoadPort;
import ee.qrental.insurance.api.out.InsuranceCaseLoadPort;
import ee.qrental.insurance.core.validator.InsuranceCalculationAddRequestValidator;
import ee.qrental.insurance.core.validator.InsuranceCaseCloseBusinessRuleValidator;
import ee.qrental.insurance.core.validator.InsuranceCaseUpdateBusinessRuleValidator;
import ee.qrental.transaction.api.in.query.rent.GetRentCalculationQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceCaseValidatorConfig {

  @Bean
  InsuranceCaseUpdateBusinessRuleValidator getInsuranceCaseBusinessRuleValidator(
      final InsuranceCaseLoadPort insuranceCaseLoadPort,
      final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort) {
    return new InsuranceCaseUpdateBusinessRuleValidator(
        insuranceCaseLoadPort, insuranceCaseBalanceLoadPort);
  }

  @Bean
  InsuranceCaseCloseBusinessRuleValidator getInsuranceCaseCloseBusinessRuleValidator(
      final InsuranceCaseLoadPort loadPort) {
    return new InsuranceCaseCloseBusinessRuleValidator(loadPort);
  }

  @Bean
  AddRequestValidator<InsuranceCalculationAddRequest> getInsuranceCalculationAddRequestValidator(
      final GetQWeekQuery qWeekQuery, final GetRentCalculationQuery rentCalculationQuery) {
    return new InsuranceCalculationAddRequestValidator(qWeekQuery, rentCalculationQuery);
  }
}
