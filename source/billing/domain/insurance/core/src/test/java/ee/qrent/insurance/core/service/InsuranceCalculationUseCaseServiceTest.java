package ee.qrent.insurance.core.service;

import static org.mockito.Mockito.mock;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.insurance.api.out.*;
import ee.qrent.insurance.core.mapper.InsuranceCalculationAddRequestMapper;
import ee.qrent.insurance.core.validator.InsuranceCalculationAddRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsuranceCalculationUseCaseServiceTest {
  private InsuranceCalculationUseCaseService instanceUnderTest;

  private InsuranceCaseLoadPort caseLoadPort;
  private InsuranceCaseUpdatePort caseUpdatePort;
  private InsuranceCalculationAddPort calculationAddPort;
  private InsuranceCalculationAddRequestMapper calculationAddRequestMapper;
  private GetQWeekQuery qWeekQuery;
  private InsuranceCaseBalanceCalculator insuranceCaseBalanceCalculator;
  private InsuranceCalculationAddRequestValidator addRequestValidator;

  @BeforeEach
  void setUp() {
    caseLoadPort = mock(InsuranceCaseLoadPort.class);
    caseUpdatePort = mock(InsuranceCaseUpdatePort.class);
    calculationAddPort = mock(InsuranceCalculationAddPort.class);
    calculationAddRequestMapper = mock(InsuranceCalculationAddRequestMapper.class);
    qWeekQuery = mock(GetQWeekQuery.class);
    insuranceCaseBalanceCalculator = mock(InsuranceCaseBalanceCalculator.class);
    addRequestValidator = mock(InsuranceCalculationAddRequestValidator.class);

    instanceUnderTest =
        new InsuranceCalculationUseCaseService(
            caseLoadPort,
            caseUpdatePort,
            calculationAddPort,
            calculationAddRequestMapper,
            qWeekQuery,
            insuranceCaseBalanceCalculator,
                addRequestValidator);
  }

  @Test
  public void testIfNoActiveInsuranceCase() {
    // given
    // when
    // then
  }

  @Test
  public void testIf___() {
    // given
    // when
    // then
  }
}
