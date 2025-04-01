package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.adapter.FirmLinkLoadAdapter;
import ee.qrent.driver.adapter.adapter.FirmLinkPersistenceAdapter;
import ee.qrent.driver.adapter.mapper.FirmLinkAdapterMapper;
import ee.qrent.driver.adapter.repository.FirmLinkRepository;
import ee.qrent.driver.api.out.FirmLinkLoadPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirmLinkAdapterConfig {

  @Bean
  FirmLinkAdapterMapper getFirmLinkAdapterMapper() {
    return new FirmLinkAdapterMapper();
  }

  @Bean
  FirmLinkLoadPort getFirmLinkLoadAdapter(
           final FirmLinkRepository repository,
   final FirmLinkAdapterMapper mapper) {
    return new FirmLinkLoadAdapter(repository, mapper);
  }

  @Bean
  FirmLinkPersistenceAdapter getFirmLinkPersistenceAdapter(
      final FirmLinkRepository repository, final FirmLinkAdapterMapper mapper) {
    return new FirmLinkPersistenceAdapter(repository, mapper);
  }
}
