package ee.qrental.contract.adapter.mapper;

import ee.qrental.contract.domain.Absence;
import ee.qrental.contract.entity.jakarta.AbsenceJakartaEntity;

public class AbsenceAdapterMapper {

  public Absence mapToDomain(final AbsenceJakartaEntity entity) {
    if (entity == null) {

      return null;
    }

    return Absence.builder()
        .id(entity.getId())
        .driverId(entity.getDriverId())
        .qWeekId(entity.getQWeekId())
        .comment(entity.getComment())
        .build();
  }

  public AbsenceJakartaEntity mapToEntity(final Absence domain) {
    if (domain == null) {

      return null;
    }

    return AbsenceJakartaEntity.builder()
        .id(domain.getId())
        .driverId(domain.getDriverId())
        .qWeekId(domain.getQWeekId())
        .comment(domain.getComment())
        .build();
  }
}
