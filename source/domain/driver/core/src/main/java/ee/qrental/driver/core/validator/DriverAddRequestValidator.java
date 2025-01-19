package ee.qrental.driver.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.AttributeChecker;
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
  private final AttributeChecker attributeChecker;

  private static final BigDecimal VALUE_MIN_OBLIGATION = BigDecimal.valueOf(1);
  private static final BigDecimal VALUE_MAX_OBLIGATION = BigDecimal.valueOf(1000);
  private static final int LENGTH_MAX_FIRST_NAME = 50;
  private static final int LENGTH_MAX_LAST_NAME = 50;
  private static final int LENGTH_FIXED_TAX_NUMBER = 11;
  private static final int LENGTH_MAX_ADDRESS = 100;
  private static final int LENGTH_MAX_DRIVER_LICENSE_NUMBER = 20;
  private static final int LENGTH_MAX_TAXI_LICENSE_NUMBER = 15;
  private static final int LENGTH_MIN_PHONE_NUMBER = 5;
  private static final int LENGTH_MAX_PHONE_NUMBER = 50;
  private static final int LENGTH_MAX_COMPANY_NAME = 50;
  private static final int LENGTH_MAX_REG_NUMBER = 15;
  private static final int LENGTH_MAX_VAT = 50;
  private static final int LENGTH_MAX_CEO_FIRST_NAME = 50;
  private static final int LENGTH_MAX_CEO_LAST_NAME = 50;
  private static final int LENGTH_FIXED_CEO_TAX_NUMBER = 11;
  private static final int LENGTH_MAX_COMPANY_ADDRESS = 50;
  private static final int LENGTH_MAX_COMMENT = 200;

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
    if (obligation.compareTo(VALUE_MIN_OBLIGATION) >= 0
        && obligation.compareTo(VALUE_MAX_OBLIGATION) <= 0) {
      return;
    }
    violationsCollector.collect(
        format(
            "Invalid Obligation (Min %s and Max %s)", VALUE_MIN_OBLIGATION, VALUE_MAX_OBLIGATION));
  }

  private void checkFirstNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "First name", request.getFirstName(), null, LENGTH_MAX_FIRST_NAME, violationsCollector);
  }

  private void checkLastNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    attributeChecker.checkLength(
        "Last name", request.getLastName(), null, LENGTH_MAX_LAST_NAME, violationsCollector);
  }

  private void checkIsikukoodValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var taxNumber = request.getTaxNumber();
    if (taxNumber == null) {
      return;
    }
    if (taxNumber.toString().length() != LENGTH_FIXED_TAX_NUMBER) {
      violationsCollector.collect(
          format("Isikukood must be %d characters long", LENGTH_FIXED_TAX_NUMBER));
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
    attributeChecker.checkLength(
        "Address", request.getAddress(), null, LENGTH_MAX_ADDRESS, violationsCollector);
  }

  private void checkLicenseNumberValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    attributeChecker.checkLength(
        "Driver License number",
        request.getDriverLicenseNumber(),
        null,
        LENGTH_MAX_DRIVER_LICENSE_NUMBER,
        violationsCollector);
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
    attributeChecker.checkLength(
        "Taxi license number",
        request.getTaxiLicense(),
        null,
        LENGTH_MAX_TAXI_LICENSE_NUMBER,
        violationsCollector);
  }

  private void checkPhoneNumberValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Phone number",
        request.getPhone(),
        LENGTH_MIN_PHONE_NUMBER,
        LENGTH_MAX_PHONE_NUMBER,
        violationsCollector);
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
    attributeChecker.checkLength(
        "Company name",
        request.getCompanyName(),
        null,
        LENGTH_MAX_COMPANY_NAME,
        violationsCollector);
  }

  private void checkRegistrationNumberValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Registration number",
        request.getRegNumber(),
        null,
        LENGTH_MAX_REG_NUMBER,
        violationsCollector);
  }

  private void checkCompanyVatValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Company VAT number", request.getCompanyVat(), null, LENGTH_MAX_VAT, violationsCollector);
  }

  private void checkCeoFirstNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "CEO First name",
        request.getCompanyCeoFirstName(),
        null,
        LENGTH_MAX_CEO_FIRST_NAME,
        violationsCollector);
  }

  private void checkCeoLastNameValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "CEO Last name",
        request.getCompanyCeoLastName(),
        null,
        LENGTH_MAX_CEO_LAST_NAME,
        violationsCollector);
  }

  private void checkCeoIsikukoodValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var ceoIsikukood = request.getCompanyCeoTaxNumber();
    if (ceoIsikukood == null) {
      return;
    }
    if (String.valueOf(ceoIsikukood).length() != LENGTH_FIXED_CEO_TAX_NUMBER) {
      return;
    }
    violationsCollector.collect(
        format("CEO Isikukood must be %d characters long", LENGTH_FIXED_CEO_TAX_NUMBER));
  }

  private void checkCompanyAddressValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Company address",
        request.getCompanyAddress(),
        null,
        LENGTH_MAX_COMPANY_ADDRESS,
        violationsCollector);
  }

  private void checkCommentValid(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Comment", request.getComment(), null, LENGTH_MAX_COMMENT, violationsCollector);
  }

  private boolean isValidEmail(String input) {
    final var emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    return input.matches(emailRegex);
  }
}
