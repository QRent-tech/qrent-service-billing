package ee.qrent.contract.spring.config;

import ee.qrent.contract.adapter.repository.ContractRepository;
import ee.qrent.contract.repository.impl.ContractRepositoryImpl;
import ee.qrent.contract.repository.spring.ContractSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractRepositoryConfig {

  @Bean
  ContractRepository getContractRepository(
      final ContractSpringDataRepository springDataRepository) {
    return new ContractRepositoryImpl(springDataRepository);
  }
}
