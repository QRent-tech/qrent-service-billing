package ee.qrental.transaction.core.service.balance;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.core.mapper.balance.BalanceResponseMapper;
import ee.qrental.transaction.core.service.balance.calculator.BalanceCalculatorStrategy;
import org.junit.jupiter.api.BeforeEach;

class BalanceQueryServiceTest {

  private GetBalanceQuery instanceUnderTest;
  private GetDriverQuery driverQuery;
  private GetQWeekQuery qWeekQuery;
  private GetTransactionQuery transactionQuery;
  private BalanceLoadPort balanceLoadPort;
  private BalanceResponseMapper balanceResponseMapper;
  private BalanceCalculatorStrategy calculatorStrategies;

  @BeforeEach
  void init() {
    qWeekQuery = mock(GetQWeekQuery.class);
    driverQuery = mock(GetDriverQuery.class);
    transactionQuery = mock(GetTransactionQuery.class);
    balanceLoadPort = mock(BalanceLoadPort.class);
    balanceResponseMapper = mock(BalanceResponseMapper.class);
    calculatorStrategies = mock(BalanceCalculatorStrategy.class);

    instanceUnderTest =
        new BalanceQueryService(
            driverQuery,
            qWeekQuery,
            transactionQuery,
            balanceLoadPort,
            balanceResponseMapper,
            asList(calculatorStrategies));
  }
}
