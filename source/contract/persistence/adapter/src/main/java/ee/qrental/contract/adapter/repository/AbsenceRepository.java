package ee.qrental.contract.adapter.repository;

import ee.qrental.contract.entity.jakarta.AbsenceJakartaEntity;

import java.util.List;

public interface AbsenceRepository {

  AbsenceJakartaEntity findById(Long id);

  List<AbsenceJakartaEntity> findAll();

  AbsenceJakartaEntity save(final AbsenceJakartaEntity entity);

  void deleteById(final Long id);

  List<AbsenceJakartaEntity> findByDriverIdAndStartQWeekId(
      final Long driverId, final Long startQWeekId);
}
