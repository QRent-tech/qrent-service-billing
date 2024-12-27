package ee.qrental.insurance.spring.config;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.CloseRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.insurance.api.in.request.InsuranceCalculationAddRequest;
import ee.qrental.insurance.api.in.request.InsuranceCaseCloseRequest;
import ee.qrental.insurance.api.in.request.InsuranceCaseUpdateRequest;
import ee.qrental.insurance.api.out.InsuranceCaseBalanceLoadPort;
import ee.qrental.insurance.api.out.InsuranceCaseLoadPort;
import ee.qrental.insurance.core.validator.InsuranceCalculationAddRequestValidator;
import ee.qrental.insurance.core.validator.InsuranceCaseCloseRequestValidator;
import ee.qrental.insurance.core.validator.InsuranceCaseUpdateRequestValidator;
import ee.qrental.transaction.api.in.query.rent.GetRentCalculationQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceCaseValidatorConfig {

  @Bean
  UpdateRequestValidator<InsuranceCaseUpdateRequest> getInsuranceCaseUpdateRequestValidator(
      final InsuranceCaseLoadPort insuranceCaseLoadPort,
      final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort) {
    return new InsuranceCaseUpdateRequestValidator(
        insuranceCaseLoadPort, insuranceCaseBalanceLoadPort);
  }

  @Bean
  CloseRequestValidator<InsuranceCaseCloseRequest> getInsuranceCaseCloseRequestValidator(
      final InsuranceCaseLoadPort loadPort) {
    return new InsuranceCaseCloseRequestValidator(loadPort);
  }

  @Bean
  AddRequestValidator<InsuranceCalculationAddRequest> getInsuranceCalculationAddRequestValidator(
      final GetQWeekQuery qWeekQuery, final GetRentCalculationQuery rentCalculationQuery) {
    return new InsuranceCalculationAddRequestValidator(qWeekQuery, rentCalculationQuery);
  }
}
