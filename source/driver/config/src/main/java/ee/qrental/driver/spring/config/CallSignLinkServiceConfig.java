package ee.qrental.driver.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.driver.api.in.query.GetCallSignLinkQuery;
import ee.qrental.driver.api.in.query.GetFirmLinkQuery;
import ee.qrental.driver.api.in.request.CallSignLinkAddRequest;
import ee.qrental.driver.api.in.request.CallSignLinkDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignLinkUpdateRequest;
import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.mapper.*;
import ee.qrental.driver.core.service.CallSignLinkQueryService;
import ee.qrental.driver.core.service.CallSignLinkUseCaseService;
import ee.qrental.driver.core.service.FirmLinkQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignLinkServiceConfig {

  @Bean
  GetCallSignLinkQuery getCallSignLinkQueryService(
      final CallSignLinkLoadPort loadPort,
      final CallSignLinkResponseMapper mapper,
      final CallSignLinkUpdateRequestMapper updateRequestMapper) {
    return new CallSignLinkQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  GetFirmLinkQuery getFirmLinkQueryService(
      final FirmLinkLoadPort loadPort,
      final FirmLinkResponseMapper mapper,
      final FirmLinkUpdateRequestMapper updateRequestMapper) {
    return new FirmLinkQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  CallSignLinkUseCaseService getCallSignLinkUseCaseService(
      final CallSignLinkAddPort addPort,
      final CallSignLinkUpdatePort updatePort,
      final CallSignLinkDeletePort deletePort,
      final CallSignLinkLoadPort loadPort,
      final CallSignLinkAddRequestMapper addRequestMapper,
      final AddRequestValidator<CallSignLinkAddRequest> addRequestValidator,
      final UpdateRequestValidator<CallSignLinkUpdateRequest> updateRequestValidator,
      final DeleteRequestValidator<CallSignLinkDeleteRequest> deleteRequestValidator,
      final QDateTime qDateTime) {
    return new CallSignLinkUseCaseService(
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
