package ee.qrental.contract.core.service.pdf;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class ContractPdfModel {
  private Long driverId;
  private Integer durationWeeksCount;
  private String number;
  private String duration;
  private String duration1;
  private String renterName;
  private String renterRegistrationNumber;
  private String renterAddress;
  private String renterCeoName;
  private Long renterCeoTaxNumber;
  private String renterPhone;
  private String renterEmail;
  private Long driverTaxNumber;
  private String driverLicenceNumber;
  private String qFirmName;
  private String qFirmPostAddress;
  private String qFirmRegNumber;
  private String qFirmVatNumber;
  private String qFirmIban;
  private String qFirmEmail;
  private String qFirmPhone;
  private String qFirmCeo;
  private String driverAddress;
  private String carVin;
  private String carManufacturer;
  private String carModel;
  private LocalDate created;
}
