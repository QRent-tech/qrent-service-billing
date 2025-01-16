package ee.qrental.driver.adapter.repository;

import ee.qrental.driver.entity.jakarta.DriverJakartaEntity;

import java.util.Collection;
import java.util.List;

public interface DriverRepository {
  List<DriverJakartaEntity> findAll();

  DriverJakartaEntity save(final DriverJakartaEntity entity);

  DriverJakartaEntity findById(final Long id);

  DriverJakartaEntity findByTaxNumber(final Long taxNumber);

  void deleteById(final Long id);

  List<DriverJakartaEntity> findAllByMatchCountAndQWeekId(
      final Integer matchCount, final Long qWeekId);
}
