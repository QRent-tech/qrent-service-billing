package ee.qrental.firm.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.request.QWeekAddRequest;
import ee.qrental.constant.api.out.QWeekAddPort;
import ee.qrental.constant.api.out.QWeekDeletePort;
import ee.qrental.constant.api.out.QWeekLoadPort;
import ee.qrental.constant.api.out.QWeekUpdatePort;
import ee.qrental.constant.core.mapper.*;
import ee.qrental.constant.core.service.QWeekQueryService;
import ee.qrental.constant.core.service.QWeekUseCaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QWeekServiceConfig {

  @Bean
  public GetQWeekQuery getQWeekQueryService(
      final QWeekLoadPort loadPort,
      final QWeekResponseMapper mapper,
      final QWeekUpdateRequestMapper updateRequestMapper,
      final QDateTime qDateTime,
      final QWeekUseCaseService qWeekUseCaseService) {
    return new QWeekQueryService(
        loadPort, mapper, updateRequestMapper, qDateTime, qWeekUseCaseService);
  }

  @Bean
  public QWeekUseCaseService getQWeekUseCaseService(
      final QWeekAddPort addPort,
      final QWeekUpdatePort updatePort,
      final QWeekDeletePort deletePort,
      final QWeekLoadPort loadPort,
      final QWeekAddRequestMapper addRequestMapper,
      final QWeekUpdateRequestMapper updateRequestMapper,
      final AddRequestValidator<QWeekAddRequest> addRequestValidator) {
    return new QWeekUseCaseService(
        addPort,
        updatePort,
        deletePort,
        loadPort,
        addRequestMapper,
        updateRequestMapper,
        addRequestValidator);
  }
}
