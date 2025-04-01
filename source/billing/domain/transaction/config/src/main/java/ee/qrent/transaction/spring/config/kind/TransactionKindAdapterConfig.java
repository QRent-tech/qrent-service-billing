package ee.qrent.transaction.spring.config.kind;

import ee.qrent.transaction.adapter.adapter.kind.TransactionKindLoadAdapter;
import ee.qrent.transaction.adapter.adapter.kind.TransactionKindPersistenceAdapter;
import ee.qrent.transaction.adapter.mapper.kind.TransactionKindAdapterMapper;
import ee.qrent.transaction.adapter.repository.kind.TransactionKindRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionKindAdapterConfig {

  @Bean
  TransactionKindAdapterMapper getTTransactionKindAdapterMapper() {
    return new TransactionKindAdapterMapper();
  }

  @Bean
  TransactionKindLoadAdapter getTransactionKindLoadAdapter(
      final TransactionKindRepository repository, final TransactionKindAdapterMapper mapper) {
    return new TransactionKindLoadAdapter(repository, mapper);
  }

  @Bean
  TransactionKindPersistenceAdapter getTransactionKindPersistenceAdapter(
      final TransactionKindRepository repository, final TransactionKindAdapterMapper mapper) {
    return new TransactionKindPersistenceAdapter(repository, mapper);
  }
}
