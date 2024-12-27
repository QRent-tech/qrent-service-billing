package ee.qrental.user.spring.config;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.email.api.in.usecase.EmailSendUseCase;
import ee.qrental.user.api.in.query.GetUserAccountQuery;
import ee.qrental.user.api.in.request.UserAccountAddRequest;
import ee.qrental.user.api.in.request.UserAccountDeleteRequest;
import ee.qrental.user.api.in.request.UserAccountUpdateRequest;
import ee.qrental.user.api.out.UserAccountAddPort;
import ee.qrental.user.api.out.UserAccountDeletePort;
import ee.qrental.user.api.out.UserAccountLoadPort;
import ee.qrental.user.api.out.UserAccountUpdatePort;
import ee.qrental.user.core.mapper.UserAccountAddRequestMapper;
import ee.qrental.user.core.mapper.UserAccountResponseMapper;
import ee.qrental.user.core.mapper.UserAccountUpdateRequestMapper;
import ee.qrental.user.core.service.UserAccountQueryService;
import ee.qrental.user.core.service.UserAccountUseCaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAccountServiceConfig {

  @Bean
  GetUserAccountQuery getUserAccountQueryService(
      final UserAccountLoadPort loadPort,
      final UserAccountResponseMapper mapper,
      final UserAccountUpdateRequestMapper updateRequestMapper) {
    return new UserAccountQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  public UserAccountUseCaseService getUserAccountUseCaseService(
      final UserAccountAddPort addPort,
      final UserAccountUpdatePort updatePort,
      final UserAccountDeletePort deletePort,
      final UserAccountAddRequestMapper addRequestMapper,
      final UserAccountUpdateRequestMapper updateRequestMapper,
      final AddRequestValidator<UserAccountAddRequest> addRequestValidator,
      final UpdateRequestValidator<UserAccountUpdateRequest> updateRequestValidator,
      final DeleteRequestValidator<UserAccountDeleteRequest> deleteRequestValidator,
      final EmailSendUseCase emailSendUseCase) {
    return new UserAccountUseCaseService(
        addPort,
        updatePort,
        deletePort,
        addRequestMapper,
        updateRequestMapper,
        addRequestValidator,
        updateRequestValidator,
        deleteRequestValidator,
        emailSendUseCase);
  }
}
