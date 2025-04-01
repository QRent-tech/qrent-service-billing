package ee.qrent.transaction.spring.config.kind;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.transaction.api.in.query.kind.GetTransactionKindQuery;
import ee.qrent.transaction.api.in.request.kind.TransactionKindAddRequest;
import ee.qrent.transaction.api.in.request.kind.TransactionKindUpdateRequest;
import ee.qrent.transaction.api.out.kind.TransactionKindAddPort;
import ee.qrent.transaction.api.out.kind.TransactionKindDeletePort;
import ee.qrent.transaction.api.out.kind.TransactionKindLoadPort;
import ee.qrent.transaction.api.out.kind.TransactionKindUpdatePort;
import ee.qrent.transaction.core.mapper.kind.TransactionKindAddRequestMapper;
import ee.qrent.transaction.core.mapper.kind.TransactionKindResponseMapper;
import ee.qrent.transaction.core.mapper.kind.TransactionKindUpdateRequestMapper;
import ee.qrent.transaction.core.service.kind.TransactionKindQueryService;
import ee.qrent.transaction.core.service.kind.TransactionKindUseCaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionKindServiceConfig {

  @Bean
  public GetTransactionKindQuery getGetTransactionKindQuery(
      final TransactionKindLoadPort loadPort,
      final TransactionKindResponseMapper mapper,
      final TransactionKindUpdateRequestMapper updateRequestMapper) {
    return new TransactionKindQueryService(loadPort, mapper, updateRequestMapper);
  }

  @Bean
  public TransactionKindUseCaseService getTransactionKindUseCaseService(
      final TransactionKindAddPort addPort,
      final TransactionKindUpdatePort updatePort,
      final TransactionKindDeletePort deletePort,
      final TransactionKindLoadPort loadPort,
      final TransactionKindAddRequestMapper addRequestMapper,
      final TransactionKindUpdateRequestMapper updateRequestMapper,
      final AddRequestValidator<TransactionKindAddRequest> addRequestValidator,
      final UpdateRequestValidator<TransactionKindUpdateRequest> updateRequestValidator) {
    return new TransactionKindUseCaseService(
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
