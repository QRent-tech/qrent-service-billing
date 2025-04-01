package ee.qrent.insurance.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrent.insurance.domain.InsuranceCalculation;

public interface InsuranceCalculationLoadPort extends LoadPort<InsuranceCalculation> {
  Long loadLastCalculatedQWeekId();
}
