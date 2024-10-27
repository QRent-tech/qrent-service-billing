package ee.qrental.ui.controller;

import ee.qrental.bonus.api.in.query.GetObligationQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.driver.api.in.query.GetCallSignLinkQuery;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrental.ui.controller.formatter.QDateFormatter;
import ee.qrental.ui.controller.transaction.assembler.DriverBalanceAssembler;
import ee.qrental.ui.service.DriverCounterService;
import ee.qrental.ui.service.impl.DriverCounterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ee.qrental.ui.controller")
public class ControllerConfig {

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
}
