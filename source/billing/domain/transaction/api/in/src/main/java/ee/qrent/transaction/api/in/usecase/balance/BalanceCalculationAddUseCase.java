package ee.qrent.transaction.api.in.usecase.balance;


import ee.qrent.transaction.api.in.request.balance.BalanceCalculationAddRequest;

public interface BalanceCalculationAddUseCase {

  void add(final BalanceCalculationAddRequest request);
}
