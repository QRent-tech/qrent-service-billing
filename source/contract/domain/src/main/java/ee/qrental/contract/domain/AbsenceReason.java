package ee.qrental.contract.domain;

import lombok.Getter;

public enum AbsenceReason {
  VACATION("Vacation"),
  SICK_LEAVE("Sick Leave"),
  ;

  @Getter private String label;

  AbsenceReason(final String label) {
    this.label = label;
  }
}
