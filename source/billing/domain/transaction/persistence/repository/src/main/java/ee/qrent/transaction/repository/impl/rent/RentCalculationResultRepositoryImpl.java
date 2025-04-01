package ee.qrent.transaction.repository.impl.rent;

import ee.qrent.transaction.adapter.repository.rent.RentCalculationResultRepository;
import ee.qrent.transaction.entity.jakarta.rent.RentCalculationResultJakartaEntity;
import ee.qrent.transaction.repository.spring.rent.RentCalculationResultSpringDataRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentCalculationResultRepositoryImpl implements RentCalculationResultRepository {

  private final RentCalculationResultSpringDataRepository springDataRepository;

  @Override
  public RentCalculationResultJakartaEntity save(
      final RentCalculationResultJakartaEntity entity) {
    return springDataRepository.save(entity);
  }
}
