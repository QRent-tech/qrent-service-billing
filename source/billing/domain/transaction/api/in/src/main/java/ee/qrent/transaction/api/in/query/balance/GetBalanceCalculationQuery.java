package ee.qrent.transaction.api.in.query.balance;


import ee.qrent.transaction.api.in.response.balance.BalanceCalculationResponse;
import java.util.List;

public interface GetBalanceCalculationQuery {

  List<BalanceCalculationResponse> getAll();

  BalanceCalculationResponse getById(final Long id);

  Long getLastCalculatedQWeekId();
}