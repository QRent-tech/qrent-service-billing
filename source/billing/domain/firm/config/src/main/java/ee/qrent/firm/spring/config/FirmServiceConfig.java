package ee.qrent.firm.spring.config;

import ee.qrent.firm.api.in.query.GetFirmQuery;
import ee.qrent.firm.api.out.FirmAddPort;
import ee.qrent.firm.api.out.FirmDeletePort;
import ee.qrent.firm.api.out.FirmLoadPort;
import ee.qrent.firm.api.out.FirmUpdatePort;
import ee.qrent.firm.core.mapper.FirmAddRequestMapper;
import ee.qrent.firm.core.mapper.FirmResponseMapper;
import ee.qrent.firm.core.mapper.FirmUpdateRequestMapper;
import ee.qrent.firm.core.service.FirmQueryService;
import ee.qrent.firm.core.service.FirmUseCaseService;
import ee.qrent.firm.core.validator.FirmRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmServiceConfig {

  @Bean
  GetFirmQuery getFirmQueryService(
      final FirmLoadPort loadPort,
      final FirmResponseMapper mapper,
      final FirmUpdateRequestMapper updateRequestMapper) {
    return new FirmQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  public FirmUseCaseService getFirmUseCaseService(
      final FirmAddPort addPort,
      final FirmUpdatePort updatePort,
      final FirmDeletePort deletePort,
      final FirmLoadPort loadPort,
      final FirmAddRequestMapper addRequestMapper,
      final FirmUpdateRequestMapper updateRequestMapper,
      final FirmRequestValidator requestValidator) {
    return new FirmUseCaseService(
        addPort,
        updatePort,
        deletePort,
        loadPort,
        addRequestMapper,
        updateRequestMapper,
        requestValidator);
  }
}
