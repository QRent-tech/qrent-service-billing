package ee.qrental.ui;

import ee.qrental.bonus.api.in.query.GetObligationQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.driver.api.in.query.GetCallSignLinkQuery;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCaseQuery;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrental.ui.formatter.QDateFormatter;
import ee.qrental.ui.controller.transaction.assembler.DriverBalanceAssembler;
import ee.qrental.ui.service.driver.DriverCounterService;
import ee.qrental.ui.service.driver.impl.DriverCounterServiceImpl;
import ee.qrental.ui.service.insurance.InsuranceCounterService;
import ee.qrental.ui.service.insurance.impl.InsuranceCounterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ee.qrental.ui.controller")
public class UIConfig {

  @Bean
  QDateFormatter getQDateFormatter() {

    return new QDateFormatter();
  }

  @Bean
  DriverBalanceAssembler getDriverBalanceAssembler(
      final GetBalanceQuery balanceQuery,
      final GetDriverQuery driverQuery,
      final GetObligationQuery obligationQuery) {

    return new DriverBalanceAssembler(balanceQuery, driverQuery, obligationQuery);
  }

  @Bean
  DriverCounterService getDriverCounterService(
      final GetCallSignLinkQuery callSignLinkQuery, final GetContractQuery contractQuery) {

    return new DriverCounterServiceImpl(callSignLinkQuery, contractQuery);
  }

  @Bean
  InsuranceCounterService getInsuranceCounterService(
      final GetInsuranceCaseQuery insuranceCaseQuery) {

    return new InsuranceCounterServiceImpl(insuranceCaseQuery);
  }
}
