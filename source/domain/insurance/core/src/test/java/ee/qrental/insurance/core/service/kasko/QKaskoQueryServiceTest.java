package ee.qrental.insurance.core.service.kasko;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.constant.api.in.response.qweek.QWeekResponse;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.response.ContractResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class QKaskoQueryServiceTest {

  private QKaskoQueryService instanceUnderTest;

  private GetContractQuery contractQuery;
  private GetQWeekQuery qWeekQuery;

  @BeforeEach
  void setUp() {
    contractQuery = mock(GetContractQuery.class);
    qWeekQuery = mock(GetQWeekQuery.class);
    instanceUnderTest = new QKaskoQueryService(contractQuery, qWeekQuery);
  }

  @Test
  public void testIfNoContract() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(contractQuery.getLatestContractByDriverId(driverId)).thenReturn(null);

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertFalse(result);
  }

  @Test
  public void testIfLastContractHasDurationLessThanTwelveWeeks() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(contractQuery.getLatestContractByDriverId(driverId))
        .thenReturn(ContractResponse.builder().duration(11).build());

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertFalse(result);
  }

  @Test
  public void testIfLastContractHasDurationTwelveWeeksAndStartAtDateEqualToWeekEndDate() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(qWeekQuery.getById(qWeekId))
        .thenReturn(
            QWeekResponse.builder()
                .start(LocalDate.of(2024, Month.JANUARY, 15))
                .end(LocalDate.of(2024, Month.JANUARY, 21))
                .build());
    when(contractQuery.getLatestContractByDriverId(driverId))
        .thenReturn(
            ContractResponse.builder()
                .duration(12)
                .dateStart(LocalDate.of(2024, Month.JANUARY, 21))
                .build());

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertFalse(result);
  }

  @Test
  public void testIfLastContractHasDurationTwelveWeeksAndStartAtDateAfterWeekEndDate() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(qWeekQuery.getById(qWeekId))
        .thenReturn(
            QWeekResponse.builder()
                .start(LocalDate.of(2024, Month.JANUARY, 15))
                .end(LocalDate.of(2024, Month.JANUARY, 21))
                .build());
    when(contractQuery.getLatestContractByDriverId(driverId))
        .thenReturn(
            ContractResponse.builder()
                .duration(12)
                .dateStart(LocalDate.of(2024, Month.JANUARY, 22))
                .build());

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertFalse(result);
  }

  @Test
  public void testIfLastContractHasDurationTwelveWeeksAndEndDateEqualToWeekStartDate() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(qWeekQuery.getById(qWeekId))
        .thenReturn(
            QWeekResponse.builder()
                .start(LocalDate.of(2024, Month.JANUARY, 15))
                .end(LocalDate.of(2024, Month.JANUARY, 21))
                .build());
    when(contractQuery.getLatestContractByDriverId(driverId))
        .thenReturn(
            ContractResponse.builder()
                .duration(12)
                .dateStart(LocalDate.of(2024, Month.JANUARY, 1))
                .dateEnd(LocalDate.of(2024, Month.JANUARY, 15))
                .build());

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertFalse(result);
  }

  @Test
  public void testIfLastContractHasDurationTwelveWeeksAndEndDateBeforeWeekStartDate() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(qWeekQuery.getById(qWeekId))
        .thenReturn(
            QWeekResponse.builder()
                .start(LocalDate.of(2024, Month.JANUARY, 15))
                .end(LocalDate.of(2024, Month.JANUARY, 21))
                .build());
    when(contractQuery.getLatestContractByDriverId(driverId))
        .thenReturn(
            ContractResponse.builder()
                .duration(12)
                .dateStart(LocalDate.of(2024, Month.JANUARY, 1))
                .dateEnd(LocalDate.of(2024, Month.JANUARY, 14))
                .build());

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertFalse(result);
  }

  @Test
  public void testIfLastContractHasDurationTwelveWeeksAndEndDateAfterWeekStartDate() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(qWeekQuery.getById(qWeekId))
        .thenReturn(
            QWeekResponse.builder()
                .start(LocalDate.of(2024, Month.JANUARY, 15))
                .end(LocalDate.of(2024, Month.JANUARY, 21))
                .build());
    when(contractQuery.getLatestContractByDriverId(driverId))
        .thenReturn(
            ContractResponse.builder()
                .duration(12)
                .dateStart(LocalDate.of(2024, Month.JANUARY, 1))
                .dateEnd(LocalDate.of(2024, Month.JANUARY, 16))
                .build());

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertTrue(result);
  }

  @Test
  public void testIfLastContractHasDurationTwelveWeeksAndStartDateBeforeWeekEndDate() {
    // given
    final var driverId = 5L;
    final var qWeekId = 60L;
    when(qWeekQuery.getById(qWeekId))
        .thenReturn(
            QWeekResponse.builder()
                .start(LocalDate.of(2024, Month.JANUARY, 15))
                .end(LocalDate.of(2024, Month.JANUARY, 21))
                .build());
    when(contractQuery.getLatestContractByDriverId(driverId))
        .thenReturn(
            ContractResponse.builder()
                .duration(12)
                .dateStart(LocalDate.of(2024, Month.JANUARY, 16))
                .dateEnd(null)
                .build());

    // when
    final var result = instanceUnderTest.hasQKasko(driverId, qWeekId);

    // then
    assertTrue(result);
  }

  @Test
  public void testIfNoActiveInsuranceCase() {
    // given
    // when
    // then
  }
}
