package ee.qrent.firm.adapter.repository;

import ee.qrent.firm.entity.jakarta.FirmJakartaEntity;
import java.util.List;

public interface FirmRepository {
  List<FirmJakartaEntity> findAll();

  FirmJakartaEntity save(final FirmJakartaEntity entity);

  FirmJakartaEntity getReferenceById(final Long id);

  void deleteById(final Long id);
}
