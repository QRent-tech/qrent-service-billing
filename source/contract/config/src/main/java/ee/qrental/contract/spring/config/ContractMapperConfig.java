package ee.qrental.contract.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.contract.api.out.ContractLoadPort;
import ee.qrental.contract.core.mapper.ContractAddRequestMapper;
import ee.qrental.contract.core.mapper.ContractResponseMapper;
import ee.qrental.contract.core.mapper.ContractUpdateRequestMapper;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.firm.api.in.query.GetFirmQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractMapperConfig {
  @Bean
  ContractAddRequestMapper getContractAddRequestMapper(
      final GetDriverQuery driverQuery, final GetFirmQuery firmQuery) {
    return new ContractAddRequestMapper(driverQuery, firmQuery);
  }

  @Bean
  ContractResponseMapper getContractResponseMapper(final QDateTime qDateTime) {
    return new ContractResponseMapper(qDateTime);
  }

  @Bean
  ContractUpdateRequestMapper getContractUpdateRequestMapper(final ContractLoadPort loadPort) {
    return new ContractUpdateRequestMapper(loadPort);
  }
}
