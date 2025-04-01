package ee.qrent.contract.spring.config;

import ee.qrent.contract.api.out.AbsenceLoadPort;
import ee.qrent.contract.core.mapper.AbsenceAddRequestMapper;
import ee.qrent.contract.core.mapper.AbsenceResponseMapper;
import ee.qrent.contract.core.mapper.AbsenceUpdateRequestMapper;
import ee.qrent.driver.api.in.query.GetDriverQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceMapperConfig {
  @Bean
  AbsenceAddRequestMapper getAbsenceAddRequestMapper() {

    return new AbsenceAddRequestMapper();
  }

  @Bean
  AbsenceUpdateRequestMapper getAbsenceUpdateRequestMapper(final AbsenceLoadPort loadPort) {

    return new AbsenceUpdateRequestMapper(loadPort);
  }

  @Bean
  AbsenceResponseMapper getAbsenceResponseMapper(final GetDriverQuery driverQuery) {

    return new AbsenceResponseMapper(driverQuery);
  }
}
