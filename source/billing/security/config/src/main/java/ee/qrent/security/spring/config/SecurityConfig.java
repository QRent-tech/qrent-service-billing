package ee.qrent.security.spring.config;

import ee.qrent.security.api.in.usecase.PasswordUseCase;
import ee.qrent.security.core.service.PasswordUseCaseService;
import ee.qrent.security.core.service.QUserDetailService;
import ee.qrent.user.api.in.query.GetUserAccountQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

  @Bean
  public UserDetailsService userQUserDetailService(final GetUserAccountQuery userAccountQuery) {

    return new QUserDetailService(userAccountQuery);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Bean
  public PasswordUseCase getPasswordUseCaseService(final PasswordEncoder encoder) {

    return new PasswordUseCaseService(encoder);
  }
}
