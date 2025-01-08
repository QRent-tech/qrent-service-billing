package ee.qrental.transaction.core.validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.domain.balance.Balance;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionAddRequestValidatorTest {
  private TransactionAddRequestValidator instanceUnderTest;
  private TransactionLoadPort transactionLoadPort;
  private BalanceLoadPort balanceLoadPort;
  private GetQWeekQuery qWeekQuery;

  @BeforeEach
  void init() {
    transactionLoadPort = mock(TransactionLoadPort.class);
    balanceLoadPort = mock(BalanceLoadPort.class);
    qWeekQuery = mock(GetQWeekQuery.class);
    instanceUnderTest =
        new TransactionAddRequestValidator(qWeekQuery, balanceLoadPort, transactionLoadPort);

    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().build());
  }

  @Test
  public void testAddIfBalanceWasNotCalculated() {
    // given
    final var addRequest = new TransactionAddRequest();
    addRequest.setDate(LocalDate.of(2023, Month.JANUARY, 25));
    addRequest.setDriverId(2L);
    when(balanceLoadPort.loadLatestByDriverId(2L)).thenReturn(null);

    // when
    final var violationsCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationsCollector.hasViolations());
  }

  @Test
  public void testAddIfTransactionDateBeforeCalculationDate() {
    // given
    final var addRequest = new TransactionAddRequest();
    addRequest.setDate(LocalDate.of(2023, Month.JANUARY, 25));
    addRequest.setDriverId(2L);

    when(balanceLoadPort.loadLatestByDriverId(2L))
        .thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());

    // when
    final var violationsCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationsCollector.hasViolations());
    assertEquals(1, violationsCollector.getViolations().size());
    assertTrue(
        violationsCollector
            .getViolations()
            .get(0)
            .contains("must be after the latest calculated Balance date"));
  }

  @Test
  public void testAddIfTransactionDateEqualToCalculationDate() {
    // given
    final var addRequest = new TransactionAddRequest();
    addRequest.setDate(LocalDate.of(2023, Month.JANUARY, 26));
    addRequest.setDriverId(2L);

    when(balanceLoadPort.loadLatestByDriverId(2L))
        .thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());
    // when
    final var violationsCollector = instanceUnderTest.validate(addRequest);

    // then
    assertTrue(violationsCollector.hasViolations());
    assertEquals(1, violationsCollector.getViolations().size());
    assertTrue(
        violationsCollector
            .getViolations()
            .get(0)
            .contains("must be after the latest calculated Balance date"));
  }

  @Test
  public void testAddIfTransactionDateAfterCalculationDate() {
    // given
    final var addRequest = new TransactionAddRequest();
    addRequest.setDate(LocalDate.of(2023, Month.JANUARY, 27));
    addRequest.setDriverId(2L);

    // when
    final var violationsCollector = instanceUnderTest.validate(addRequest);

    // then
    assertFalse(violationsCollector.hasViolations());
  }
}
