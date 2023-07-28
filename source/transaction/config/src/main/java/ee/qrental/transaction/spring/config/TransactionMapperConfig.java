package ee.qrental.transaction.spring.config;

import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.transaction.core.mapper.TransactionAddRequestMapper;
import ee.qrental.transaction.core.mapper.TransactionResponseMapper;
import ee.qrental.transaction.core.mapper.TransactionUpdateRequestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionMapperConfig {

  @Bean
  TransactionAddRequestMapper getTransactionAddRequestMapper() {
    return new TransactionAddRequestMapper();
  }

  @Bean
  TransactionResponseMapper getTransactionResponseMapper(
      final GetDriverQuery driverQuery) {
    return new TransactionResponseMapper(driverQuery);
  }

  @Bean
  TransactionUpdateRequestMapper getTransactionUpdateRequestMapper() {
    return new TransactionUpdateRequestMapper();
  }
}
