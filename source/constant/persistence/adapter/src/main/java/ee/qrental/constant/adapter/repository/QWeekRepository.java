package ee.qrental.constant.adapter.repository;

import ee.qrental.constant.entity.jakarta.QWeekJakartaEntity;
import java.util.List;

public interface QWeekRepository {
  List<QWeekJakartaEntity> findAll();

  QWeekJakartaEntity save(final QWeekJakartaEntity entity);

  QWeekJakartaEntity getReferenceById(final Long id);

  void deleteById(final Long id);

  QWeekJakartaEntity findByYearAndWeekNumber(final Integer year, final Integer weekNumber);

  List<QWeekJakartaEntity> findByYear(final Integer year);

  List<Integer> findAllYears();
}
