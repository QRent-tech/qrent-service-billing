package ee.qrent.transaction.api.in.query;

import ee.qrent.common.in.query.BaseGetQuery;
import ee.qrent.transaction.api.in.query.filter.PeriodAndKindAndDriverTransactionFilter;
import ee.qrent.transaction.api.in.query.filter.PeriodFilter;
import ee.qrent.transaction.api.in.query.filter.YearAndWeekAndDriverAndFeeFilter;
import ee.qrent.transaction.api.in.request.TransactionUpdateRequest;
import ee.qrent.transaction.api.in.response.TransactionResponse;
import java.util.List;

public interface GetTransactionQuery
    extends BaseGetQuery<TransactionUpdateRequest, TransactionResponse> {

  List<TransactionResponse> getAllByDriverId(final Long driverId);

  List<TransactionResponse> getAllByDriverIdAndQWeekId(final Long driverId, final Long qWeekId);

  List<TransactionResponse> getAllByIds(final List<Long> ids);

  List<TransactionResponse> getAllByRentCalculationId(final Long rentCalculationId);

  List<TransactionResponse> getAllByBonusCalculationId(final Long bonusCalculationId);

  List<TransactionResponse> getAllByInsuranceCalculationId(final Long insuranceCalculationId);

  List<TransactionResponse> getAllByInsuranceCaseId(final Long insuranceCaseId);

  List<TransactionResponse> getAllByFilter(final YearAndWeekAndDriverAndFeeFilter filter);

  List<TransactionResponse> getAllByFilter(final PeriodAndKindAndDriverTransactionFilter filter);

  List<TransactionResponse> getAllByFilter(final PeriodFilter filter);

  List<TransactionResponse> getAllByQWeekId(final Long qWeekId);
}
