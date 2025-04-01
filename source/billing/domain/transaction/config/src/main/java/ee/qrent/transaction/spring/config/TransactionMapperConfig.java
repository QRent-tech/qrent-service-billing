package ee.qrent.transaction.spring.config;

import ee.qrent.driver.api.in.query.GetDriverQuery;
import ee.qrent.transaction.api.out.type.TransactionTypeLoadPort;
import ee.qrent.transaction.core.mapper.TransactionAddRequestMapper;
import ee.qrent.transaction.core.mapper.TransactionResponseMapper;
import ee.qrent.transaction.core.mapper.TransactionUpdateRequestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionMapperConfig {

  @Bean
  TransactionAddRequestMapper getTransactionAddRequestMapper(
      final TransactionTypeLoadPort transactionTypeLoadPort) {
    return new TransactionAddRequestMapper(transactionTypeLoadPort);
  }

  @Bean
  TransactionResponseMapper getTransactionResponseMapper(final GetDriverQuery driverQuery) {
    return new TransactionResponseMapper(driverQuery);
  }

  @Bean
  TransactionUpdateRequestMapper getTransactionUpdateRequestMapper() {
    return new TransactionUpdateRequestMapper();
  }
}
