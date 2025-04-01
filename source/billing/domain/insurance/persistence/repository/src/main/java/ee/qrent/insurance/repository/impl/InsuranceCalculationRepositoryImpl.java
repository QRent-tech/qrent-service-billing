package ee.qrent.insurance.repository.impl;

import ee.qrent.insurance.adapter.repository.InsuranceCalculationRepository;
import ee.qrent.insurance.entity.jakarta.InsuranceCalculationJakartaEntity;
import ee.qrent.insurance.repository.spring.InsuranceCalculationSpringDataRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class InsuranceCalculationRepositoryImpl implements InsuranceCalculationRepository {
  private final InsuranceCalculationSpringDataRepository springDataRepository;

  @Override
  public InsuranceCalculationJakartaEntity save(InsuranceCalculationJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public List<InsuranceCalculationJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public InsuranceCalculationJakartaEntity getReferenceById(Long id) {
    return springDataRepository.getReferenceById(id);
  }

  @Override
  public Long getLastCalculatedQWeekId() {
    return springDataRepository.getLastCalculationQWeekId();
  }
}
