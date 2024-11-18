package ee.qrental.driver.repository.impl;

import ee.qrental.driver.adapter.repository.DriverRepository;
import ee.qrental.driver.entity.jakarta.DriverJakartaEntity;
import ee.qrental.driver.repository.spring.DriverSpringDataRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DriverRepositoryImpl implements DriverRepository {

  private final DriverSpringDataRepository springDataRepository;

  @Override
  public List<DriverJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public DriverJakartaEntity save(final DriverJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public DriverJakartaEntity getReferenceById(final Long id) {
    return springDataRepository.getReferenceById(id);
  }

  @Override
  public void deleteById(final Long id) {
    springDataRepository.deleteById(id);
  }

  @Override
  public List<DriverJakartaEntity> findAllByMatchCountAndQWeekId(
      final Integer matchCount, final Long qWeekId) {
    return springDataRepository.findAllByMatchCountAndQWeekId(matchCount, qWeekId);
  }
}
