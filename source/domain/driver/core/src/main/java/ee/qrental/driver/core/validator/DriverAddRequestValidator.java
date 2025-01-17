package ee.qrental.driver.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.request.DriverAddRequest;
import ee.qrental.driver.api.out.DriverLoadPort;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class DriverAddRequestValidator implements AddRequestValidator<DriverAddRequest> {

  private final DriverLoadPort loadPort;
  private final GetQWeekQuery qWeekQuery;
  private final QDateTime qDateTime;

  private static final BigDecimal MIN_VALUE_OBLIGATION = BigDecimal.valueOf(1);
  private static final BigDecimal MAX_VALUE_OBLIGATION = BigDecimal.valueOf(1000);
  private static final int MAX_LENGTH_FIRST_NAME = 50;
  private static final int MAX_LENGTH_LAST_NAME = 50;
  private static final int FIXED_LENGTH_TAX_NUMBER = 11;
  private static final int MAX_LENGTH_ADDRESS = 100;
  private static final int MAX_LENGTH_LICENSE_NUMBER = 20;
  private static final int MAX_LENGTH_TAXI_LICENSE = 15;
  private static final int MIN_LENGTH_PHONE_NUMBER = 5;
  private static final int MAX_LENGTH_PHONE_NUMBER = 50;
  private static final int MAX_LENGTH_COMPANY_NAME = 50;
  private static final int MAX_LENGTH_REG_NUMBER = 15;
  private static final int MAX_LENGTH_VAT = 50;
  private static final int MAX_LENGTH_CEO_FIRST_NAME = 50;
  private static final int MAX_LENGTH_CEO_LAST_NAME = 50;
  private static final int FIXED_LENGTH_CEO_TAX_NUMBER = 11;
  private static final int MAX_LENGTH_COMPANY_ADDRESS = 50;
  private static final int MAX_LENGTH_COMMENT = 200;

  @Override
  public ViolationsCollector validate(final DriverAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkObligationValidNumber(request, violationsCollector);
    checkFirstNameValid(request, violationsCollector);
    checkLastNameValid(request, violationsCollector);
    checkIsikukoodValid(request, violationsCollector);
    checkAddressLength(request, violationsCollector);
    checkLicenseNumberValid(request, violationsCollector);
    checkLicenseExpirationDateValid(request, violationsCollector);
    checkTaxiLicenseValid(request, violationsCollector);
    checkPhoneNumberValid(request, violationsCollector);
    checkEmailValid(request, violationsCollector);
    checkCompanyNameValid(request, violationsCollector);
    checkRegistrationNumberValid(request, violationsCollector);
    checkCompanyVatValid(request, violationsCollector);
    checkCeoFirstNameValid(request, violationsCollector);
    checkCeoLastNameValid(request, violationsCollector);
    checkCeoIsikukoodValid(request, violationsCollector);
    checkCompanyAddressValid(request, violationsCollector);
    checkCommentValid(request, violationsCollector);

    return violationsCollector;
  }

  private void checkObligationValidNumber(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    if (!request.getHasRequiredObligation()) {
      return;
    }
    final var obligation = request.getRequiredObligation();
    if (obligation.compareTo(MIN_VALUE_OBLIGATION) >= 0
        && obligation.compareTo(MAX_VALUE_OBLIGATION) <= 0) {
      return;
    }
    violationsCollector.collect(
        format(
            "Invalid Obligation (Min %s and Max %s)", MIN_VALUE_OBLIGATION, MAX_VALUE_OBLIGATION));
  }

  private void checkFirstNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var firstName = request.getFirstName();
    if (firstName == null) {
      return;
    }
    if (firstName.length() <= MAX_LENGTH_FIRST_NAME) {
      return;
    }
    violationsCollector.collect(
        format("Too long First name (Max %d characters)", MAX_LENGTH_FIRST_NAME));
  }

  private void checkLastNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var lastName = request.getLastName();
    if (lastName == null) {
      return;
    }
    if (lastName.length() <= MAX_LENGTH_LAST_NAME) {
      return;
    }
    violationsCollector.collect(
        format("Too long Last name (Max %d characters)", MAX_LENGTH_LAST_NAME));
  }

  private void checkIsikukoodValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var taxNumber = request.getTaxNumber();
    if (taxNumber == null) {
      return;
    }
    if (taxNumber.toString().length() != FIXED_LENGTH_TAX_NUMBER) {
      violationsCollector.collect(
          format("Isikukood must be %d characters long", FIXED_LENGTH_TAX_NUMBER));
      return;
    }
    final var fromDb = loadPort.loadByTaxNumber(taxNumber);
    if (fromDb == null) {
      return;
    }
    violationsCollector.collect(format("Isikukood %d already exists", taxNumber));
  }

  private void checkAddressLength(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var address = request.getAddress();
    if (address == null) {
      return;
    }
    if (address.length() <= MAX_LENGTH_ADDRESS) {
      return;
    }
    violationsCollector.collect(format("Too long Address (Max %d characters)", MAX_LENGTH_ADDRESS));
  }

  private void checkLicenseNumberValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var licenseNumber = request.getDriverLicenseNumber();
    if (licenseNumber == null) {
      return;
    }
    if (licenseNumber.length() <= MAX_LENGTH_LICENSE_NUMBER) {
      return;
    }
    violationsCollector.collect(
        format("Too long License number (Max %d characters)", MAX_LENGTH_LICENSE_NUMBER));
  }

  private void checkLicenseExpirationDateValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var licenseExpirationDate = request.getDriverLicenseExp();
    if (licenseExpirationDate == null) {
      return;
    }
    if (!licenseExpirationDate.isBefore(qDateTime.getToday())) {
      return;
    }
    violationsCollector.collect("License expiration date is in the past");
  }

  private void checkTaxiLicenseValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var taxiLicense = request.getTaxiLicense();
    if (taxiLicense == null) {
      return;
    }
    if (taxiLicense.length() <= MAX_LENGTH_TAXI_LICENSE) {
      return;
    }
    violationsCollector.collect(
        format("Too long Taxi license (Max %d characters)", MAX_LENGTH_TAXI_LICENSE));
  }

  private void checkPhoneNumberValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var phoneNumber = request.getPhone();
    if (phoneNumber == null) {
      return;
    }
    if (phoneNumber.length() >= MIN_LENGTH_PHONE_NUMBER
        && phoneNumber.length() <= MAX_LENGTH_PHONE_NUMBER) {
      return;
    }
    violationsCollector.collect(
        format(
            "Invalid Phone number (Min %d and Max %d)",
            MIN_LENGTH_PHONE_NUMBER, MAX_LENGTH_PHONE_NUMBER));
  }

  private void checkEmailValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var email = request.getEmail();
    if (email == null) {
      return;
    }
    if (isValidEmail(email)) {
      return;
    }
    violationsCollector.collect("Invalid email (Example email@gmail.com)");
  }

  private void checkCompanyNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var companyName = request.getCompanyName();
    if (companyName == null) {
      return;
    }
    if (companyName.length() <= MAX_LENGTH_COMPANY_NAME) {
      return;
    }
    violationsCollector.collect(
        format("Too long Company name (Max %d characters)", MAX_LENGTH_COMPANY_NAME));
  }

  private void checkRegistrationNumberValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var regNumber = request.getRegNumber();
    if (regNumber == null) {
      return;
    }
    if (regNumber.length() <= MAX_LENGTH_REG_NUMBER) {
      return;
    }
    violationsCollector.collect(
        format("Too long Registration number (Max %d characters)", MAX_LENGTH_REG_NUMBER));
  }

  private void checkCompanyVatValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var vat = request.getCompanyVat();
    if (vat == null) {
      return;
    }
    if (vat.length() <= MAX_LENGTH_VAT) {
      return;
    }
    violationsCollector.collect(format("Too long Company VAT (Max %d characters)", MAX_LENGTH_VAT));
  }

  private void checkCeoFirstNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var ceoFirstName = request.getCompanyCeoFirstName();
    if (ceoFirstName == null) {
      return;
    }
    if (ceoFirstName.length() <= MAX_LENGTH_CEO_FIRST_NAME) {
      return;
    }
    violationsCollector.collect(
        format("Too long CEO First name (Max %d characters)", MAX_LENGTH_CEO_FIRST_NAME));
  }

  private void checkCeoLastNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var ceoLastName = request.getCompanyCeoLastName();

    if (ceoLastName == null) {
      return;
    }
    if (ceoLastName.length() <= MAX_LENGTH_CEO_LAST_NAME) {
      return;
    }
    violationsCollector.collect(
        format("Too long CEO Last name (Max %d characters)", MAX_LENGTH_CEO_LAST_NAME));
  }

  private void checkCeoIsikukoodValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var ceoIsikukood = request.getCompanyCeoTaxNumber();
    if (ceoIsikukood == null) {
      return;
    }
    if (String.valueOf(ceoIsikukood).length() != FIXED_LENGTH_CEO_TAX_NUMBER) {
      return;
    }
    violationsCollector.collect(
        format("CEO Isikukood must be %d characters long", FIXED_LENGTH_CEO_TAX_NUMBER));
  }

  private void checkCompanyAddressValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var companyAddress = request.getCompanyAddress();
    if (companyAddress == null) {
      return;
    }
    if (companyAddress.length() <= MAX_LENGTH_COMPANY_ADDRESS) {
      return;
    }
    violationsCollector.collect(
        format("Too long Company address (Max %d characters)", MAX_LENGTH_COMPANY_ADDRESS));
  }

  private void checkCommentValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var comment = request.getComment();
    if (comment == null) {
      return;
    }
    if (comment.length() <= MAX_LENGTH_COMMENT) {
      return;
    }
    violationsCollector.collect(format("Too long Comment (Max %d characters)", MAX_LENGTH_COMMENT));
  }

  private boolean isValidEmail(String input) {
    final var emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    return input.matches(emailRegex);
  }
}
