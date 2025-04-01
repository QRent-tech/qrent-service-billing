package ee.qrent.transaction.adapter.repository.rent;


import ee.qrent.transaction.entity.jakarta.rent.RentCalculationJakartaEntity;

import java.util.List;

public interface RentCalculationRepository {
  RentCalculationJakartaEntity save(final RentCalculationJakartaEntity entity);

  List<RentCalculationJakartaEntity> findAll();

  RentCalculationJakartaEntity getReferenceById(final Long id);

  Long getLastCalculationQWeekId();
}
