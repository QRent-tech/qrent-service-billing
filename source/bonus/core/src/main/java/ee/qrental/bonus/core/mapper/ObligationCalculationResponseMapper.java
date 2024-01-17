package ee.qrental.bonus.core.mapper;

import ee.qrental.bonus.api.in.response.ObligationCalculationResponse;
import ee.qrental.bonus.domain.ObligationCalculation;
import ee.qrental.common.core.in.mapper.ResponseMapper;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObligationCalculationResponseMapper
    implements ResponseMapper<ObligationCalculationResponse, ObligationCalculation> {
  private final GetQWeekQuery qWeekQuery;

  @Override
  public ObligationCalculationResponse toResponse(ObligationCalculation domain) {
    if (domain == null) {
      return null;
    }

    final var qWeek = qWeekQuery.getById(domain.getQWeekId());

    return ObligationCalculationResponse.builder()
        .id(domain.getId())
        .actionDate(domain.getActionDate())
        .startDate(qWeek.getStart())
        .endDate(qWeek.getEnd())
        .year(qWeek.getYear())
        .weekNumber(qWeek.getNumber())
        .obligationsCount(domain.getResults().size())
        .comment(domain.getComment())
        .build();
  }

  @Override
  public String toObjectInfo(ObligationCalculation domain) {
    return String.format("Obligation Calculation was done at: %s", domain.getActionDate());
  }
}
