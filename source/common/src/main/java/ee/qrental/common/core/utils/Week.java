package ee.qrental.common.core.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record Week(LocalDate start, LocalDate end, int weekNumber) {
  public int getYear() {
    return start.getYear();
  }
  @Override
  public String toString() {
    return "Week{" +
            "start=" + start +
            ", end=" + end +
            ", weekNumber=" + weekNumber +
            '}';
  }
}
