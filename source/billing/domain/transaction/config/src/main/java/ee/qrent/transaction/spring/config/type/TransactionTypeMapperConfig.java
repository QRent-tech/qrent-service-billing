package ee.qrent.transaction.spring.config.type;

import ee.qrent.transaction.core.mapper.type.TransactionTypeAddRequestMapper;
import ee.qrent.transaction.core.mapper.type.TransactionTypeResponseMapper;
import ee.qrent.transaction.core.mapper.type.TransactionTypeUpdateRequestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionTypeMapperConfig {
  @Bean
  TransactionTypeAddRequestMapper getTransactionTypeAddRequestMapper() {
    return new TransactionTypeAddRequestMapper();
  }

  @Bean
  TransactionTypeResponseMapper getTransactionTypeResponseMapper() {
    return new TransactionTypeResponseMapper();
  }

  @Bean
  TransactionTypeUpdateRequestMapper getTransactionTypeUpdateRequestMapper() {
    return new TransactionTypeUpdateRequestMapper();
  }
}
