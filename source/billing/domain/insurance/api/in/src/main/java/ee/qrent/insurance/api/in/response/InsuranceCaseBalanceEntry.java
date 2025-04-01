package ee.qrent.insurance.api.in.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class InsuranceCaseBalanceEntry {
  private InsuranceCaseBalanceResponse previousWeekBalance;
  private InsuranceCaseBalanceResponse requestedWeekBalance;
}
