package ee.qrental.contract.api.in.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
public class AbsenceResponse {
  private Long id;

  private Long driverId;
  private String driverFirstName;
  private String driverLastName;
  private Long driverIsikukood;

  private Long qWeekId;
  private Integer qWeekNumber;
  private Integer qWeekYear;
  private LocalDate startDate;
  private LocalDate endDate;

  private String comment;
}
