package ee.qrent.transaction.core.service.balance;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.driver.api.in.query.GetDriverQuery;
import ee.qrent.transaction.api.in.query.GetTransactionQuery;
import ee.qrent.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrent.transaction.api.out.balance.BalanceLoadPort;
import ee.qrent.transaction.core.mapper.balance.BalanceResponseMapper;
import ee.qrent.transaction.core.service.balance.calculator.BalanceCalculatorStrategy;
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
