package ee.qrental.contract.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.query.GetAbsenceQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.out.*;
import ee.qrental.contract.core.mapper.*;
import ee.qrental.contract.core.service.*;
import ee.qrental.contract.core.validator.AbsenceUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceServiceConfig {

  @Bean
  GetAbsenceQuery getAbsenceQueryService(
      final GetContractQuery contractQuery,
      final GetQWeekQuery qWeekQuery,
      final AbsenceLoadPort loadPort,
      final AbsenceResponseMapper mapper,
      final AbsenceUpdateRequestMapper updateRequestMapper,
      final QDateTime qDateTime) {
    return new AbsenceQueryService(
        contractQuery, qWeekQuery, loadPort, mapper, updateRequestMapper, qDateTime);
  }

  @Bean
  AbsenceUseCaseService getAbsenceUseCaseService(
      final AbsenceAddPort addPort,
      final AbsenceUpdatePort updatePort,
      final AbsenceDeletePort deletePort,
      final AbsenceAddRequestMapper addRequestMapper,
      final AbsenceUpdateRequestMapper updateRequestMapper,
      final AbsenceUpdateRequestValidator businessRuleValidator) {

    return new AbsenceUseCaseService(
        addPort,
        updatePort,
        deletePort,
        addRequestMapper,
        updateRequestMapper,
        businessRuleValidator);
  }
}
