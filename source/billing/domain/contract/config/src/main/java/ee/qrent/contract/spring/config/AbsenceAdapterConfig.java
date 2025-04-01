package ee.qrent.contract.spring.config;

import ee.qrent.contract.adapter.adapter.AbsenceLoadAdapter;
import ee.qrent.contract.adapter.adapter.AbsencePersistenceAdapter;
import ee.qrent.contract.adapter.mapper.AbsenceAdapterMapper;
import ee.qrent.contract.adapter.repository.AbsenceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceAdapterConfig {
  @Bean
  AbsenceAdapterMapper getAbsenceAdapterMapper() {
    return new AbsenceAdapterMapper();
  }

  @Bean
  AbsenceLoadAdapter getAbsenceLoadAdapter(
      final AbsenceRepository repository, final AbsenceAdapterMapper mapper) {
    return new AbsenceLoadAdapter(repository, mapper);
  }

  @Bean
  AbsencePersistenceAdapter getAbsencePersistenceAdapter(
      final AbsenceRepository repository, final AbsenceAdapterMapper mapper) {
    return new AbsencePersistenceAdapter(repository, mapper);
  }
}
