package ee.qrental.contract.repository.impl;

import ee.qrental.contract.adapter.repository.AbsenceRepository;
import ee.qrental.contract.entity.jakarta.AbsenceJakartaEntity;
import ee.qrental.contract.repository.spring.AbsenceSpringDataRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AbsenceRepositoryImpl implements AbsenceRepository {
  private final AbsenceSpringDataRepository springDataRepository;

  @Override
  public AbsenceJakartaEntity findById(Long id) {
    return springDataRepository.getReferenceById(id);
  }

  @Override
  public List<AbsenceJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public AbsenceJakartaEntity save(final AbsenceJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public void deleteById(final Long id) {
    springDataRepository.deleteById(id);
  }

  @Override
  public List<AbsenceJakartaEntity> findByDriverIdAndStartQWeekId(
      final Long driverId, final Long startQWeekId) {
    return springDataRepository.findByDriverIdAndStartQWeekId(driverId, startQWeekId);
  }
}
