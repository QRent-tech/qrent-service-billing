package ee.qrental.insurance.core.service.balance;


import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.insurance.api.out.InsuranceCaseBalanceLoadPort;
import ee.qrental.insurance.core.service.kasko.QKaskoService;
import ee.qrental.insurance.domain.InsuranceCase;
import ee.qrental.insurance.domain.InsuranceCaseBalance;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.query.type.GetTransactionTypeQuery;
import ee.qrental.transaction.api.in.usecase.TransactionAddUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InsuranceCaseBalanceCalculatorServiceTest {
  private InsuranceCaseBalanceCalculatorService instanceUnderTest;

  private InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort;
  private QKaskoService qKaskoService;
  private GetTransactionQuery transactionQuery;
  private GetTransactionTypeQuery transactionTypeQuery;
  private InsuranceCaseBalanceDeriveService deriveService;
  private TransactionAddUseCase transactionAddUseCase;
  private GetQWeekQuery getQWeekQuery;

  @BeforeEach
  void setUp() {
    insuranceCaseBalanceLoadPort = mock(InsuranceCaseBalanceLoadPort.class);
    qKaskoService = mock(QKaskoService.class);
    transactionQuery = mock(GetTransactionQuery.class);
    transactionTypeQuery = mock(GetTransactionTypeQuery.class);
    deriveService = mock(InsuranceCaseBalanceDeriveService.class);
    transactionAddUseCase = mock(TransactionAddUseCase.class);
    getQWeekQuery = mock(GetQWeekQuery.class);

    instanceUnderTest =
        new InsuranceCaseBalanceCalculatorService(
            qKaskoService,
            insuranceCaseBalanceLoadPort,
            transactionQuery,
            transactionTypeQuery,
            deriveService,
            transactionAddUseCase,
            getQWeekQuery);
  }

  @Test
  public void testFirstRemainingAndSelfResponsibilityIfDamageLessThanDefaultSelfResponsibility() {
    // given
    final var insuranceCase = InsuranceCase.builder().damageAmount(BigDecimal.valueOf(499)).build();
    final var insuranceCaseBalance = InsuranceCaseBalance.builder().build();

    // when
    instanceUnderTest.setFirstRemainingAndSelfResponsibility(insuranceCase, insuranceCaseBalance);

    // then
    assertEquals(BigDecimal.valueOf(499), insuranceCaseBalance.getDamageRemaining());
    assertEquals(ZERO, insuranceCaseBalance.getSelfResponsibilityRemaining());
  }

  // @Test
  public void testIf() {
    // given
    // when
    // then
  }
}
