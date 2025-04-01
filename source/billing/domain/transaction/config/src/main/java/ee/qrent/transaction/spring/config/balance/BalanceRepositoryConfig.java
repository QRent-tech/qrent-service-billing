package ee.qrent.transaction.spring.config.balance;

import ee.qrent.transaction.adapter.repository.balance.BalanceCalculationRepository;
import ee.qrent.transaction.adapter.repository.balance.BalanceCalculationResultRepository;
import ee.qrent.transaction.adapter.repository.balance.BalanceRepository;
import ee.qrent.transaction.adapter.repository.balance.BalanceTransactionRepository;
import ee.qrent.transaction.repository.impl.balance.BalanceCalculationRepositoryImpl;
import ee.qrent.transaction.repository.impl.balance.BalanceCalculationResultRepositoryImpl;
import ee.qrent.transaction.repository.impl.balance.BalanceRepositoryImpl;
import ee.qrent.transaction.repository.impl.balance.BalanceTransactionRepositoryImpl;
import ee.qrent.transaction.repository.spring.balance.BalanceCalculationResultSpringDataRepository;
import ee.qrent.transaction.repository.spring.balance.BalanceCalculationSpringDataRepository;
import ee.qrent.transaction.repository.spring.balance.BalanceSpringDataRepository;
import ee.qrent.transaction.repository.spring.balance.BalanceTransactionSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BalanceRepositoryConfig {

  @Bean
  BalanceRepository getBalanceRepository(final BalanceSpringDataRepository springDataRepository) {
    return new BalanceRepositoryImpl(springDataRepository);
  }
  @Bean
  BalanceTransactionRepository getBalanceTransactionRepository(
          final BalanceTransactionSpringDataRepository springDataRepository) {
    return new BalanceTransactionRepositoryImpl(springDataRepository);
  }

  @Bean
  BalanceCalculationRepository getBalanceCalculationRepository(
      final BalanceCalculationSpringDataRepository springDataRepository) {
    return new BalanceCalculationRepositoryImpl(springDataRepository);
  }

  @Bean
  BalanceCalculationResultRepository getBalanceCalculationResultRepository(
      final BalanceCalculationResultSpringDataRepository springDataRepository) {
    return new BalanceCalculationResultRepositoryImpl(springDataRepository);
  }
}
