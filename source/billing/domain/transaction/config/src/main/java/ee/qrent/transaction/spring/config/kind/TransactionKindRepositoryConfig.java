package ee.qrent.transaction.spring.config.kind;

import ee.qrent.transaction.adapter.repository.kind.TransactionKindRepository;
import ee.qrent.transaction.repository.impl.kind.TransactionKindRepositoryImpl;
import ee.qrent.transaction.repository.spring.kind.TransactionKindSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionKindRepositoryConfig {

  @Bean
  TransactionKindRepository getTransactionKindRepository(
      final TransactionKindSpringDataRepository springDataRepository) {
    return new TransactionKindRepositoryImpl(springDataRepository);
  }
}
