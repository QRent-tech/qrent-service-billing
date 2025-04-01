package ee.qrent.user.spring.config;

import ee.qrent.email.api.in.usecase.EmailSendUseCase;
import ee.qrent.security.api.in.usecase.PasswordUseCase;
import ee.qrent.user.api.in.query.GetUserAccountQuery;
import ee.qrent.user.api.out.UserAccountAddPort;
import ee.qrent.user.api.out.UserAccountDeletePort;
import ee.qrent.user.api.out.UserAccountLoadPort;
import ee.qrent.user.api.out.UserAccountUpdatePort;
import ee.qrent.user.core.mapper.UserAccountAddRequestMapper;
import ee.qrent.user.core.mapper.UserAccountResponseMapper;
import ee.qrent.user.core.mapper.UserAccountUpdateRequestMapper;
import ee.qrent.user.core.service.UserAccountQueryService;
import ee.qrent.user.core.service.UserAccountUseCaseService;
import ee.qrent.user.core.validator.UserAccountRequestValidator;
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
      final UserAccountLoadPort loadPort,
      final UserAccountAddRequestMapper addRequestMapper,
      final UserAccountUpdateRequestMapper updateRequestMapper,
      final UserAccountRequestValidator requestValidator,
      final EmailSendUseCase emailSendUseCase, final PasswordUseCase passwordUseCase) {
    return new UserAccountUseCaseService(
        addPort, 
            updatePort, 
            deletePort, 
            loadPort, 
            addRequestMapper, 
            updateRequestMapper,
            requestValidator,
            emailSendUseCase, passwordUseCase);
  }
}
