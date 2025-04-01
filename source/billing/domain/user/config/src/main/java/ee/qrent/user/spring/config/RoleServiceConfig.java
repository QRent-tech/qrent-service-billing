package ee.qrent.user.spring.config;

import ee.qrent.user.api.in.query.GetRoleQuery;
import ee.qrent.user.api.out.*;
import ee.qrent.user.core.mapper.*;
import ee.qrent.user.core.service.RoleQueryService;
import ee.qrent.user.core.service.RoleUseCaseService;
import ee.qrent.user.core.validator.RoleRequestValidator;
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
      final RoleLoadPort loadPort,
      final RoleAddRequestMapper addRequestMapper,
      final RoleUpdateRequestMapper updateRequestMapper,
      final RoleRequestValidator requestValidator) {

    return new RoleUseCaseService(
     addPort,
     updatePort,
     deletePort,
     loadPort,
     addRequestMapper,
     updateRequestMapper,
            requestValidator);
  }
}
