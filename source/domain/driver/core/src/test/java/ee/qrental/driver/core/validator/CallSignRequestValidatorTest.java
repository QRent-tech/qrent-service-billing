package ee.qrental.driver.core.validator;

import ee.qrental.driver.api.in.request.CallSignAddRequest;
import ee.qrental.driver.api.in.request.CallSignDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignUpdateRequest;
import ee.qrental.driver.api.out.CallSignLinkLoadPort;
import ee.qrental.driver.api.out.CallSignLoadPort;
import ee.qrental.driver.domain.CallSign;
import ee.qrental.driver.domain.CallSignLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallSignRequestValidatorTest {

  private CallSignRequestValidator instanceUnderTest;
  private CallSignLoadPort loadPort;
  private CallSignLinkLoadPort linkLoadPort;

  @BeforeEach
  void init() {
    loadPort = mock(CallSignLoadPort.class);
    linkLoadPort = mock(CallSignLinkLoadPort.class);
    instanceUnderTest = new CallSignRequestValidator(loadPort, linkLoadPort);
  }

  @Test
  public void testIfCallSignAddRequestIsInvalidCallSignNumber() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(0);
    addRequest.setComment("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
  }

  @Test
  public void testIfCallSignAddRequestIsValidCallBetweenTwoCallSignsNumber() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(0);
    addRequest.setCallSign(1000);
    addRequest.setComment("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
  }

  @Test
  public void testIfCallSignAddRequestTooLengthCallSignComment() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(1);
    addRequest.setComment(
        "The quick brown fox jumps over the lazy dog, showcasing agility and speed. Meanwhile, a curious cat watches from afar, pondering the mystery of life and enjoying the warmth of a sunny afternoon. 123123123");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
  }

  @Test
  public void testIfCallSignAddNotUniquenessCallSignNumber() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(1);
    addRequest.setComment("");
    when(loadPort.loadByCallSign(1)).thenReturn(CallSign.builder().callSign(1).build());
    when(loadPort.loadByCallSign(1)).thenReturn(CallSign.builder().callSign(1).build());

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
  }

  @Test
  public void testIfCallSignUpdateCheckExistenceIsNull() {
    // given
    final var updateRequest = new CallSignUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setCallSign(1);
    updateRequest.setComment("");
    when(loadPort.loadById(1L)).thenReturn(CallSign.builder().build());

    // when
    final var violationCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  public void testIfCallSignDeleteIsCallSignLinksEmpty() {
    // given
    final var deleteRequest = new CallSignDeleteRequest(1L);
    when(linkLoadPort.loadByCallSignId(1L)).thenReturn(new ArrayList<>());

    // when
    final var violationCollector = instanceUnderTest.validate(deleteRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  public void testIfCallSignDeleteIsCallSignLinksNotEmpty() {
    // given
    final var deleteRequest = new CallSignDeleteRequest(1L);
    final var callSignLinks =
        new ArrayList<CallSignLink>(Collections.singletonList(CallSignLink.builder().build()));

    when(linkLoadPort.loadByCallSignId(1L)).thenReturn(callSignLinks);

    // when
    final var violationCollector = instanceUnderTest.validate(deleteRequest);

    // then
    assertTrue(violationCollector.hasViolations());
  }
}
