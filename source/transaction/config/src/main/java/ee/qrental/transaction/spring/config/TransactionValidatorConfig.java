package ee.qrental.transaction.spring.config;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.in.request.TransactionDeleteRequest;
import ee.qrental.transaction.api.in.request.TransactionUpdateRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.core.validator.TransactionAddRequestValidator;
import ee.qrental.transaction.core.validator.TransactionDeleteRequestValidator;
import ee.qrental.transaction.core.validator.TransactionUpdateRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionValidatorConfig {

  @Bean
  AddRequestValidator<TransactionAddRequest> getTransactionAddRequestValidator(
      final GetQWeekQuery qWeekQuery,
      final TransactionLoadPort transactionLoadPort,
      final BalanceLoadPort balanceLoadPort) {
    return new TransactionAddRequestValidator(qWeekQuery, transactionLoadPort, balanceLoadPort);
  }

  @Bean
  UpdateRequestValidator<TransactionUpdateRequest> getTransactionUpdateRequestValidator(
      final GetQWeekQuery qWeekQuery,
      final TransactionLoadPort transactionLoadPort,
      final BalanceLoadPort balanceLoadPort) {
    return new TransactionUpdateRequestValidator(qWeekQuery, transactionLoadPort, balanceLoadPort);
  }

  @Bean
  DeleteRequestValidator<TransactionDeleteRequest> getTransactionDeleteRequestValidator(
      final GetQWeekQuery qWeekQuery,
      final TransactionLoadPort transactionLoadPort,
      final BalanceLoadPort balanceLoadPort) {
    return new TransactionDeleteRequestValidator(qWeekQuery, transactionLoadPort, balanceLoadPort);
  }
}
