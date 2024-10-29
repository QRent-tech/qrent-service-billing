package ee.qrental.insurance.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCalculationQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCaseBalanceQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCaseQuery;
import ee.qrental.insurance.api.in.query.GetQKaskoQuery;
import ee.qrental.insurance.api.in.usecase.InsuranceCaseCloseUseCase;
import ee.qrental.insurance.api.out.*;
import ee.qrental.insurance.core.mapper.*;
import ee.qrental.insurance.core.service.*;
import ee.qrental.insurance.core.service.balance.*;
import ee.qrental.insurance.core.service.kasko.QKaskoQueryService;
import ee.qrental.insurance.core.validator.InsuranceCalculationAddBusinessRuleValidator;
import ee.qrental.insurance.core.validator.InsuranceCaseUpdateBusinessRuleValidator;
import ee.qrental.insurance.core.validator.InsuranceCaseCloseBusinessRuleValidator;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrental.transaction.api.in.query.kind.GetTransactionKindQuery;
import ee.qrental.transaction.api.in.query.type.GetTransactionTypeQuery;
import ee.qrental.transaction.api.in.usecase.TransactionAddUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class InsuranceCaseServiceConfig {

  @Bean
  InsuranceCaseBalanceCalculator getInsuranceCaseBalanceCalculator(
      final GetQKaskoQuery qKaskoQuery,
      final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort,
      final GetTransactionQuery transactionQuery,
      final GetTransactionTypeQuery transactionTypeQuery,
      final InsuranceCaseBalanceDeriveService deriveService,
      final TransactionAddUseCase transactionAddUseCase,
      final GetQWeekQuery getQWeekQuery) {

    return new InsuranceCaseBalanceCalculatorService(
        qKaskoQuery,
        insuranceCaseBalanceLoadPort,
        transactionQuery,
        transactionTypeQuery,
        deriveService,
        transactionAddUseCase,
        getQWeekQuery);
  }

  @Bean
  GetInsuranceCaseBalanceQuery getInsuranceCaseBalanceQuery(
      final GetQWeekQuery qWeekQuery,
      final InsuranceCaseBalanceLoadPort balanceLoadPort,
      final InsuranceCaseBalanceCalculator insuranceCaseBalanceCalculator,
      final InsuranceCaseLoadPort insuranceCaseLoadPort,
      final GetInsuranceCalculationQuery insuranceCalculationQuery,
      final GetTransactionQuery transactionQuery,
      final GetTransactionKindQuery transactionKindQuery) {

    return new InsuranceCaseBalanceQueryService(
        qWeekQuery,
        balanceLoadPort,
        insuranceCaseBalanceCalculator,
        insuranceCaseLoadPort,
        insuranceCalculationQuery,
        transactionQuery,
        transactionKindQuery);
  }

  @Bean
  GetInsuranceCaseQuery getInsuranceCaseQueryService(
      final InsuranceCaseLoadPort loadPort,
      final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort,
      final InsuranceCaseResponseMapper mapper,
      final InsuranceCaseBalanceResponseMapper insuranceCaseBalanceResponseMapper,
      final InsuranceCaseUpdateRequestMapper updateRequestMapper) {

    return new InsuranceCaseQueryService(
        loadPort,
        insuranceCaseBalanceLoadPort,
        mapper,
        insuranceCaseBalanceResponseMapper,
        updateRequestMapper);
  }

  @Bean
  InsuranceCaseUseCaseService getInsuranceCaseUseCaseService(
      final InsuranceCaseAddPort addPort,
      final InsuranceCaseUpdatePort updatePort,
      final InsuranceCaseAddRequestMapper addRequestMapper,
      final InsuranceCaseUpdateRequestMapper updateRequestMapper,
      final InsuranceCaseUpdateBusinessRuleValidator updateBusinessRuleValidator) {

    return new InsuranceCaseUseCaseService(
        addPort, updatePort, addRequestMapper, updateRequestMapper, updateBusinessRuleValidator);
  }

  @Bean
  InsuranceCaseCloseUseCase getInsuranceCaseCloseUseCase(
      final InsuranceCaseUpdatePort updatePort,
      final InsuranceCaseLoadPort loadPort,
      final InsuranceCaseCloseBusinessRuleValidator closeRuleValidator,
      final GetQKaskoQuery getQKaskoQuery,
      final GetQWeekQuery qWeekQuery,
      final GetDriverQuery driverQuery,
      final GetTransactionQuery transactionQuery,
      final GetTransactionTypeQuery transactionTypeQuery,
      final QDateTime qDateTime,
      final TransactionAddUseCase transactionAddUseCase) {

    return new InsuranceCaseCloseUseCaseService(
        updatePort,
        loadPort,
        closeRuleValidator,
        getQKaskoQuery,
        qWeekQuery,
        driverQuery,
        transactionQuery,
        transactionTypeQuery,
        qDateTime,
        transactionAddUseCase);
  }

  @Bean
  InsuranceCalculationQueryService getInsuranceCalculationQueryService(
      final InsuranceCalculationLoadPort loadPort,
      final InsuranceCalculationResponseMapper responseMapper,
      final GetQWeekQuery qWeekQuery,
      final GetBalanceQuery balanceQuery) {

    return new InsuranceCalculationQueryService(loadPort, responseMapper, qWeekQuery, balanceQuery);
  }

  @Bean
  InsuranceCalculationUseCaseService getInsuranceCalculationUseCaseService(
      final InsuranceCaseLoadPort caseLoadPort,
      final InsuranceCaseUpdatePort caseUpdatePort,
      final InsuranceCalculationAddPort calculationAddPort,
      final InsuranceCalculationAddRequestMapper calculationAddRequestMapper,
      final GetQWeekQuery qWeekQuery,
      final InsuranceCaseBalanceCalculator insuranceCaseBalanceCalculator,
      final InsuranceCalculationAddBusinessRuleValidator addBusinessRuleValidator) {

    return new InsuranceCalculationUseCaseService(
        caseLoadPort,
        caseUpdatePort,
        calculationAddPort,
        calculationAddRequestMapper,
        qWeekQuery,
        insuranceCaseBalanceCalculator,
        addBusinessRuleValidator);
  }

  @Bean
  InsuranceCaseBalanceDeriveService getInsuranceCaseBalanceDeriveService() {

    return new InsuranceCaseBalanceDeriveService();
  }

  @Bean
  GetQKaskoQuery getGetQKaskoQuery(
      final GetContractQuery contractQuery, final GetQWeekQuery qWeekQuery) {

    return new QKaskoQueryService(contractQuery, qWeekQuery);
  }
}
