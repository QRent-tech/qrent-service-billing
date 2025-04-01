package ee.qrent.contract.spring.config;

import ee.qrent.contract.adapter.adapter.ContractLoadAdapter;
import ee.qrent.contract.adapter.adapter.ContractPersistenceAdapter;
import ee.qrent.contract.adapter.mapper.ContractAdapterMapper;
import ee.qrent.contract.adapter.repository.ContractRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractAdapterConfig {
  @Bean
  ContractAdapterMapper getContractAdapterMapper() {
    return new ContractAdapterMapper();
  }

  @Bean
  ContractLoadAdapter getContractLoadAdapter(
      final ContractRepository repository, final ContractAdapterMapper mapper) {
    return new ContractLoadAdapter(repository, mapper);
  }

  @Bean
  ContractPersistenceAdapter getContractPersistenceAdapter(
      final ContractRepository repository, final ContractAdapterMapper mapper) {
    return new ContractPersistenceAdapter(repository, mapper);
  }
}
