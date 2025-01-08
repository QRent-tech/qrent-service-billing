package ee.qrental.transaction.core.validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.in.request.TransactionUpdateRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.domain.Transaction;
import ee.qrental.transaction.domain.balance.Balance;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionUpdateRequestValidatorTest {
  private TransactionUpdateRequestValidator instanceUnderTest;
  private TransactionLoadPort transactionLoadPort;
  private BalanceLoadPort balanceLoadPort;
  private GetQWeekQuery qWeekQuery;

  @BeforeEach
  void init() {
    transactionLoadPort = mock(TransactionLoadPort.class);
    balanceLoadPort = mock(BalanceLoadPort.class);
    qWeekQuery = mock(GetQWeekQuery.class);
    instanceUnderTest =
        new TransactionUpdateRequestValidator(qWeekQuery, balanceLoadPort, transactionLoadPort);

    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().build());
  }

  @Test
  public void testUpdateIfBalanceWasNotCalculated() {
    // given
    when(balanceLoadPort.loadLatest()).thenReturn(null);
    final var updateRequest = new TransactionUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setDate(LocalDate.of(2023, Month.JANUARY, 28));
    final var transactionFromDb =
        Transaction.builder().id(1L).date(LocalDate.of(2023, Month.JANUARY, 25)).build();
    when(transactionLoadPort.loadById(1L)).thenReturn(transactionFromDb);

    // when
    final var violationsCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertFalse(violationsCollector.hasViolations());
  }

  @Test
  public void testUpdateIfTransactionDateBeforeCalculationDate() {
    // given
    final var updateRequest = new TransactionUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setDate(LocalDate.of(2023, Month.JANUARY, 28));
    final var transactionFromDb =
        Transaction.builder().id(1L).date(LocalDate.of(2023, Month.JANUARY, 25)).build();
    when(transactionLoadPort.loadById(1L)).thenReturn(transactionFromDb);
    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());

    // when
    final var violationsCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertTrue(violationsCollector.hasViolations());
    assertEquals(1, violationsCollector.getViolations().size());
    assertTrue(
        violationsCollector
            .getViolations()
            .get(0)
            .contains(
                "Update for the Transaction with id=1 is prohibited. Transaction is already calculated in Balance"));
  }

  @Test
  public void testUpdateIfTransactionDateEqualToCalculationDate() {
    // given
    final var updateRequest = new TransactionUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setDate(LocalDate.of(2023, Month.JANUARY, 28));
    final var transactionFromDb =
        Transaction.builder().id(1L).date(LocalDate.of(2023, Month.JANUARY, 26)).build();
    when(transactionLoadPort.loadById(1L)).thenReturn(transactionFromDb);
    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());

    // when
    final var violationsCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertTrue(violationsCollector.hasViolations());
    assertEquals(1, violationsCollector.getViolations().size());
    assertTrue(
        violationsCollector
            .getViolations()
            .get(0)
            .contains(
                "Update for the Transaction with id=1 is prohibited. Transaction is already calculated in Balance"));
  }

  @Test
  public void testUpdateIfTransactionDateAfterCalculationDate() {
    // given
    final var updateRequest = new TransactionUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setDate(LocalDate.of(2023, Month.JANUARY, 28));
    updateRequest.setDriverId(2L);
    final var transactionFromDb =
        Transaction.builder().id(1L).date(LocalDate.of(2023, Month.JANUARY, 27)).build();
    when(transactionLoadPort.loadById(1L)).thenReturn(transactionFromDb);
    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());

    // when
    final var violationsCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertFalse(violationsCollector.hasViolations());
  }

  @Test
  public void testUpdateIfTransactionNewDateBeforeCalculationDate() {
    // given
    final var updateRequest = new TransactionUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setDate(LocalDate.of(2023, Month.JANUARY, 25));
    final var transactionFromDb =
        Transaction.builder().id(1L).date(LocalDate.of(2023, Month.JANUARY, 27)).build();
    when(transactionLoadPort.loadById(1L)).thenReturn(transactionFromDb);
    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());

    // when
    final var violationsCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertTrue(violationsCollector.hasViolations());
    assertEquals(1, violationsCollector.getViolations().size());
    assertEquals(
        violationsCollector.getViolations().get(0),
        "Transaction new date 25-01-2023 must be after the latest calculated Balance date: 26-01-2023");
  }

  @Test
  public void testUpdateIfTransactionNewDateEqualToCalculationDate() {
    // given
    final var updateRequest = new TransactionUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setDate(LocalDate.of(2023, Month.JANUARY, 26));
    final var transactionFromDb =
        Transaction.builder().id(1L).date(LocalDate.of(2023, Month.JANUARY, 27)).build();
    when(transactionLoadPort.loadById(1L)).thenReturn(transactionFromDb);
    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());

    // when
    final var violationsCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertTrue(violationsCollector.hasViolations());
    assertEquals(1, violationsCollector.getViolations().size());
    assertEquals(
        violationsCollector.getViolations().get(0),
        "Transaction new date 26-01-2023 must be after the latest calculated Balance date: 26-01-2023");
  }

  @Test
  public void testUpdateIfTransactionNewDateAfterCalculationDate() {
    // given
    final var updateRequest = new TransactionUpdateRequest();
    updateRequest.setId(1L);
    updateRequest.setDate(LocalDate.of(2023, Month.JANUARY, 28));
    final var transactionFromDb =
        Transaction.builder().id(1L).date(LocalDate.of(2023, Month.JANUARY, 27)).build();
    when(transactionLoadPort.loadById(1L)).thenReturn(transactionFromDb);
    when(balanceLoadPort.loadLatest()).thenReturn(Balance.builder().qWeekId(99L).build());
    when(qWeekQuery.getById(99L))
        .thenReturn(QWeekResponse.builder().end(LocalDate.of(2023, Month.JANUARY, 26)).build());

    // when
    final var violationsCollector = instanceUnderTest.validate(updateRequest);

    // then
    assertFalse(violationsCollector.hasViolations());
  }
}
