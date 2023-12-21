package ee.qrental.driver.entity.jakarta;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "driver")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverJakartaEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "isikukood")
  private Long isikukood;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "company_ceo_first_name")
  private String companyCeoFirstName;

  @Column(name = "company_ceo_last_name")
  private String companyCeoLastName;

  @Column(name = "company_ceo_tax_number")
  private Long companyCeoTaxNumber;

  @Column(name = "company_registration_number")
  private String companyRegistrationNumber;

  @Column(name = "company_address")
  private String companyAddress;

  @Column(name = "company_vat")
  private String companyVat;

  @Column(name = "driver_license_number")
  private String driverLicenseNumber;

  @Column(name = "driver_license_exp")
  private LocalDate driverLicenseExp;

  @Column(name = "taxi_license")
  private String taxiLicense;

  @Column(name = "need_invoices_by_email")
  private Boolean needInvoicesByEmail;

  @Column(name = "need_fee")
  private Boolean needFee;

  @Column(name = "by_telegram")
  private Boolean byTelegram;

  @Column(name = "by_whatsapp")
  private Boolean byWhatsApp;

  @Column(name = "by_viber")
  private Boolean byViber;

  @Column(name = "by_email")
  private Boolean byEmail;

  @Column(name = "by_sms")
  private Boolean bySms;

  @Column(name = "by_phone")
  private Boolean byPhone;

  @Column(name = "q_firm_id")
  private Long qFirmId;

  @Column(name = "deposit")
  private BigDecimal deposit;

  @Column(name = "comment")
  private String comment;

  @Column(name = "created_date")
  private LocalDate createdDate;

  @OneToMany(mappedBy = "driver")
  private List<CallSignLinkJakartaEntity> callSignLinks;
}
