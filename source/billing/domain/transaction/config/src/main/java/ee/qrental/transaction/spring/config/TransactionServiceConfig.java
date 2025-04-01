package ee.qrental.transaction.spring.config;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.query.GetTransactionQuery;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.in.request.TransactionDeleteRequest;
import ee.qrental.transaction.api.in.request.TransactionUpdateRequest;
import ee.qrental.transaction.api.out.TransactionAddPort;
import ee.qrental.transaction.api.out.TransactionDeletePort;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.TransactionUpdatePort;
import ee.qrental.transaction.core.mapper.TransactionAddRequestMapper;
import ee.qrental.transaction.core.mapper.TransactionResponseMapper;
import ee.qrental.transaction.core.mapper.TransactionUpdateRequestMapper;
import ee.qrental.transaction.core.service.TransactionQueryService;
import ee.qrental.transaction.core.service.TransactionUseCaseService;
import ee.qrental.transaction.core.service.strategy.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionServiceConfig {

  @Bean
  public List<TransactionLoadStrategy> getLoadStrategies(
      final TransactionLoadPort transactionLoadPort) {
    return Arrays.asList(
        new TransactionLoadStrategyByDriverAndYear(transactionLoadPort),
        new TransactionLoadStrategyByDriverAndYearAndWeekAndFee(transactionLoadPort),
        new TransactionLoadStrategyByYear(transactionLoadPort),
        new TransactionLoadStrategyByYearAndWeek(transactionLoadPort));
  }

  @Bean
  public GetTransactionQuery getTransactionQueryService(
      final TransactionLoadPort transactionLoadPort,
      final List<TransactionLoadStrategy> loadStrategies,
      final TransactionResponseMapper responseMapper,
      final TransactionUpdateRequestMapper updateRequestMapper,
      final GetQWeekQuery qWeekQuery) {
    return new TransactionQueryService(
        transactionLoadPort, loadStrategies, responseMapper, updateRequestMapper, qWeekQuery);
  }

  @Bean
  public TransactionUseCaseService getTransactionUseCaseService(
      final TransactionAddPort addPort,
      final TransactionUpdatePort updatePort,
      final TransactionDeletePort deletePort,
      final TransactionAddRequestMapper addRequestMapper,
      final TransactionUpdateRequestMapper updateRequestMapper,
      final AddRequestValidator<TransactionAddRequest> addRequestValidator,
      final UpdateRequestValidator<TransactionUpdateRequest> updateRequestValidator,
      final DeleteRequestValidator<TransactionDeleteRequest> deleteRequestValidator) {
    return new TransactionUseCaseService(
        addPort,
        updatePort,
        deletePort,
        addRequestMapper,
        updateRequestMapper,
        addRequestValidator,
        updateRequestValidator,
        deleteRequestValidator);
  }
}
