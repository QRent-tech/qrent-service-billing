package ee.qrental.transaction.api.in.query.filter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PeriodAndKindAndDriverTransactionFilter {
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private Long driverId;
  private List<String> transactionKindCodes;
}
