package ee.qrent.insurance.core.mapper;

import ee.qrent.insurance.api.in.request.InsuranceCalculationAddRequest;
import ee.qrent.insurance.domain.InsuranceCalculation;

import java.util.ArrayList;

public class InsuranceCalculationAddRequestMapper {

  public InsuranceCalculation toDomain(final InsuranceCalculationAddRequest request) {
    if (request == null) {

      return null;
    }

    return InsuranceCalculation.builder()
        .actionDate(request.getActionDate())
        .qWeekId(request.getQWeekId())
        .insuranceCaseBalances(new ArrayList<>())
        .comment(request.getComment())
        .build();
  }
}
