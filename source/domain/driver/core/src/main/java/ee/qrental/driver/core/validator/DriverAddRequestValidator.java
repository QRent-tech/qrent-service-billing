package ee.qrental.driver.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.request.DriverAddRequest;
import ee.qrental.driver.api.out.DriverLoadPort;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class DriverAddRequestValidator implements AddRequestValidator<DriverAddRequest> {

  private final DriverLoadPort loadPort;
  private final GetQWeekQuery qWeekQuery;
  private final QDateTime qDateTime;

  private static final int MIN_OBLIGATION = 1;
  private static final int MAX_OBLIGATION = 1000;
  private static final int MAX_FIRST_NAME_LENGTH = 50;
  private static final int MAX_LAST_NAME_LENGTH = 50;
  private static final int LENGTH_OF_ISIKUKOOD = 11;
  private static final int MAX_ADDRESS_LENGTH = 100;
  private static final int MAX_LICENSE_NUMBER_LENGTH = 20;
  private static final int MAX_TAXI_LICENSE_LENGTH = 15;
  private static final int MIN_PHONE_NUMBER_LENGTH = 5;
  private static final int MAX_PHONE_NUMBER_LENGTH = 50;
  private static final int MAX_COMPANY_NAME_LENGTH = 50;
  private static final int MAX_REG_NUMBER_LENGTH = 15;
  private static final int MAX_VAT_LENGTH = 50;
  private static final int MAX_CEO_FIRST_NAME_LENGTH = 50;
  private static final int MAX_CEO_LAST_NAME_LENGTH = 50;
  private static final int LENGTH_OF_CEO_ISIKUKOOD = 11;
  private static final int MAX_ADDRESS_COMPANY_LENGTH = 50;
  private static final int MAX_COMMENT_LENGTH = 200;

  //  private boolean containsLetters(String input) {
  //    String letterRegex = ".*[A-Za-z].*";
  //
  //    return !input.matches(letterRegex);
  //  }
  //
  //  private boolean containsDigits(String input) {
  //    String digitRegex = ".*[0-9].*";
  //
  //    return !input.matches(digitRegex);
  //  }

  private boolean isValidEmail(String input) {
    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    return input.matches(emailRegex);
  }

  @Override
  public ViolationsCollector validate(final DriverAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkObligationValidNumberForAdd(request, violationsCollector);
    checkFirstNameValidForAdd(request, violationsCollector);
    checkLastNameValidForAdd(request, violationsCollector);
    checkIsikukoodValidForAdd(request, violationsCollector);
    checkAddressLengthForAdd(request, violationsCollector);
    checkLicenseNumberValidForAdd(request, violationsCollector);
    checkLicenseExpirationDateValidForAdd(request, violationsCollector);
    checkTaxiLicenseValidForAdd(request, violationsCollector);
    checkPhoneNumberValidForAdd(request, violationsCollector);
    checkEmailValidForAdd(request, violationsCollector);
    checkCompanyNameValidForAdd(request, violationsCollector);
    checkRegistrationNumberValidForAdd(request, violationsCollector);
    checkVatValidForAdd(request, violationsCollector);
    checkCeoFirstNameValidForAdd(request, violationsCollector);
    checkCeoLastNameValidForAdd(request, violationsCollector);
    checkCeoIsikukoodValidForAdd(request, violationsCollector);
    checkAddressCompanyValidForAdd(request, violationsCollector);
    checkCommentValidForAdd(request, violationsCollector);

    return violationsCollector;
  }

  private void checkObligationValidNumberForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    if (!request.getHasRequiredObligation()) {
      return;
    }

    final var obligation = request.getRequiredObligation();

    if (obligation.intValue() >= MIN_OBLIGATION && obligation.intValue() <= MAX_OBLIGATION) {
      return;
    }

    violationsCollector.collect(
        format("Invalid obligation (Min %d and Max %d)", MIN_OBLIGATION, MAX_OBLIGATION));
  }

  private void checkFirstNameValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var firstName = request.getFirstName();

    //    if (containsLetters(firstName)) {
    //      violationsCollector.collect("First name must have letters");
    //      return;
    //    }

    if (firstName.length() <= MAX_FIRST_NAME_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long first name (Max %d characters)", MAX_FIRST_NAME_LENGTH));
  }

  private void checkLastNameValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var lastName = request.getLastName();

    //    if (containsLetters(lastName)) {
    //      violationsCollector.collect("Last name must have letters");
    //      return;
    //    }

    if (lastName.length() <= MAX_LAST_NAME_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long last name (Max %d characters)", MAX_LAST_NAME_LENGTH));
  }

  private void checkIsikukoodValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var taxNumber = request.getTaxNumber();

    if (taxNumber.toString().length() != LENGTH_OF_ISIKUKOOD) {
      violationsCollector.collect(
          format("Isikukood must be %d characters long", LENGTH_OF_ISIKUKOOD));
      return;
    }

    final var fromDb = loadPort.loadByTaxNumber(taxNumber);

    if (fromDb != null) {
      violationsCollector.collect(format("Isikukood %d already exists", taxNumber));
    }
  }

  private void checkAddressLengthForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var addressLength = request.getAddress().length();

    if (addressLength <= MAX_ADDRESS_LENGTH) {
      return;
    }

    violationsCollector.collect(format("Too long address (Max %d characters)", MAX_ADDRESS_LENGTH));
  }

  private void checkLicenseNumberValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var licenseNumber = request.getDriverLicenseNumber();

    if (licenseNumber.length() <= MAX_LICENSE_NUMBER_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long license number (Max %d characters)", MAX_LICENSE_NUMBER_LENGTH));
  }

  private void checkLicenseExpirationDateValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var licenseExpirationDate = request.getDriverLicenseExp();

    if (!licenseExpirationDate.isBefore(LocalDate.now())) {
      return;
    }

    violationsCollector.collect("License expiration date is in the past");
  }

  private void checkTaxiLicenseValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var taxiLicenseLength = request.getTaxiLicense().length();

    if (taxiLicenseLength <= MAX_TAXI_LICENSE_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long taxi license (Max %d characters)", MAX_TAXI_LICENSE_LENGTH));
  }

  private void checkPhoneNumberValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {
    final var phoneNumberLength = request.getPhone().length();

    if (phoneNumberLength >= MIN_PHONE_NUMBER_LENGTH
        && phoneNumberLength <= MAX_PHONE_NUMBER_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format(
            "Invalid phone number (Min %d and Max %d)",
            MIN_PHONE_NUMBER_LENGTH, MAX_PHONE_NUMBER_LENGTH));
  }

  private void checkEmailValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var email = request.getEmail();

    if (isValidEmail(email)) {
      return;
    }

    violationsCollector.collect("Invalid email (Example email@gmail.com)");
  }

  private void checkCompanyNameValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var companyNameLength = request.getCompanyName().length();

    if (companyNameLength < MAX_COMPANY_NAME_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long company name (Max %d characters)", MAX_COMPANY_NAME_LENGTH));
  }

  private void checkRegistrationNumberValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var regNumberLength = request.getRegNumber().length();

    if (regNumberLength < MAX_REG_NUMBER_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long registration number (Max %d characters)", MAX_REG_NUMBER_LENGTH));
  }

  private void checkVatValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var vat = request.getCompanyVat();

    if (vat == null) {
      return;
    }

    if (vat.length() < MAX_VAT_LENGTH) {
      return;
    }

    violationsCollector.collect(format("Too long VAT (Max %d characters)", MAX_VAT_LENGTH));
  }

  private void checkCeoFirstNameValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var ceoFirstName = request.getCompanyCeoFirstName();

    if (ceoFirstName == null) {
      return;
    }

    if (ceoFirstName.length() < MAX_CEO_FIRST_NAME_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long CEO First name (Max %d characters)", MAX_CEO_FIRST_NAME_LENGTH));
  }

  private void checkCeoLastNameValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var ceoLastName = request.getCompanyCeoLastName();

    if (ceoLastName == null) {
      return;
    }

    if (ceoLastName.length() < MAX_CEO_LAST_NAME_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long CEO Last name (Max %d characters)", MAX_CEO_LAST_NAME_LENGTH));
  }

  private void checkCeoIsikukoodValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var ceoIsikukood = String.valueOf(request.getCompanyCeoTaxNumber());

    if (ceoIsikukood == null) {
      return;
    }

    if (ceoIsikukood.length() != LENGTH_OF_CEO_ISIKUKOOD) {
      return;
    }

    violationsCollector.collect(
        format("CEO Isikukood must be %d characters long", LENGTH_OF_CEO_ISIKUKOOD));
  }

  private void checkAddressCompanyValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var addressCompany = request.getCompanyAddress();

    if (addressCompany == null) {
      return;
    }

    if (addressCompany.length() < MAX_ADDRESS_COMPANY_LENGTH) {
      return;
    }

    violationsCollector.collect(
        format("Too long Address company (Max %d characters)", MAX_ADDRESS_COMPANY_LENGTH));
  }

  private void checkCommentValidForAdd(
      final DriverAddRequest request, final ViolationsCollector violationsCollector) {

    final var comment = request.getComment();

    if (comment == null) {
      return;
    }

    if (comment.length() < MAX_COMMENT_LENGTH) {
      return;
    }

    violationsCollector.collect(format("Too long comment (Max %d characters)", MAX_COMMENT_LENGTH));
  }
}
