package ee.qrental.car.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.car.api.in.query.GetCarLinkQuery;
import ee.qrental.car.api.in.request.CarLinkAddRequest;
import ee.qrental.car.api.in.request.CarLinkDeleteRequest;
import ee.qrental.car.api.in.request.CarLinkUpdateRequest;
import ee.qrental.car.api.out.CarLinkAddPort;
import ee.qrental.car.api.out.CarLinkDeletePort;
import ee.qrental.car.api.out.CarLinkLoadPort;
import ee.qrental.car.api.out.CarLinkUpdatePort;
import ee.qrental.car.core.mapper.CarLinkAddRequestMapper;
import ee.qrental.car.core.mapper.CarLinkResponseMapper;
import ee.qrental.car.core.mapper.CarLinkUpdateRequestMapper;
import ee.qrental.car.core.service.CarLinkQueryService;
import ee.qrental.car.core.service.CarLinkUseCaseService;
import ee.qrental.car.core.validator.CarLinkAddRequestValidator;
import ee.qrental.car.core.validator.CarLinkUpdateRequestValidator;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkServiceConfig {

  @Bean
  public GetCarLinkQuery getLinkQueryService(
      final CarLinkLoadPort loadPort,
      final CarLinkResponseMapper mapper,
      final CarLinkUpdateRequestMapper updateRequestMapper) {
    return new CarLinkQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  public CarLinkUseCaseService getLinkUseCaseService(
      final CarLinkAddPort addPort,
      final CarLinkUpdatePort updatePort,
      final CarLinkDeletePort deletePort,
      final CarLinkLoadPort loadPort,
      final CarLinkAddRequestMapper addRequestMapper,
      final AddRequestValidator<CarLinkAddRequest> addRequestValidator,
   final UpdateRequestValidator<CarLinkUpdateRequest> updateRequestValidator,
   final DeleteRequestValidator<CarLinkDeleteRequest> deleteRequestValidator,


      final QDateTime qDateTime) {
    return new CarLinkUseCaseService(
        addPort,
        updatePort,
        deletePort,
        loadPort,
        addRequestMapper,
            addRequestValidator,
            updateRequestValidator,
            deleteRequestValidator,
        qDateTime);
  }
}
