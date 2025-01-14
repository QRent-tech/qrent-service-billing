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

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
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
  public void testIfCallSignAddRequestWithInvalidCallSign() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(0);
    addRequest.setComment("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Invalid number call sign (Min 1 and Max 999)", violationCollector.getViolations().get(0));
  }

  @Test
  public void testIfCallSignAddRequestWithInvalidCallSign2() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(1000);
    addRequest.setComment("");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Invalid number call sign (Min 1 and Max 999)", violationCollector.getViolations().get(0));
  }

  @Test
  public void testIfCallSignAddRequestWithTooLongComment() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(1);
    addRequest.setComment(
        "The quick brown fox jumps over the lazy dog, showcasing agility and speed. Meanwhile, a curious cat watches from afar, pondering the mystery of life and enjoying the warmth of a sunny afternoon. 123123123");

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Too long comment (Max 200 characters)", violationCollector.getViolations().get(0));
  }

  @Test
  public void testIfCallSignAddNotUniquenessCallSignNumber() {
    // given
    final var addRequest = new CallSignAddRequest();
    addRequest.setCallSign(1);
    addRequest.setComment("");
    when(loadPort.loadByCallSign(1)).thenReturn(CallSign.builder().callSign(1).build());

    // when
    final var violationCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals("Call Sign 1 already exists", violationCollector.getViolations().get(0));
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
  public void testIfCallSignDeleteRequestDoesntHaveCallSignLinks() {
    // given
    final var deleteRequest = new CallSignDeleteRequest(1L);
    when(linkLoadPort.loadByCallSignId(1L)).thenReturn(emptyList());

    // when
    final var violationCollector = instanceUnderTest.validate(deleteRequest);

    // then
    assertFalse(violationCollector.hasViolations());
  }

  @Test
  public void testIfCallSignDeleteRequestHasCallSignLinks() {
    // given
    final var deleteRequest = new CallSignDeleteRequest(1L);
    final var callSignLinks =
        new ArrayList<CallSignLink>(singletonList(CallSignLink.builder().build()));

    when(linkLoadPort.loadByCallSignId(1L)).thenReturn(callSignLinks);

    // when
    final var violationCollector = instanceUnderTest.validate(deleteRequest);

    // then
    assertTrue(violationCollector.hasViolations());
    assertEquals(
        "Call Sign with id: 1 can not be deleted, because it uses in 1 Call Sign Links",
        violationCollector.getViolations().get(0));
  }
}
