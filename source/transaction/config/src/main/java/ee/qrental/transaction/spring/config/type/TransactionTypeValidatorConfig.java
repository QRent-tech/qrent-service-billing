package ee.qrental.transaction.spring.config.type;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.transaction.api.in.request.type.TransactionTypeAddRequest;
import ee.qrental.transaction.api.in.request.type.TransactionTypeUpdateRequest;
import ee.qrental.transaction.core.validator.TransactionTypeAddRequestValidator;
import ee.qrental.transaction.core.validator.TransactionTypeUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionTypeValidatorConfig {

  @Bean
  AddRequestValidator<TransactionTypeAddRequest> getTransactionTypeAddRequestValidator() {
    return new TransactionTypeAddRequestValidator();
  }

  @Bean
  UpdateRequestValidator<TransactionTypeUpdateRequest> getTransactionTypeUpdateRequestValidator() {
    return new TransactionTypeUpdateRequestValidator();
  }
}
