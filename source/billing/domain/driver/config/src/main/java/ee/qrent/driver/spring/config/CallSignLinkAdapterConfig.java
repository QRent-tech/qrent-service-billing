package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.adapter.CallSignLinkLoadAdapter;
import ee.qrent.driver.adapter.adapter.CallSignLinkPersistenceAdapter;
import ee.qrent.driver.adapter.mapper.CallSignAdapterMapper;
import ee.qrent.driver.adapter.mapper.CallSignLinkAdapterMapper;
import ee.qrent.driver.adapter.repository.CallSignLinkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallSignLinkAdapterConfig {

  @Bean
  CallSignLinkAdapterMapper getCallSignLinkAdapterMapper(
      final CallSignAdapterMapper callSignAdapterMapper) {
    return new CallSignLinkAdapterMapper(callSignAdapterMapper);
  }

  @Bean
  CallSignLinkLoadAdapter getCallSignLinkLoadAdapter(
      final CallSignLinkRepository repository, final CallSignLinkAdapterMapper mapper) {
    return new CallSignLinkLoadAdapter(repository, mapper);
  }

  @Bean
  CallSignLinkPersistenceAdapter getCallSignLinkPersistenceAdapter(
      final CallSignLinkRepository repository, final CallSignLinkAdapterMapper mapper) {
    return new CallSignLinkPersistenceAdapter(repository, mapper);
  }
}
