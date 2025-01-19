package ee.qrental.driver.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.AttributeChecker;
import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.request.DriverAddRequest;
import ee.qrental.driver.api.in.request.DriverDeleteRequest;
import ee.qrental.driver.api.in.request.DriverUpdateRequest;
import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.validator.DriverAddRequestValidator;
import ee.qrental.driver.core.validator.DriverDeleteRequestValidator;
import ee.qrental.driver.core.validator.DriverUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverValidatorConfig {

  @Bean
  AddRequestValidator<DriverAddRequest> getDriverAddRequestValidator(
      final DriverLoadPort loadPort,
      final GetQWeekQuery qWeekQuery,
      final QDateTime qDateTime,
      final AttributeChecker attributeChecker) {

    return new DriverAddRequestValidator(loadPort, qWeekQuery, qDateTime, attributeChecker);
  }

  @Bean
  UpdateRequestValidator<DriverUpdateRequest> getDriverUpdateRequestValidator(
      final DriverLoadPort loadPort, final GetQWeekQuery qWeekQuery, final QDateTime qDateTime) {

    return new DriverUpdateRequestValidator(loadPort, qWeekQuery, qDateTime);
  }

  @Bean
  DeleteRequestValidator<DriverDeleteRequest> getDriverDeleteRequestValidator(
      final DriverLoadPort loadPort, final GetQWeekQuery qWeekQuery, final QDateTime qDateTime) {

    return new DriverDeleteRequestValidator(loadPort, qWeekQuery, qDateTime);
  }
}
