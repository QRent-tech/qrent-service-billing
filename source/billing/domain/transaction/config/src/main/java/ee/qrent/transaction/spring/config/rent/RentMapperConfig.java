package ee.qrent.transaction.spring.config.rent;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.transaction.adapter.mapper.rent.RentCalculationAdapterMapper;
import ee.qrent.transaction.core.mapper.rent.RentCalculationAddRequestMapper;
import ee.qrent.transaction.core.mapper.rent.RentCalculationResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentMapperConfig {
  @Bean
  RentCalculationAddRequestMapper getRentCalculationAddRequestMapper() {
    return new RentCalculationAddRequestMapper();
  }

  @Bean
  RentCalculationResponseMapper getRentCalculationResponseMapper(final GetQWeekQuery qWeekQuery) {
    return new RentCalculationResponseMapper(qWeekQuery);
  }

  @Bean
  RentCalculationAdapterMapper getRentCalculationAdapterMapper() {
    return new RentCalculationAdapterMapper();
  }
}
