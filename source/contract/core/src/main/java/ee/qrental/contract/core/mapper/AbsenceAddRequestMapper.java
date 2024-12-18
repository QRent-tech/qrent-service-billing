package ee.qrental.contract.core.mapper;

import ee.qrent.common.in.mapper.AddRequestMapper;
import ee.qrental.contract.api.in.request.AbsenceAddRequest;
import ee.qrental.contract.domain.Absence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsenceAddRequestMapper implements AddRequestMapper<AbsenceAddRequest, Absence> {

  @Override
  public Absence toDomain(final AbsenceAddRequest request) {

    return Absence.builder()
        .id(null)
        .driverId(request.getDriverId())
        .dateStart(request.getDateStart())
        .dateEnd(request.getDateEnd())
        .withCar(request.getWithCar())
        .reason(request.getReason())
        .comment(request.getComment())
        .build();
  }
}
