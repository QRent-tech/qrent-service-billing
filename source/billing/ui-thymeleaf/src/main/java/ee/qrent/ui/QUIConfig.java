package ee.qrent.ui;

import ee.qrent.billing.bonus.api.in.query.GetObligationQuery;
import ee.qrent.billing.contract.api.in.query.GetContractQuery;
import ee.qrent.billing.driver.api.in.query.GetCallSignLinkQuery;
import ee.qrent.billing.driver.api.in.query.GetDriverQuery;
import ee.qrent.billing.insurance.api.in.query.GetInsuranceCaseQuery;
import ee.qrent.billing.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrent.ui.formatter.QDateFormatter;
import ee.qrent.ui.controller.transaction.assembler.DriverBalanceAssembler;
import ee.qrent.ui.service.driver.DriverCounterService;
import ee.qrent.ui.service.driver.impl.DriverCounterServiceImpl;
import ee.qrent.ui.service.insurance.InsuranceCounterService;
import ee.qrent.ui.service.insurance.impl.InsuranceCounterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ee.qrent.ui.controller")
public class QUIConfig {

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
