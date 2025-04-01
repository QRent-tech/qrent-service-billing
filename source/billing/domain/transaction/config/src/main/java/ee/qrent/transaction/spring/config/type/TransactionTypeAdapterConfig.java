package ee.qrent.transaction.spring.config.type;

import ee.qrent.transaction.adapter.adapter.type.TransactionTypeLoadAdapter;
import ee.qrent.transaction.adapter.adapter.type.TransactionTypePersistenceAdapter;
import ee.qrent.transaction.adapter.mapper.kind.TransactionKindAdapterMapper;
import ee.qrent.transaction.adapter.mapper.type.TransactionTypeAdapterMapper;
import ee.qrent.transaction.adapter.repository.TransactionTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionTypeAdapterConfig {

  @Bean
  TransactionTypeAdapterMapper getTransactionTypeAdapterMapper(
      final TransactionKindAdapterMapper transactionKindAdapterMapper) {
    return new TransactionTypeAdapterMapper(transactionKindAdapterMapper);
  }

  @Bean
  TransactionTypeLoadAdapter getTransactionTypeLoadAdapter(
      final TransactionTypeRepository repository, final TransactionTypeAdapterMapper mapper) {
    return new TransactionTypeLoadAdapter(repository, mapper);
  }

  @Bean
  TransactionTypePersistenceAdapter getTransactionTypePersistenceAdapter(
      final TransactionTypeRepository repository, final TransactionTypeAdapterMapper mapper) {
    return new TransactionTypePersistenceAdapter(repository, mapper);
  }
}
