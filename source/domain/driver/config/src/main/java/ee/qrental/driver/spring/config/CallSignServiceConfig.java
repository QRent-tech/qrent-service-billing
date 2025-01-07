package ee.qrental.driver.spring.config;

import ee.qrental.driver.api.in.query.GetCallSignQuery;
import ee.qrental.driver.api.out.CallSignAddPort;
import ee.qrental.driver.api.out.CallSignDeletePort;
import ee.qrental.driver.api.out.CallSignLoadPort;
import ee.qrental.driver.api.out.CallSignUpdatePort;
import ee.qrental.driver.core.mapper.CallSignAddRequestMapper;
import ee.qrental.driver.core.mapper.CallSignResponseMapper;
import ee.qrental.driver.core.mapper.CallSignUpdateRequestMapper;
import ee.qrental.driver.core.service.CallSignQueryService;
import ee.qrental.driver.core.service.CallSignUseCaseService;
import ee.qrental.driver.core.validator.CallSignRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignServiceConfig {

  @Bean
  GetCallSignQuery getCallSignQueryService(
      final CallSignLoadPort loadPort,
      final CallSignResponseMapper mapper,
      final CallSignUpdateRequestMapper updateRequestMapper) {
    return new CallSignQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  CallSignUseCaseService getCallSignUseCaseService(
      final CallSignAddPort addPort,
      final CallSignUpdatePort updatePort,
      final CallSignDeletePort deletePort,
      final CallSignAddRequestMapper addRequestMapper,
      final CallSignUpdateRequestMapper updateRequestMapper,
      final CallSignRequestValidator requestValidator) {
    return new CallSignUseCaseService(
        addPort, updatePort, deletePort, addRequestMapper, updateRequestMapper, requestValidator);
  }
}
