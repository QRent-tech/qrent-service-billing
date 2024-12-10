package ee.qrental.contract.spring.config;

import ee.qrental.contract.adapter.adapter.AbsenceLoadAdapter;
import ee.qrental.contract.adapter.adapter.AbsencePersistenceAdapter;
import ee.qrental.contract.adapter.mapper.AbsenceAdapterMapper;
import ee.qrental.contract.adapter.repository.AbsenceRepository;
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
