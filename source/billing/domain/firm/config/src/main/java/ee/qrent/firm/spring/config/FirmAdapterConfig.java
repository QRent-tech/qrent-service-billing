package ee.qrent.firm.spring.config;

import ee.qrent.firm.adapter.adapter.FirmLoadAdapter;
import ee.qrent.firm.adapter.adapter.FirmPersistenceAdapter;
import ee.qrent.firm.adapter.mapper.FirmAdapterMapper;
import ee.qrent.firm.adapter.repository.FirmRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmAdapterConfig {
  @Bean
  FirmAdapterMapper getFirmAdapterMapper() {
    return new FirmAdapterMapper();
  }

  @Bean
  FirmLoadAdapter getFirmLoadAdapter(
      final FirmRepository repository, final FirmAdapterMapper mapper) {
    return new FirmLoadAdapter(repository, mapper);
  }

  @Bean
  FirmPersistenceAdapter getFirmPersistenceAdapter(
      final FirmRepository repository, final FirmAdapterMapper mapper) {
    return new FirmPersistenceAdapter(repository, mapper);
  }
}
