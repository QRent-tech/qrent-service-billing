package ee.qrent.transaction.core.mapper.rent;

import ee.qrent.common.in.mapper.AddRequestMapper;
import ee.qrent.transaction.api.in.request.rent.RentCalculationAddRequest;
import ee.qrent.transaction.domain.rent.RentCalculation;

import java.time.LocalDate;
import java.util.ArrayList;

public class RentCalculationAddRequestMapper
    implements AddRequestMapper<RentCalculationAddRequest, RentCalculation> {

  public RentCalculation toDomain(final RentCalculationAddRequest request) {
    final var actionDate = LocalDate.now();

    return RentCalculation.builder()
        .actionDate(actionDate)
        .qWeekId(request.getQWeekId())
        .results(new ArrayList<>())
        .comment(request.getComment())
        .build();
  }
}
