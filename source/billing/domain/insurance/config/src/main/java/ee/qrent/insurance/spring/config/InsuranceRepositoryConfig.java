package ee.qrent.insurance.spring.config;

import ee.qrent.insurance.adapter.repository.InsuranceCalculationRepository;
import ee.qrent.insurance.adapter.repository.InsuranceCaseBalanceRepository;
import ee.qrent.insurance.adapter.repository.InsuranceCaseBalanceTransactionRepository;
import ee.qrent.insurance.adapter.repository.InsuranceCaseRepository;
import ee.qrent.insurance.repository.impl.InsuranceCalculationRepositoryImpl;
import ee.qrent.insurance.repository.impl.InsuranceCaseBalanceRepositoryImpl;
import ee.qrent.insurance.repository.impl.InsuranceCaseBalanceTransactionRepositoryImpl;
import ee.qrent.insurance.repository.impl.InsuranceCaseRepositoryImpl;
import ee.qrent.insurance.repository.spring.InsuranceCalculationSpringDataRepository;
import ee.qrent.insurance.repository.spring.InsuranceCaseBalanceSpringDataRepository;
import ee.qrent.insurance.repository.spring.InsuranceCaseBalanceTransactionSpringDataRepository;
import ee.qrent.insurance.repository.spring.InsuranceCaseSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceRepositoryConfig {

  @Bean
  InsuranceCaseRepository getInsuranceCaseRepository(
      final InsuranceCaseSpringDataRepository springDataRepository) {
    return new InsuranceCaseRepositoryImpl(springDataRepository);
  }

  @Bean
  InsuranceCaseBalanceRepository getInsuranceCaseBalanceRepository(
      final InsuranceCaseBalanceSpringDataRepository springDataRepository) {
    return new InsuranceCaseBalanceRepositoryImpl(springDataRepository);
  }

  @Bean
  InsuranceCalculationRepository getInsuranceCalculationRepository(
      final InsuranceCalculationSpringDataRepository springDataRepository) {
    return new InsuranceCalculationRepositoryImpl(springDataRepository);
  }

  @Bean
  InsuranceCaseBalanceTransactionRepository getInsuranceCaseBalanceTransactionRepository(
      final InsuranceCaseBalanceTransactionSpringDataRepository springDataRepository) {
    return new InsuranceCaseBalanceTransactionRepositoryImpl(springDataRepository);
  }
}
