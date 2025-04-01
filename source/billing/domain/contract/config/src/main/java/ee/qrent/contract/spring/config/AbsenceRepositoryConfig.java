package ee.qrent.contract.spring.config;

import ee.qrent.contract.adapter.repository.AbsenceRepository;
import ee.qrent.contract.repository.impl.AbsenceRepositoryImpl;
import ee.qrent.contract.repository.spring.AbsenceSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceRepositoryConfig {

  @Bean
  AbsenceRepository getAbsenceRepository(final AbsenceSpringDataRepository springDataRepository) {
    return new AbsenceRepositoryImpl(springDataRepository);
  }
}
