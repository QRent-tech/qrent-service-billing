package ee.qrental.transaction.spring.config.kind;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.transaction.api.in.request.kind.TransactionKindAddRequest;
import ee.qrental.transaction.api.in.request.kind.TransactionKindDeleteRequest;
import ee.qrental.transaction.api.in.request.kind.TransactionKindUpdateRequest;
import ee.qrental.transaction.api.out.kind.TransactionKindLoadPort;
import ee.qrental.transaction.core.validator.TransactionKindAddRequestValidator;
import ee.qrental.transaction.core.validator.TransactionKindDeleteRequestValidator;
import ee.qrental.transaction.core.validator.TransactionKindUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionKindValidatorConfig {

  @Bean
  AddRequestValidator<TransactionKindAddRequest> getTransactionKindAddRequestValidator(
      final TransactionKindLoadPort loadPort) {
    return new TransactionKindAddRequestValidator(loadPort);
  }

  @Bean
  UpdateRequestValidator<TransactionKindUpdateRequest> getTransactionKindUpdateRequestValidator(
      final TransactionKindLoadPort loadPort) {
    return new TransactionKindUpdateRequestValidator(loadPort);
  }

  @Bean
  DeleteRequestValidator<TransactionKindDeleteRequest> getTransactionKindDeleteRequestValidator(
      final TransactionKindLoadPort loadPort) {
    return new TransactionKindDeleteRequestValidator(loadPort);
  }
}
