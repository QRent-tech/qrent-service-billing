package ee.qrental.bonus.adapter.repository;

import ee.qrental.bonus.entity.jakarta.ObligationJakartaEntity;
import java.util.List;

public interface ObligationRepository {
  List<ObligationJakartaEntity> findAll();

  ObligationJakartaEntity save(final ObligationJakartaEntity entity);

  ObligationJakartaEntity getReferenceById(final Long id);

  void deleteById(final Long id);


  ObligationJakartaEntity findByDriverIdAndByQWeekId(final Long driverId, final Long qWekId);

  List<ObligationJakartaEntity> findByIds(final List<Long> ids);
}
