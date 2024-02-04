package ee.qrental.bonus.core.mapper;

import ee.qrental.bonus.api.in.response.BonusCalculationResponse;
import ee.qrental.bonus.domain.BonusCalculation;
import ee.qrental.common.core.in.mapper.ResponseMapper;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BonusCalculationResponseMapper
    implements ResponseMapper<BonusCalculationResponse, BonusCalculation> {
  private final GetQWeekQuery qWeekQuery;

  @Override
  public BonusCalculationResponse toResponse(BonusCalculation domain) {
    if (domain == null) {
      return null;
    }

    final var qWeek = qWeekQuery.getById(domain.getQWeekId());

    return BonusCalculationResponse.builder()
        .id(domain.getId())
        .actionDate(domain.getActionDate())
        .startDate(qWeek.getStart())
        .endDate(qWeek.getEnd())
        .year(qWeek.getYear())
        .weekNumber(qWeek.getNumber())
        .transactionsCount(domain.getResults().size())
        .comment(domain.getComment())
        .build();
  }

  @Override
  public String toObjectInfo(BonusCalculation domain) {
    return String.format("Bonus Calculation was done at: %s", domain.getActionDate());
  }
}
