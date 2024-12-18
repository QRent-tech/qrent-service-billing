package ee.qrental.contract.spring.config;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.core.mapper.AbsenceAddRequestMapper;
import ee.qrental.contract.core.mapper.AbsenceResponseMapper;
import ee.qrental.contract.core.mapper.AbsenceUpdateRequestMapper;
import ee.qrental.driver.api.in.query.GetDriverQuery;
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
