package ee.qrental.bonus.adapter.mapper;

import ee.qrental.bonus.domain.ObligationCalculation;
import ee.qrental.bonus.domain.ObligationCalculationResult;
import ee.qrental.bonus.entity.jakarta.ObligationCalculationJakartaEntity;
import ee.qrental.bonus.entity.jakarta.ObligationCalculationResultJakartaEntity;

public class ObligationCalculationAdapterMapper {
  public ObligationCalculation mapToDomain(final ObligationCalculationJakartaEntity entity) {
    if (entity == null) {
      return null;
    }
    return ObligationCalculation.builder()
        .id(entity.getId())
        .qWeekId(entity.getQWeekId())
        .actionDate(entity.getActionDate())
        .results(entity.getResults().stream().map(this::mapToDomain).toList())
        .comment(entity.getComment())
        .build();
  }

  private ObligationCalculationResult mapToDomain(
      final ObligationCalculationResultJakartaEntity resultEntity) {

    return ObligationCalculationResult.builder()
        .obligationId(resultEntity.getObligationId())
        .calculationId(resultEntity.getObligationCalculation().getId())
        .build();
  }
}
