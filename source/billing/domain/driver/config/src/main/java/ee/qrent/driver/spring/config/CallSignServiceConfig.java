package ee.qrent.driver.spring.config;

import ee.qrent.driver.api.in.query.GetCallSignQuery;
import ee.qrent.driver.api.out.CallSignAddPort;
import ee.qrent.driver.api.out.CallSignDeletePort;
import ee.qrent.driver.api.out.CallSignLoadPort;
import ee.qrent.driver.api.out.CallSignUpdatePort;
import ee.qrent.driver.core.mapper.CallSignAddRequestMapper;
import ee.qrent.driver.core.mapper.CallSignResponseMapper;
import ee.qrent.driver.core.mapper.CallSignUpdateRequestMapper;
import ee.qrent.driver.core.service.CallSignQueryService;
import ee.qrent.driver.core.service.CallSignUseCaseService;
import ee.qrent.driver.core.validator.CallSignRequestValidator;
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
