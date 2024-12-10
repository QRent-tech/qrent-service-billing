package ee.qrental.contract.spring.config;

import ee.qrental.contract.adapter.repository.AbsenceRepository;
import ee.qrental.contract.repository.impl.AbsenceRepositoryImpl;
import ee.qrental.contract.repository.spring.AbsenceSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsenceRepositoryConfig {

  @Bean
  AbsenceRepository getAbsenceRepository(final AbsenceSpringDataRepository springDataRepository) {
    return new AbsenceRepositoryImpl(springDataRepository);
  }
}
