package ee.qrental.insurance.spring.config;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.CloseRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCalculationQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCaseBalanceQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCaseQuery;
import ee.qrental.insurance.api.in.query.GetQKaskoQuery;
import ee.qrental.insurance.api.in.request.InsuranceCalculationAddRequest;
import ee.qrental.insurance.api.in.request.InsuranceCaseCloseRequest;
import ee.qrental.insurance.api.in.request.InsuranceCaseUpdateRequest;
import ee.qrental.insurance.api.in.usecase.InsuranceCaseCloseUseCase;
import ee.qrental.insurance.api.out.*;
import ee.qrental.insurance.core.mapper.*;
import ee.qrental.insurance.core.service.*;
import ee.qrental.insurance.core.service.balance.*;
import ee.qrental.insurance.core.service.kasko.QKaskoQueryService;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrental.transaction.api.in.query.kind.GetTransactionKindQuery;
import ee.qrental.transaction.api.in.query.type.GetTransactionTypeQuery;
import ee.qrental.transaction.api.in.usecase.TransactionAddUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static java.util.Arrays.asList;

@Configuration
@EnableTransactionManagement
public class InsuranceCaseServiceConfig {

  @Bean
  List<InsuranceCaseBalanceCalculationStrategy> getInsuranceCaseBalanceCalculationStrategies(
      final GetQWeekQuery qWeekQuery,
      final GetQKaskoQuery qKaskoQuery,
      final GetTransactionTypeQuery transactionTypeQuery,
      final TransactionAddUseCase transactionAddUseCase,
      final InsuranceCaseBalanceLoadPort insuranceCaseBalanceLoadPort,
      final GetTransactionQuery transactionQuery,
      final InsuranceCaseBalanceDeriveService deriveService) {
    return asList(
        new InsuranceCaseBalanceWithQKaskoCalculationStrategy(
            qWeekQuery,
            qKaskoQuery,
            transactionTypeQuery,
            transactionAddUseCase,
            insuranceCaseBalanceLoadPort,
            transactionQuery,
            deriveService),
        new InsuranceCaseBalanceWithoutQKaskoCalculationStrategy(
            qWeekQuery,
            qKaskoQuery,
            transactionTypeQuery,
            transactionAddUseCase,
            insuranceCaseBalanceLoadPort));
  }

  @Bean
  InsuranceCaseBalanceCalculator getInsuranceCaseBalanceCalculator(
      List<InsuranceCaseBalanceCalculationStrategy> calculationStrategies) {

    return new InsuranceCaseBalanceCalculationService(calculationStrategies);
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
      final UpdateRequestValidator<InsuranceCaseUpdateRequest> updateRequestValidator) {

    return new InsuranceCaseUseCaseService(
        addPort, updatePort, addRequestMapper, updateRequestMapper, updateRequestValidator);
  }

  @Bean
  InsuranceCaseCloseUseCase getInsuranceCaseCloseUseCase(
      final InsuranceCaseUpdatePort updatePort,
      final InsuranceCaseLoadPort loadPort,
      final CloseRequestValidator<InsuranceCaseCloseRequest> closeRuleValidator,
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
      final AddRequestValidator<InsuranceCalculationAddRequest> addRequestValidator) {

    return new InsuranceCalculationUseCaseService(
        caseLoadPort,
        caseUpdatePort,
        calculationAddPort,
        calculationAddRequestMapper,
        qWeekQuery,
        insuranceCaseBalanceCalculator,
        addRequestValidator);
  }

  @Bean
  InsuranceCaseBalanceDeriveService getInsuranceCaseBalanceDeriveService() {

    return new InsuranceCaseBalanceDeriveService();
  }

  @Bean
  GetQKaskoQuery getGetQKaskoQuery(final GetContractQuery contractQuery) {

    return new QKaskoQueryService(contractQuery);
  }
}
