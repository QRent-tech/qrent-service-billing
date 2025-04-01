package ee.qrent.transaction.api.in.usecase.damage;


import ee.qrent.transaction.api.in.request.rent.RentCalculationAddRequest;

public interface DamageCalculationAddUseCase {

  void add(final RentCalculationAddRequest request);
}
