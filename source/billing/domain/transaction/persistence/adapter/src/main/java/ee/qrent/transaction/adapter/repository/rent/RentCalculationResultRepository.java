package ee.qrent.transaction.adapter.repository.rent;


import ee.qrent.transaction.entity.jakarta.rent.RentCalculationResultJakartaEntity;

public interface RentCalculationResultRepository {
  RentCalculationResultJakartaEntity save(final RentCalculationResultJakartaEntity entity);
}
