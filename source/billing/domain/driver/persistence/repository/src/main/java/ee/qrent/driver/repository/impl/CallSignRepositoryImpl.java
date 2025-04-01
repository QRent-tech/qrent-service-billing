package ee.qrent.driver.repository.impl;

import ee.qrent.driver.adapter.repository.CallSignRepository;
import ee.qrent.driver.entity.jakarta.CallSignJakartaEntity;
import ee.qrent.driver.repository.spring.CallSignSpringDataRepository;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignRepositoryImpl implements CallSignRepository {

  private final CallSignSpringDataRepository springDataRepository;

  @Override
  public List<CallSignJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public List<CallSignJakartaEntity> findAvailable(final LocalDate nowDate) {
    return springDataRepository.findAvailable(nowDate);
  }

  @Override
  public CallSignJakartaEntity save(final CallSignJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public CallSignJakartaEntity getReferenceById(final Long id) {
    return springDataRepository.getReferenceById(id);
  }

  @Override
  public void deleteById(final Long id) {
    springDataRepository.deleteById(id);
  }

  @Override
  public CallSignJakartaEntity findByCallSign(final Integer callSign) {
    return springDataRepository.findByCallSign(callSign);
  }
}
