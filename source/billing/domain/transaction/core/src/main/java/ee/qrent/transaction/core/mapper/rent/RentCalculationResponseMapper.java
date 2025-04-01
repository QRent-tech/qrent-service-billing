package ee.qrent.transaction.core.mapper.rent;

import ee.qrent.common.in.mapper.ResponseMapper;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.transaction.api.in.response.rent.RentCalculationResponse;
import ee.qrent.transaction.domain.rent.RentCalculation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentCalculationResponseMapper
    implements ResponseMapper<RentCalculationResponse, RentCalculation> {

  private final GetQWeekQuery qWeekQuery;

  @Override
  public RentCalculationResponse toResponse(final RentCalculation domain) {
    if (domain == null) {
      return null;
    }
    final var qWeek = qWeekQuery.getById(domain.getQWeekId());

    return RentCalculationResponse.builder()
        .id(domain.getId())
        .actionDate(domain.getActionDate())
        .startDate(qWeek.getStart())
        .endDate(qWeek.getEnd())
        .year(qWeek.getYear())
        .weekNumber(qWeek.getNumber())
        .transactionCount(domain.getResults().size())
        .comment(domain.getComment())
        .build();
  }

  @Override
  public String toObjectInfo(final RentCalculation domain) {
    return String.format("Rent Calculation was done at: %s", domain.getActionDate());
  }
}
