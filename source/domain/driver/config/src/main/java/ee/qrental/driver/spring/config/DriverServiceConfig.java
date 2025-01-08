package ee.qrental.driver.spring.config;

import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrental.bonus.api.in.query.GetObligationCalculationQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.usecase.ContractUpdateUseCase;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.driver.api.in.request.DriverUpdateRequest;
import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.mapper.DriverAddRequestMapper;
import ee.qrental.driver.core.mapper.DriverResponseMapper;
import ee.qrental.driver.core.mapper.DriverUpdateRequestMapper;
import ee.qrental.driver.core.mapper.FriendshipResponseMapper;
import ee.qrental.driver.core.service.DriverQueryService;
import ee.qrental.driver.core.service.DriverUseCaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverServiceConfig {

  @Bean
  GetDriverQuery getDriverQueryService(
      final GetObligationCalculationQuery obligationCalculationQuery,
      final DriverLoadPort loadPort,
      final DriverUpdateRequestMapper updateRequestMapper,
      final DriverResponseMapper mapper,
      final FriendshipLoadPort friendshipLoadPort,
      final FriendshipResponseMapper friendshipResponseMapper) {
    return new DriverQueryService(
        obligationCalculationQuery,
        loadPort,
        updateRequestMapper,
        mapper,
        friendshipLoadPort,
        friendshipResponseMapper);
  }

  @Bean
  public DriverUseCaseService getDriverUseCaseService(
      final GetContractQuery contractQuery,
      final ContractUpdateUseCase updateUseCase,
      final DriverAddPort addPort,
      final DriverUpdatePort updatePort,
      final DriverDeletePort deletePort,
      final DriverLoadPort loadPort,
      final DriverAddRequestMapper addRequestMapper,
      final DriverUpdateRequestMapper updateRequestMapper,
      final UpdateRequestValidator<DriverUpdateRequest> updateBusinessRuleValidator) {
    return new DriverUseCaseService(
        contractQuery,
        updateUseCase,
        addPort,
        updatePort,
        deletePort,
        loadPort,
        addRequestMapper,
        updateRequestMapper,
        updateBusinessRuleValidator);
  }
}
