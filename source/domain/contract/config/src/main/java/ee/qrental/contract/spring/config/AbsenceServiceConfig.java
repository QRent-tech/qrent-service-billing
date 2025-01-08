package ee.qrental.contract.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.query.GetAbsenceQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.request.AbsenceAddRequest;
import ee.qrental.contract.api.in.request.AbsenceDeleteRequest;
import ee.qrental.contract.api.in.request.AbsenceUpdateRequest;
import ee.qrental.contract.api.out.*;
import ee.qrental.contract.core.mapper.*;
import ee.qrental.contract.core.service.*;
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
      final AddRequestValidator<AbsenceAddRequest> addRequestValidator,
      final UpdateRequestValidator<AbsenceUpdateRequest> updateRequestValidator,
      DeleteRequestValidator<AbsenceDeleteRequest> deleteRequestValidator) {

    return new AbsenceUseCaseService(
        addPort,
        updatePort,
        deletePort,
        addRequestMapper,
        updateRequestMapper,
        addRequestValidator,
        updateRequestValidator,
        deleteRequestValidator);
  }
}
