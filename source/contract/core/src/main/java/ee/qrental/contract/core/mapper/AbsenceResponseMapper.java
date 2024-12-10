package ee.qrental.contract.core.mapper;

import static java.lang.String.format;

import ee.qrent.common.in.mapper.ResponseMapper;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.response.AbsenceResponse;
import ee.qrental.contract.domain.Absence;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsenceResponseMapper implements ResponseMapper<AbsenceResponse, Absence> {
  private final GetQWeekQuery qWeekQuery;
  private final GetDriverQuery driverQuery;

  @Override
  public AbsenceResponse toResponse(final Absence domain) {
    if (domain == null) {
      return null;
    }
    final var qWeek = qWeekQuery.getById(domain.getQWeekId());
    final var driver = driverQuery.getById(domain.getDriverId());
    return AbsenceResponse.builder()
        .id(domain.getId())
        .qWeekId(domain.getQWeekId())
        .qWeekNumber(qWeek.getNumber())
        .qWeekYear(qWeek.getYear())
        .startDate(qWeek.getStart())
        .endDate(qWeek.getEnd())
        .driverId(domain.getDriverId())
        .driverFirstName(driver.getFirstName())
        .driverLastName(driver.getLastName())
        .driverIsikukood(driver.getIsikukood())
        .comment(domain.getComment())
        .build();
  }

  @Override
  public String toObjectInfo(final Absence domain) {

    final var qWeek = qWeekQuery.getById(domain.getQWeekId());
    final var driver = driverQuery.getById(domain.getDriverId());
    return format(
        "Absence for Driver: %s %s and week: %d, %d",
        driver.getFirstName(), driver.getLastName(), qWeek.getNumber(), qWeek.getYear());
  }
}
