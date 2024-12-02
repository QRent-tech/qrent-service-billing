package ee.qrental.transaction.core.service.rent;

import static ee.qrental.transaction.api.in.utils.TransactionTypeConstant.TRANSACTION_TYPE_NAME_WEEKLY_RENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.car.api.in.query.GetCarQuery;
import ee.qrental.car.api.in.response.CarLinkResponse;
import ee.qrental.car.api.in.response.CarResponse;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.transaction.api.out.type.TransactionTypeLoadPort;
import ee.qrental.transaction.domain.type.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentTransactionGeneratorTest {
  private RentTransactionGenerator instanceUnderTest;

  private GetCarQuery carQuery;
  private TransactionTypeLoadPort transactionTypeLoadPort;
  private QDateTime qDateTime;

  @BeforeEach
  void init() {
    carQuery = mock(GetCarQuery.class);
    transactionTypeLoadPort = mock(TransactionTypeLoadPort.class);
    qDateTime = mock(QDateTime.class);

    instanceUnderTest = new RentTransactionGenerator(transactionTypeLoadPort, carQuery, qDateTime);
  }

  @Test
  public void testIfCarAgeLessThanOneYear() {
    // given
    final var qWeek =
        QWeekResponse.builder()
            .id(10L)
            .start(LocalDate.of(2024, Month.JANUARY, 2))
            .number(1)
            .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
        .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
        .thenReturn(
            CarResponse.builder()
                .releaseDate(LocalDate.of(2024, Month.JANUARY, 1))
                .elegance(Boolean.TRUE)
                .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
        instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(240), transactionAddRequest.getAmount());
  }
  @Test
  public void testIfCarAgeIsTwoYears() {
    // given
    final var qWeek =
            QWeekResponse.builder()
                    .id(10L)
                    .start(LocalDate.of(2024, Month.JANUARY, 2))
                    .number(1)
                    .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
            .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
            .thenReturn(
                    CarResponse.builder()
                            .releaseDate(LocalDate.of(2022, Month.JANUARY, 1))
                            .elegance(Boolean.TRUE)
                            .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
            instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(240), transactionAddRequest.getAmount());
  }

  @Test
  public void testIfCarAgeIsThreeYears() {
    // given
    final var qWeek =
            QWeekResponse.builder()
                    .id(10L)
                    .start(LocalDate.of(2024, Month.JANUARY, 2))
                    .number(1)
                    .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
            .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
            .thenReturn(
                    CarResponse.builder()
                            .releaseDate(LocalDate.of(2021, Month.JANUARY, 1))
                            .elegance(Boolean.TRUE)
                            .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
            instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(230), transactionAddRequest.getAmount());
  }
  @Test
  public void testIfCarAgeIsFourYears() {
    // given
    final var qWeek =
            QWeekResponse.builder()
                    .id(10L)
                    .start(LocalDate.of(2024, Month.JANUARY, 2))
                    .number(1)
                    .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
            .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
            .thenReturn(
                    CarResponse.builder()
                            .releaseDate(LocalDate.of(2020, Month.JANUARY, 1))
                            .elegance(Boolean.TRUE)
                            .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
            instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(220), transactionAddRequest.getAmount());
  }
  @Test
  public void testIfCarAgeIsFiveYears() {
    // given
    final var qWeek =
            QWeekResponse.builder()
                    .id(10L)
                    .start(LocalDate.of(2024, Month.JANUARY, 2))
                    .number(1)
                    .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
            .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
            .thenReturn(
                    CarResponse.builder()
                            .releaseDate(LocalDate.of(2019, Month.JANUARY, 1))
                            .elegance(Boolean.TRUE)
                            .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
            instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(210), transactionAddRequest.getAmount());
  }
  @Test
  public void testIfCarAgeIsSixYears() {
    // given
    final var qWeek =
            QWeekResponse.builder()
                    .id(10L)
                    .start(LocalDate.of(2024, Month.JANUARY, 2))
                    .number(1)
                    .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
            .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
            .thenReturn(
                    CarResponse.builder()
                            .releaseDate(LocalDate.of(2018, Month.JANUARY, 1))
                            .elegance(Boolean.TRUE)
                            .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
            instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(200), transactionAddRequest.getAmount());
  }

  @Test
  public void testIfCarAgeIsSevenYears() {
    // given
    final var qWeek =
            QWeekResponse.builder()
                    .id(10L)
                    .start(LocalDate.of(2024, Month.JANUARY, 2))
                    .number(1)
                    .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
            .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
            .thenReturn(
                    CarResponse.builder()
                            .releaseDate(LocalDate.of(2017, Month.JANUARY, 1))
                            .elegance(Boolean.TRUE)
                            .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
            instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(150), transactionAddRequest.getAmount());
  }
  @Test
  public void testIfCarAgeIsEightYears() {
    // given
    final var qWeek =
            QWeekResponse.builder()
                    .id(10L)
                    .start(LocalDate.of(2024, Month.JANUARY, 2))
                    .number(1)
                    .build();

    final var carId = 20L;
    final var activeCarLink = CarLinkResponse.builder().carId(carId).driverId(21L).build();
    when(transactionTypeLoadPort.loadByName(TRANSACTION_TYPE_NAME_WEEKLY_RENT))
            .thenReturn(TransactionType.builder().id(30L).build());
    when(carQuery.getById(carId))
            .thenReturn(
                    CarResponse.builder()
                            .releaseDate(LocalDate.of(2016, Month.JANUARY, 1))
                            .elegance(Boolean.TRUE)
                            .build());
    when(qDateTime.getToday()).thenReturn(LocalDate.of(2024, Month.JANUARY, 1));

    // when
    final var transactionAddRequest =
            instanceUnderTest.getRentTransactionAddRequest(qWeek, activeCarLink);

    // then
    assertNotNull(transactionAddRequest);
    assertEquals(1, transactionAddRequest.getWeekNumber());
    assertEquals(30L, transactionAddRequest.getTransactionTypeId());
    assertEquals(21L, transactionAddRequest.getDriverId());
    assertEquals(LocalDate.of(2024, Month.JANUARY, 2), transactionAddRequest.getDate());
    assertEquals(BigDecimal.valueOf(150), transactionAddRequest.getAmount());
  }
}
