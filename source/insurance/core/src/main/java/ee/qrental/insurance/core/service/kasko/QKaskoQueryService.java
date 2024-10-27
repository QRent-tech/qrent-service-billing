package ee.qrental.insurance.core.service.kasko;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.insurance.api.in.query.GetQKaskoQuery;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QKaskoQueryService implements GetQKaskoQuery {
  private static final Integer MIN_WEEKS_CONTRACT_DURATION_REQUIRED_FOR_Q_KASKO = 12;

  private final GetContractQuery contractQuery;
  private final GetQWeekQuery qWeekQuery;

  @Override
  public boolean hasQKasko(final Long driverId, final Long qWeekId) {
    final var latestContract = contractQuery.getLatestContractByDriverId(driverId);
    if (latestContract == null) {

      return false;
    }
    if (latestContract.getDuration() < MIN_WEEKS_CONTRACT_DURATION_REQUIRED_FOR_Q_KASKO) {

      return false;
    }

    final var contractStartDate = latestContract.getDateStart();
    final var contractEndDate = latestContract.getDateEnd();
    final var qWeek = qWeekQuery.getById(qWeekId);
    final var qWeekStartDate = qWeek.getStart();
    final var qWeekEndDate = qWeek.getEnd();

    if (contractStartDate.isEqual(qWeekEndDate) || contractStartDate.isAfter(qWeekEndDate)) {
      return false;
    }

    if (contractStartDate.isEqual(qWeekEndDate) || contractStartDate.isAfter(qWeekEndDate)) {
      return false;
    }

    if (contractEndDate != null && contractEndDate.isEqual(qWeekStartDate)) {
      return false;
    }

    if (contractEndDate != null && contractEndDate.isBefore(qWeekStartDate)) {
      return false;
    }

    return true;
  }
}
