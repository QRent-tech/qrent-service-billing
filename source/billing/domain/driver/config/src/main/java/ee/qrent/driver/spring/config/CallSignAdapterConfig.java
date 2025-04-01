package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.adapter.CallSignLoadAdapter;
import ee.qrent.driver.adapter.adapter.CallSignPersistenceAdapter;
import ee.qrent.driver.adapter.mapper.CallSignAdapterMapper;
import ee.qrent.driver.adapter.repository.CallSignRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignAdapterConfig {
  @Bean
  CallSignAdapterMapper getCallSignAdapterMapper() {
    return new CallSignAdapterMapper();
  }

  @Bean
  CallSignLoadAdapter getCallSignLoadAdapter(
      final CallSignRepository repository, final CallSignAdapterMapper mapper) {
    return new CallSignLoadAdapter(repository, mapper);
  }

  @Bean
  CallSignPersistenceAdapter getCallSignPersistenceAdapter(
      final CallSignRepository repository, final CallSignAdapterMapper mapper) {
    return new CallSignPersistenceAdapter(repository, mapper);
  }
}
