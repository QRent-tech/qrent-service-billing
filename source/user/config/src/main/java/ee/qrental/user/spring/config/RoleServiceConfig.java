package ee.qrental.user.spring.config;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.user.api.in.query.GetRoleQuery;
import ee.qrental.user.api.in.request.RoleAddRequest;
import ee.qrental.user.api.in.request.RoleDeleteRequest;
import ee.qrental.user.api.in.request.RoleUpdateRequest;
import ee.qrental.user.api.out.*;
import ee.qrental.user.core.mapper.*;
import ee.qrental.user.core.service.RoleQueryService;
import ee.qrental.user.core.service.RoleUseCaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleServiceConfig {

  @Bean
  GetRoleQuery getRoleQueryService(
      final RoleLoadPort loadPort,
      final RoleResponseMapper mapper,
      final RoleUpdateRequestMapper updateRequestMapper) {
    return new RoleQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  public RoleUseCaseService getRoleUseCaseService(
      final RoleAddPort addPort,
      final RoleUpdatePort updatePort,
      final RoleDeletePort deletePort,
      final RoleAddRequestMapper addRequestMapper,
      final RoleUpdateRequestMapper updateRequestMapper,
      final AddRequestValidator<RoleAddRequest> addRequestValidator,
      final UpdateRequestValidator<RoleUpdateRequest> updateRequestValidator,
      final DeleteRequestValidator<RoleDeleteRequest> deleteRequestValidator) {

    return new RoleUseCaseService(
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
