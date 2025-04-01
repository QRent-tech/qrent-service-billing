package ee.qrental.car.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.car.core.mapper.CarAddRequestMapper;
import ee.qrental.car.core.mapper.CarResponseMapper;
import ee.qrental.car.core.mapper.CarUpdateRequestMapper;
import ee.qrental.car.core.service.CarWarrantyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarMapperConfig {
  @Bean
  CarAddRequestMapper getCarAddRequestMapper() {
    return new CarAddRequestMapper();
  }

  @Bean
  CarResponseMapper getCarResponseMapper(
      final QDateTime qDateTime, final CarWarrantyService warrantyService) {
    return new CarResponseMapper(qDateTime, warrantyService);
  }

  @Bean
  CarUpdateRequestMapper getCarUpdateRequestMapper() {
    return new CarUpdateRequestMapper();
  }
}
