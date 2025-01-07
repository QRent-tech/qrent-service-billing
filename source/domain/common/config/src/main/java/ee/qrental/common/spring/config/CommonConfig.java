package ee.qrental.common.spring.config;

import ee.qrental.common.core.time.QDateTimeImpl;
import ee.qrent.common.in.time.QDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class CommonConfig {

  @Bean
  QDateTime getQDateTime() {
    return new QDateTimeImpl();
  }
}
