package ee.qrental.car.spring.config;

import ee.qrental.car.api.in.request.CarLinkAddRequest;
import ee.qrental.car.api.in.request.CarLinkDeleteRequest;
import ee.qrental.car.api.in.request.CarLinkUpdateRequest;
import ee.qrental.car.api.out.CarLinkLoadPort;
import ee.qrental.car.core.validator.CarLinkAddRequestValidator;
import ee.qrental.car.core.validator.CarLinkDeleteRequestValidator;
import ee.qrental.car.core.validator.CarLinkUpdateRequestValidator;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkValidatorConfig {

  @Bean
  AddRequestValidator<CarLinkAddRequest> getCarLinkAddRequestValidator(
      final CarLinkLoadPort loadPort) {
    return new CarLinkAddRequestValidator(loadPort);
  }

  @Bean
  UpdateRequestValidator<CarLinkUpdateRequest> getCarLinkUpdateRequestValidator(
      final CarLinkLoadPort loadPort) {
    return new CarLinkUpdateRequestValidator(loadPort);
  }

  @Bean
  DeleteRequestValidator<CarLinkDeleteRequest> getCarLinkDeleteRequestValidator(
      final CarLinkLoadPort loadPort) {
    return new CarLinkDeleteRequestValidator(loadPort);
  }
}
