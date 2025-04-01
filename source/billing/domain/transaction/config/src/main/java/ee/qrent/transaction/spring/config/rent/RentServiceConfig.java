package ee.qrent.transaction.spring.config.rent;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.billing.car.api.in.query.GetCarLinkQuery;
import ee.qrent.billing.car.api.in.query.GetCarQuery;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.billing.contract.api.in.query.GetAbsenceQuery;
import ee.qrent.email.api.in.usecase.EmailSendUseCase;
import ee.qrent.transaction.api.in.query.GetTransactionQuery;
import ee.qrent.transaction.api.in.query.balance.GetBalanceCalculationQuery;
import ee.qrent.transaction.api.in.query.rent.GetRentCalculationQuery;
import ee.qrent.transaction.api.in.request.rent.RentCalculationAddRequest;
import ee.qrent.transaction.api.out.rent.RentCalculationAddPort;
import ee.qrent.transaction.api.out.rent.RentCalculationLoadPort;
import ee.qrent.transaction.api.out.type.TransactionTypeLoadPort;
import ee.qrent.transaction.core.mapper.rent.RentCalculationAddRequestMapper;
import ee.qrent.transaction.core.mapper.rent.RentCalculationResponseMapper;
import ee.qrent.transaction.core.service.TransactionUseCaseService;
import ee.qrent.transaction.core.service.rent.RentCalculationQueryService;
import ee.qrent.transaction.core.service.rent.RentCalculationService;
import ee.qrent.transaction.core.service.rent.RentTransactionGenerator;
import ee.qrent.user.api.in.query.GetUserAccountQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentServiceConfig {

  @Bean
  GetRentCalculationQuery getRentCalculationQueryService(
      final GetQWeekQuery qWeekQuery,
      final GetBalanceCalculationQuery balanceCalculationQuery,
      final RentCalculationLoadPort loadPort,
      final RentCalculationResponseMapper responseMapper) {
    return new RentCalculationQueryService(
        qWeekQuery, balanceCalculationQuery, loadPort, responseMapper);
  }

  @Bean
  RentCalculationService getRentCalculationService(
      final RentTransactionGenerator rentTransactionGenerator,
      final GetCarLinkQuery carLinkQuery,
      final GetTransactionQuery transactionQuery,
      final TransactionUseCaseService transactionUseCaseService,
      final RentCalculationAddPort rentCalculationAddPort,
      final RentCalculationAddRequestMapper addRequestMapper,
      final AddRequestValidator<RentCalculationAddRequest> addRequestValidator,
      final EmailSendUseCase emailSendUseCase,
      final GetUserAccountQuery userAccountQuery,
      final GetQWeekQuery weekQuery,
      final GetAbsenceQuery absenceQuery) {

    return new RentCalculationService(
        rentTransactionGenerator,
        carLinkQuery,
        transactionQuery,
        transactionUseCaseService,
        rentCalculationAddPort,
        addRequestMapper,
        addRequestValidator,
        emailSendUseCase,
        userAccountQuery,
        weekQuery,
        absenceQuery);
  }

  @Bean
  RentTransactionGenerator getRentTransactionGenerator(
      final TransactionTypeLoadPort transactionTypeLoadPort,
      final GetCarQuery carQuery,
      final QDateTime qDateTime) {
    return new RentTransactionGenerator(transactionTypeLoadPort, carQuery, qDateTime);
  }
}
