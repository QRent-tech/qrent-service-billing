package ee.qrental.driver.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.validator.DriverUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverValidatorConfig {

  @Bean
  DriverUpdateRequestValidator getDriverUpdateRequestValidator(
      final DriverLoadPort loadPort, final GetQWeekQuery qWeekQuery, final QDateTime qDateTime) {
    return new DriverUpdateRequestValidator(loadPort, qWeekQuery, qDateTime);
  }
}
