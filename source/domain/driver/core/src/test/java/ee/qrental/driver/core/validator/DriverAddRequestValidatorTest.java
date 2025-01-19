package ee.qrental.driver.core.validator;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AttributeChecker;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.request.DriverAddRequest;
import ee.qrental.driver.api.out.DriverLoadPort;
import ee.qrental.driver.domain.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DriverAddRequestValidatorTest {

  private DriverAddRequestValidator instanceUnderTest;
  private DriverLoadPort loadPort;
  private GetQWeekQuery qWeekQuery;
  private QDateTime qDateTime;
  private AttributeChecker attributeChecker;

  @BeforeEach
  void init() {
    loadPort = mock(DriverLoadPort.class);
    qWeekQuery = mock(GetQWeekQuery.class);
    qDateTime = mock(QDateTime.class);
    attributeChecker = mock(AttributeChecker.class);
    instanceUnderTest =
        new DriverAddRequestValidator(loadPort, qWeekQuery, qDateTime, attributeChecker);
  }

  @Test
  void testObligationNumber1() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(true);
    addRequest.setRequiredObligation(BigDecimal.valueOf(0));
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
  }

  @Test
  void testObligationNumber2() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(true);
    addRequest.setRequiredObligation(BigDecimal.valueOf(1001));
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Invalid Obligation (Min 1 and Max 1000)", violationCollector.getViolations().get(0));
  }

  @Test
  void testObligationNumber3() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(true);
    addRequest.setRequiredObligation(BigDecimal.valueOf(1));
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testObligationNumberNotRequiredObligation() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testFirstNameLengthLess50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setFirstName("Name");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testFirstNameLengthMore50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setFirstName("Name Name Name Name Name Name Name Name Name Name  ");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long First name (Max 50 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testLastNameLengthLessThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setLastName("Surname");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testLastNameLengthMoreThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setLastName("Name Name Name Name Name Name Name Name Name Name  ");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Last name (Max 50 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testTaxNumberNotEqual11Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    final var taxNumber = 1234567890L;
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setTaxNumber(taxNumber);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals("Isikukood must be 11 characters long", violationCollector.getViolations().get(0));
  }

  @Test
  void testTaxNumberFromDbNull() {
    // given
    final var addRequest = new DriverAddRequest();
    final var taxNumber = 12345678900L;
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setTaxNumber(taxNumber);
    when(loadPort.loadByTaxNumber(taxNumber)).thenReturn(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testTaxNumberNotUniqueness() {
    // given
    final var addRequest = new DriverAddRequest();
    final var taxNumber = 12345678900L;
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setTaxNumber(taxNumber);
    when(loadPort.loadByTaxNumber(taxNumber)).thenReturn(Driver.builder().build());

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals("Isikukood 12345678900 already exists", violationCollector.getViolations().get(0));
  }

  @Test
  void testAddressLengthLessThan100Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setAddress("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testAddressLengthMoreThan100Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setAddress(
        "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Address (Max 100 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testLicenseNumberLengthLessThan20Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setDriverLicenseNumber("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testLicenseNumberLengthMoreThan20Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setDriverLicenseNumber("123456789012345678901");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long License number (Max 20 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testLicenseExpirationIsNotBeforeToday() {
    // given
    final var addRequest = new DriverAddRequest();
    final var date = LocalDate.now();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(date);
    when(qDateTime.getToday()).thenReturn(date);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testLicenseExpirationIsBeforeToday() {
    // given
    final var addRequest = new DriverAddRequest();
    final var date = LocalDate.now().minusDays(1);
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(date);
    when(qDateTime.getToday()).thenReturn(LocalDate.now());

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "License expiration date is in the past", violationCollector.getViolations().get(0));
  }

  @Test
  void testTaxiLicenseLengthLessThan15Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setTaxiLicense("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testTaxiLicenseLengthMoreThan15Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setTaxiLicense("123456789012345678901");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Taxi license (Max 15 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testPhoneNumberValid1() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setPhone("1234");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
  }

  @Test
  void testPhoneNumberValid2() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setPhone("3721234567837212345678372123456783721234567837212345678");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Invalid Phone number (Min 5 and Max 50)", violationCollector.getViolations().get(0));
  }

  @Test
  void testPhoneNumberValid3() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setPhone("12345");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testEmailOnValid() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setEmail("test@gmail.com");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testEmailOnInvalid() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setEmail("test");
    addRequest.setDriverLicenseExp(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Invalid email (Example email@gmail.com)", violationCollector.getViolations().get(0));
  }

  @Test
  void testCompanyNameLengthLessThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyName("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testCompanyNameLengthMoreThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyName("CompanyNameCompanyNameCompanyNameCompanyNameCompanyName");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Company name (Max 50 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testRegistrationNumberLengthLessThan15Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setRegNumber("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testRegistrationNumberLengthMoreThan15Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setRegNumber("CompanyNameCompanyName");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Registration number (Max 15 characters)",
        violationCollector.getViolations().get(0));
  }

  @Test
  void testCompanyVatLengthLessThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyVat("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testCompanyVatLengthMoreThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyVat("CompanyVATCompanyVATCompanyVATCompanyVATCompanyVAT1");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Company VAT (Max 50 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testCeoFirstNameLengthLessThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyCeoFirstName("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testCeoFirstNameLengthMoreThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyCeoFirstName(
        "CeoFirstNameCeoFirstNameCeoFirstNameCeoFirstNameCeoFirstName");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long CEO First name (Max 50 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testCeoLastNameLengthLessThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyCeoLastName("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testCeoLastNameLengthMoreThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyCeoLastName("CeoLastNameCeoLastNameCeoLastNameCeoLastNameCeoLastName");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long CEO Last name (Max 50 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testCeoIsikukoodLengthLessThan11Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyCeoTaxNumber(0L);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testCeoIsikukoodLengthMoreThan11Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyCeoTaxNumber(12312312312L);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "CEO Isikukood must be 11 characters long", violationCollector.getViolations().get(0));
  }

  @Test
  void testCompanyAddressLengthLessThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyAddress("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testCompanyAddressLengthMoreThan50Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setCompanyAddress("CompanyAddressCompanyAddressCompanyAddressCompanyAddress");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Company address (Max 50 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  void testCommentLengthLessThan200Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setComment("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  void testCommentLengthMoreThan200Characters() {
    // given
    final var addRequest = new DriverAddRequest();
    addRequest.setHasRequiredObligation(false);
    addRequest.setDriverLicenseExp(null);
    addRequest.setComment(
        "CommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentCommentComment");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long Comment (Max 200 characters)", violationCollector.getViolations().get(0));
  }
}
