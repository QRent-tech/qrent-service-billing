package ee.qrent.transaction.spring.config.type;

import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.transaction.api.in.request.type.TransactionTypeUpdateRequest;
import ee.qrent.transaction.core.validator.TransactionTypeAddRequestValidator;
import ee.qrent.transaction.core.validator.TransactionTypeUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionTypeValidatorConfig {

  @Bean
  TransactionTypeAddRequestValidator getTransactionTypeAddRequestValidator() {

    return new TransactionTypeAddRequestValidator();
  }

  @Bean
  UpdateRequestValidator<TransactionTypeUpdateRequest> getTransactionTypeUpdateRequestValidator() {

    return new TransactionTypeUpdateRequestValidator();
  }
}
