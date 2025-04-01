package ee.qrent.transaction.spring.config;

import ee.qrent.transaction.adapter.repository.TransactionRepository;
import ee.qrent.transaction.repository.impl.TransactionRepositoryImpl;
import ee.qrent.transaction.repository.spring.TransactionSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionRepositoryConfig {

  @Bean
  TransactionRepository getTransactionRepository(
      final TransactionSpringDataRepository springDataRepository) {
    return new TransactionRepositoryImpl(springDataRepository);
  }
}
