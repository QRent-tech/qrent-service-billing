package ee.qrent.insurance.spring.config;

import ee.qrent.billing.car.api.in.query.GetCarQuery;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.driver.api.in.query.GetDriverQuery;
import ee.qrent.insurance.adapter.adapter.InsuranceCaseBalancePersistenceAdapter;
import ee.qrent.insurance.adapter.mapper.InsuranceCalculationAdapterMapper;
import ee.qrent.insurance.adapter.mapper.InsuranceCaseAdapterMapper;
import ee.qrent.insurance.adapter.mapper.InsuranceCaseBalanceAdapterMapper;
import ee.qrent.insurance.adapter.repository.InsuranceCaseBalanceRepository;
import ee.qrent.insurance.api.out.InsuranceCaseLoadPort;
import ee.qrent.insurance.core.mapper.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceMapperConfig {
  @Bean
  InsuranceCaseAddRequestMapper getInsuranceCaseAddRequestMapper(
      final GetQWeekQuery getQWeekQuery) {
    return new InsuranceCaseAddRequestMapper(getQWeekQuery);
  }

  @Bean
  InsuranceCaseResponseMapper getInsuranceCaseResponseMapper(
      final GetDriverQuery driverQuery,
      final GetCarQuery carQuery,
      final GetQWeekQuery qWeekQuery) {
    return new InsuranceCaseResponseMapper(driverQuery, carQuery, qWeekQuery);
  }

  @Bean
  InsuranceCaseUpdateRequestMapper getInsuranceCaseUpdateRequestMapper(
      final InsuranceCaseLoadPort loadPort) {
    return new InsuranceCaseUpdateRequestMapper(loadPort);
  }

  @Bean
  InsuranceCaseBalanceAdapterMapper getInsuranceCaseBalanceAdapterMapper(
      final InsuranceCaseAdapterMapper insuranceCaseAdapterMapper) {
    return new InsuranceCaseBalanceAdapterMapper(insuranceCaseAdapterMapper);
  }

  @Bean
  InsuranceCaseBalancePersistenceAdapter getInsuranceCaseBalancePersistenceAdapter(
      final InsuranceCaseBalanceRepository repository,
      final InsuranceCaseBalanceAdapterMapper mapper) {
    return new InsuranceCaseBalancePersistenceAdapter(repository, mapper);
  }

  @Bean
  InsuranceCaseBalanceResponseMapper getInsuranceCaseBalanceResponseMapper(
      final GetQWeekQuery qWeekQuery) {
    return new InsuranceCaseBalanceResponseMapper(qWeekQuery);
  }

  @Bean
  InsuranceCalculationAdapterMapper getInsuranceCalculationAdapterMapper() {
    return new InsuranceCalculationAdapterMapper();
  }

  @Bean
  InsuranceCalculationAddRequestMapper getInsuranceCalculationAddRequestMapper() {
    return new InsuranceCalculationAddRequestMapper();
  }

  @Bean
  InsuranceCalculationResponseMapper getInsuranceCalculationResponseMapper(
      final GetQWeekQuery qWeekQuery) {
    return new InsuranceCalculationResponseMapper(qWeekQuery);
  }
}
