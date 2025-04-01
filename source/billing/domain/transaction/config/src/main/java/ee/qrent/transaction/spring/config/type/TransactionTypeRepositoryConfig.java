package ee.qrent.transaction.spring.config.type;

import ee.qrent.transaction.adapter.repository.TransactionTypeRepository;
import ee.qrent.transaction.repository.impl.type.TransactionTypeRepositoryImpl;
import ee.qrent.transaction.repository.spring.type.TransactionTypeSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionTypeRepositoryConfig {

  @Bean
  TransactionTypeRepository getTransactionTypeRepository(
      final TransactionTypeSpringDataRepository springDataRepository) {
    return new TransactionTypeRepositoryImpl(springDataRepository);
  }
}
