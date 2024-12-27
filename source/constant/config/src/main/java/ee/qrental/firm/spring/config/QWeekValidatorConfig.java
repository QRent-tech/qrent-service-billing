package ee.qrental.firm.spring.config;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.constant.api.in.request.QWeekAddRequest;
import ee.qrental.constant.api.out.QWeekLoadPort;
import ee.qrental.constant.core.validator.QWeekAddRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QWeekValidatorConfig {
  @Bean
  public AddRequestValidator<QWeekAddRequest> getQWeekAddRequestValidator(
      final QWeekLoadPort loadPort) {
    return new QWeekAddRequestValidator(loadPort);
  }
}
