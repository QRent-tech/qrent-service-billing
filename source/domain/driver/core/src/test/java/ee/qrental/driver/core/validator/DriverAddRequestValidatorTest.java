package ee.qrental.driver.core.validator;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AttributeChecker;
import ee.qrental.common.core.validation.AttributeCheckerImpl;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.request.DriverAddRequest;
import ee.qrental.driver.api.out.DriverLoadPort;
import ee.qrental.driver.domain.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DriverAddRequestValidatorTest {

  private DriverAddRequestValidator instanceUnderTest;
  private DriverLoadPort loadPort;

  private QDateTime qDateTime;
  private AttributeChecker attributeChecker;

  @BeforeEach
  void init() {
    loadPort = mock(DriverLoadPort.class);
    qDateTime = mock(QDateTime.class);
    attributeChecker = new AttributeCheckerImpl();
    instanceUnderTest = new DriverAddRequestValidator(loadPort, qDateTime, attributeChecker);
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2025, Month.JANUARY, 15));
  }

  private DriverAddRequest getValidDriverAddRequest() {
    final var addRequest = new DriverAddRequest();
    addRequest.setActive(true);
    addRequest.setHasRequiredObligation(Boolean.FALSE);
    addRequest.setFirstName("John");
    addRequest.setLastName("Doe");
    addRequest.setTaxNumber(38408190101L);
    addRequest.setAddress("Tallinn, Str. Lootsa, 45b");
    addRequest.setDriverLicenseNumber("AR-TY_45879");
    addRequest.setDriverLicenseExp(qDateTime.getToday().plus(2, ChronoUnit.DAYS));
    addRequest.setTaxiLicense("TLL-T-958");
    addRequest.setPhone("+3729876587, +383050478955");
    addRequest.setEmail("test.f.l@gmail.com");
    addRequest.setCompanyName("Driver&Company");
    addRequest.setRegNumber("REG-7878");
    addRequest.setCompanyAddress("Tallinn, Str. Lootsa, 88c");
    addRequest.setCompanyVat("VAT-8989");
    addRequest.setCompanyCeoFirstName("CEO_FN");
    addRequest.setCompanyCeoLastName("CEO_LN");
    addRequest.setCompanyCeoTaxNumber(4381082590101L);
    addRequest.setComment("This is a comment");

    return addRequest;
  }

  @Test
  void testObligationNumberIfObligationRequiredButNotSet() {
    // given
    final var addRequest = getValidDriverAddRequest();
    addRequest.setHasRequiredObligation(true);
    addRequest.setRequiredObligation(null);

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(1, violationCollector.getViolations().size());
    assertTrue(
        violationCollector.getViolations().stream()
            .anyMatch(
                violation ->
                    violation.equals("Invalid value for Required Obligation. Value must be set")));
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
