package ee.qrent.transaction.api.out.rent;

import ee.qrent.common.out.port.LoadPort;
import ee.qrent.transaction.domain.rent.RentCalculation;

public interface RentCalculationLoadPort extends LoadPort<RentCalculation> {
  Long loadLastCalculationQWeekId();
}
