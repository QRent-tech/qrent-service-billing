package ee.qrent.transaction.spring.config.rent;

import ee.qrent.transaction.repository.impl.rent.RentCalculationRepositoryImpl;
import ee.qrent.transaction.repository.impl.rent.RentCalculationResultRepositoryImpl;
import ee.qrent.transaction.repository.spring.rent.RentCalculationResultSpringDataRepository;
import ee.qrent.transaction.repository.spring.rent.RentCalculationSpringDataRepository;
import org.springframework.context.annotation.Bean;
import ee.qrent.transaction.adapter.repository.rent.RentCalculationRepository;
import ee.qrent.transaction.adapter.repository.rent.RentCalculationResultRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentRepositoryConfig {

  @Bean
  RentCalculationRepository getRentCalculationRepository(
      final RentCalculationSpringDataRepository springDataRepository) {

    return new RentCalculationRepositoryImpl(springDataRepository);
  }

  @Bean
  RentCalculationResultRepository getRentCalculationResultRepository(
      final RentCalculationResultSpringDataRepository springDataRepository) {

    return new RentCalculationResultRepositoryImpl(springDataRepository);
  }
}
