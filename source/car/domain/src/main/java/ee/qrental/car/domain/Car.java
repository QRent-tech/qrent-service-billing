package ee.qrental.car.domain;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@SuperBuilder
@Getter
public class Car {

  // TODO add constant 5 years
  private Long id;
  private Boolean active;
  private CarStatus status;
  private Boolean qRent;
  private String regNumber;
  private String vin;
  private LocalDate releaseDate;
  private String manufacturer;
  private String model;
  private Boolean appropriation;
  private Boolean elegance;
  private String gearType;
  private String fuelType;
  private Boolean lpg;
  private LocalDate dateInstallLpg;
  private String insuranceFirm;
  private LocalDate insuranceDateStart;
  private LocalDate insuranceDateEnd;
  private Boolean sCard;
  private Boolean key2;
  private Boolean gps;
  private LocalDate technicalInspectionEnd;
  private LocalDate gasInspectionEnd;
  private LocalDate dateEndLpg;
  private RagStatus insuranceRagStatus;
  private RagStatus technicalInspectionRagStatus;
  private RagStatus gasInspectionRagStatus;
  private RagStatus warrantyRagStatus;
  private String comment;
  private Boolean brandingQrent;
  private Boolean brandingBolt;
  private Boolean brandingForus;
  private Boolean brandingUber;
  private Boolean brandingTallink;


  // TODO
  public boolean isOld(){
    //return LocalDate.now().isBefore(releaseDate) > 5;
    return true;
  }

  public RagStatus getInsuranceRagStatus() {
    final var daysBetween = DAYS.between(LocalDate.now(), insuranceDateEnd);

    return getRagStatusByDuration(daysBetween);
  }

  public RagStatus getTechnicalInspectionRagStatus() {
    final var daysBetween = DAYS.between(LocalDate.now(), technicalInspectionEnd);

    return getRagStatusByDuration(daysBetween);
  }

  public RagStatus getWarrantyRagStatus() {
    final var daysBetween = DAYS.between(releaseDate, LocalDate.now());
    return getRagStatusByDurationForWarranty(daysBetween);
  }

  public RagStatus getGasInspectionRagStatus() {
    if (gasInspectionEnd == null) {
      return RagStatus.GREEN;
    }

    final var daysBetween = DAYS.between(LocalDate.now(), gasInspectionEnd);

    return getRagStatusByDuration(daysBetween);
  }

  private RagStatus getRagStatusByDuration(final Long durationInDays) {
    if (durationInDays >= 30) {

      return RagStatus.GREEN;
    }

    if (durationInDays > 0 && durationInDays < 30) {

      return RagStatus.AMBER;
    }

    return RagStatus.RED;
  }

  private RagStatus getRagStatusByDurationForWarranty(final Long durationInDays) {
    if (durationInDays < 550) {

      return RagStatus.GREEN;
    }

    if (durationInDays > 610 && durationInDays < 730) {

      return RagStatus.AMBER;
    }

    return RagStatus.RED;
  }
}
