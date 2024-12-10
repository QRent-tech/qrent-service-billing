package ee.qrental.contract.core.mapper;

import ee.qrent.common.in.mapper.UpdateRequestMapper;
import ee.qrental.contract.api.in.request.AbsenceUpdateRequest;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.domain.Absence;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsenceUpdateRequestMapper
    implements UpdateRequestMapper<AbsenceUpdateRequest, Absence> {

  private final AbsenceLoadPort loadPort;

  @Override
  public Absence toDomain(final AbsenceUpdateRequest request) {
    final var absenceFromDb = loadPort.loadById(request.getId());
    absenceFromDb.setDriverId(request.getDriverId());
    absenceFromDb.setQWeekId(request.getQWeekId());
    absenceFromDb.setComment(request.getComment());

    return absenceFromDb;
  }

  @Override
  public AbsenceUpdateRequest toRequest(final Absence domain) {
    return AbsenceUpdateRequest.builder()
        .id(domain.getId())
        .qWeekId(domain.getQWeekId())
        .driverId(domain.getDriverId())
        .comment(domain.getComment())
        .build();
  }
}
