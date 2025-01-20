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
    checkObligationNumber(request, violationsCollector);
    checkFirstName(request, violationsCollector);
    checkLastName(request, violationsCollector);
    checkTaxNumber(request, violationsCollector);
    checkAddress(request, violationsCollector);
    checkLicenseNumber(request, violationsCollector);
    checkLicenseExpirationDate(request, violationsCollector);
    checkTaxiLicense(request, violationsCollector);
    checkPhoneNumber(request, violationsCollector);
    checkEmail(request, violationsCollector);
    checkCompanyName(request, violationsCollector);
    checkRegistrationNumber(request, violationsCollector);
    checkCompanyVat(request, violationsCollector);
    checkCeoFirstName(request, violationsCollector);
    checkCeoLastName(request, violationsCollector);
    checkCeoIsikukood(request, violationsCollector);
    checkCompanyAddress(request, violationsCollector);
    checkComment(request, violationsCollector);

    return violationsCollector;
  }

  private void checkObligationNumber(
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

  private void checkFirstName(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "First name";
    final var attributeValue = request.getFirstName();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_FIRST_NAME, violationsCollector);
  }

  private void checkLastName(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Last name";
    final var attributeValue = request.getLastName();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_LAST_NAME, violationsCollector);
  }

  private void checkTaxNumber(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Isikukood";
    final var attributeValue = request.getTaxNumber();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkFixedLength(
        attributeName, attributeValue, LENGTH_FIXED_TAX_NUMBER, violationsCollector);
    checkTaxNumberUniqueness(attributeValue, violationsCollector);
  }

  private void checkTaxNumberUniqueness(
      final Long taxNumber, final ViolationsCollector violationsCollector) {
    final var fromDb = loadPort.loadByTaxNumber(taxNumber);
    if (fromDb == null) {
      return;
    }
    violationsCollector.collect(
        format("Driver with mentioned Isikukood %d already exist", taxNumber));
  }

  private void checkAddress(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Address";
    final var attributeValue = request.getAddress();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_ADDRESS, violationsCollector);
  }

  private void checkLicenseNumber(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Driver License number";
    final var attributeValue = request.getDriverLicenseNumber();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_DRIVER_LICENSE_NUMBER, violationsCollector);
  }

  private void checkLicenseExpirationDate(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Driver License expiration date";
    final var attributeValue = request.getDriverLicenseExp();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    if (attributeValue.isBefore(qDateTime.getToday())) {
      violationsCollector.collect("License expiration date is in the past");
    }
  }

  private void checkTaxiLicense(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Taxi license number";
    final var attributeValue = request.getTaxiLicense();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_TAXI_LICENSE_NUMBER, violationsCollector);
  }

  private void checkPhoneNumber(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Phone number(s)";
    final var attributeValue = request.getPhone();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName,
        attributeValue,
        LENGTH_MIN_PHONE_NUMBER,
        LENGTH_MAX_PHONE_NUMBER,
        violationsCollector);
  }

  private void checkEmail(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Email";
    final var attributeValue = request.getEmail();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    checkEmailPattern(attributeValue, violationsCollector);
  }

  private void checkCompanyName(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Company name";
    final var attributeValue = request.getCompanyName();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_COMPANY_NAME, violationsCollector);
  }

  private void checkRegistrationNumber(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Registration number";
    final var attributeValue = request.getRegNumber();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_REG_NUMBER, violationsCollector);
  }

  private void checkCompanyVat(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Company VAT number", request.getCompanyVat(), null, LENGTH_MAX_VAT, violationsCollector);
  }

  private void checkCeoFirstName(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "CEO First name";
    final var attributeValue = request.getCompanyCeoFirstName();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_CEO_FIRST_NAME, violationsCollector);
  }

  private void checkCeoLastName(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "CEO Last name";
    final var attributeValue = request.getCompanyCeoLastName();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_CEO_LAST_NAME, violationsCollector);
  }

  private void checkCeoIsikukood(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "CEO Isikukood";
    final var attributeValue = request.getCompanyCeoTaxNumber();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    if (attributeValue == null) {
      return;
    }
    if (String.valueOf(attributeValue).length() != LENGTH_FIXED_CEO_TAX_NUMBER) {
      return;
    }
    violationsCollector.collect(
        format("CEO Isikukood must be %d characters long", LENGTH_FIXED_CEO_TAX_NUMBER));
  }

  private void checkCompanyAddress(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var attributeName = "Company address";
    final var attributeValue = request.getCompanyAddress();
    attributeChecker.checkRequired(attributeName, attributeValue, violationsCollector);
    attributeChecker.checkLength(
        attributeName, attributeValue, null, LENGTH_MAX_COMPANY_ADDRESS, violationsCollector);
  }

  private void checkComment(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    attributeChecker.checkLength(
        "Comment", request.getComment(), null, LENGTH_MAX_COMMENT, violationsCollector);
  }

  private void checkEmailPattern(
      final String email, final ViolationsCollector violationsCollector) {
    final var emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    if (email.matches(emailRegex)) {

      return;
    }
    violationsCollector.collect("Invalid email pattern,please follow the example: email@gmail.com");
  }
}
