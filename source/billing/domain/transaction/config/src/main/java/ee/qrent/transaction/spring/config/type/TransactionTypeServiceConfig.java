package ee.qrent.transaction.spring.config.type;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.transaction.api.in.query.type.GetTransactionTypeQuery;
import ee.qrent.transaction.api.in.request.type.TransactionTypeAddRequest;
import ee.qrent.transaction.api.in.request.type.TransactionTypeUpdateRequest;
import ee.qrent.transaction.api.out.type.TransactionTypeAddPort;
import ee.qrent.transaction.api.out.type.TransactionTypeDeletePort;
import ee.qrent.transaction.api.out.type.TransactionTypeLoadPort;
import ee.qrent.transaction.api.out.type.TransactionTypeUpdatePort;
import ee.qrent.transaction.core.mapper.type.TransactionTypeAddRequestMapper;
import ee.qrent.transaction.core.mapper.type.TransactionTypeResponseMapper;
import ee.qrent.transaction.core.mapper.type.TransactionTypeUpdateRequestMapper;
import ee.qrent.transaction.core.service.type.TransactionTypeQueryService;
import ee.qrent.transaction.core.service.type.TransactionTypeUseCaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionTypeServiceConfig {

  @Bean
  public GetTransactionTypeQuery getGetTransactionTypeQuery(
      final TransactionTypeLoadPort loadPort,
      final TransactionTypeResponseMapper mapper,
      final TransactionTypeUpdateRequestMapper updateRequestMapper) {

    return new TransactionTypeQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  public TransactionTypeUseCaseService getTransactionTypeUseCaseService(
      final TransactionTypeAddPort addPort,
      final TransactionTypeUpdatePort updatePort,
      final TransactionTypeDeletePort deletePort,
      final TransactionTypeLoadPort loadPort,
      final TransactionTypeAddRequestMapper addRequestMapper,
      final TransactionTypeUpdateRequestMapper updateRequestMapper,
      final AddRequestValidator<TransactionTypeAddRequest> addRequestValidator,
      final UpdateRequestValidator<TransactionTypeUpdateRequest> updateRequestValidator) {

    return new TransactionTypeUseCaseService(
        addPort,
        updatePort,
        deletePort,
        loadPort,
        addRequestMapper,
        updateRequestMapper,
        addRequestValidator,
        updateRequestValidator);
  }
}
