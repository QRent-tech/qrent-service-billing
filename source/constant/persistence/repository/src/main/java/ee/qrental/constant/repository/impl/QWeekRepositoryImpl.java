package ee.qrental.constant.repository.impl;

import ee.qrental.constant.adapter.repository.QWeekRepository;
import ee.qrental.constant.entity.jakarta.QWeekJakartaEntity;
import ee.qrental.constant.repository.spring.QWeekSpringDataRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QWeekRepositoryImpl implements QWeekRepository {

  private final QWeekSpringDataRepository springDataRepository;

  @Override
  public List<QWeekJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public QWeekJakartaEntity save(final QWeekJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public QWeekJakartaEntity getReferenceById(final Long id) {
    return springDataRepository.getReferenceById(id);
  }

  @Override
  public void deleteById(final Long id) {
    springDataRepository.deleteById(id);
  }

  @Override
  public QWeekJakartaEntity findByYearAndWeekNumber(final Integer year, final Integer weekNumber) {
    return springDataRepository.findByYearAndNumber(year, weekNumber);
  }

  @Override
  public List<QWeekJakartaEntity> findByYear(final Integer year) {
    return springDataRepository.findByYear(year);
  }

  @Override
  public List<QWeekJakartaEntity> findAllBetweenByIds(
      final Long startWeekId, final Long endWeekId) {
    return springDataRepository.findAllBetweenByIds(startWeekId, endWeekId);
  }

  @Override
  public List<QWeekJakartaEntity> findAllBeforeById(Long id) {
    return springDataRepository.findAllBeforeById(id);
  }

  @Override
  public List<Integer> findAllYears() {
    return springDataRepository.findYears();
  }

  @Override
  public List<QWeekJakartaEntity> findAllAfterById(final Long id) {
    return springDataRepository.findAllAfterById(id);
  }
}
