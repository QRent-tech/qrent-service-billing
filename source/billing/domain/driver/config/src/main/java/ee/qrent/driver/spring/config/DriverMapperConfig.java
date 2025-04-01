package ee.qrent.driver.spring.config;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.driver.core.mapper.DriverAddRequestMapper;
import ee.qrent.driver.core.mapper.DriverResponseMapper;
import ee.qrent.driver.core.mapper.DriverUpdateRequestMapper;
import ee.qrent.driver.core.mapper.FriendshipDomainMapper;
import ee.qrent.firm.api.in.query.GetFirmQuery;
import ee.qrent.insurance.api.in.query.GetQKaskoQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverMapperConfig {
  @Bean
  DriverAddRequestMapper getDriverAddRequestMapper() {
    return new DriverAddRequestMapper();
  }

  @Bean
  DriverResponseMapper getDriverResponseMapper(
      final GetFirmQuery firmQuery,
      final GetQKaskoQuery qKaskoQuery,
      final GetQWeekQuery qWeekQuery) {
    return new DriverResponseMapper(firmQuery, qKaskoQuery, qWeekQuery);
  }

  @Bean
  DriverUpdateRequestMapper getDriverUpdateRequestMapper(
      final FriendshipDomainMapper friendshipDomainMapper) {
    return new DriverUpdateRequestMapper(friendshipDomainMapper);
  }
}
