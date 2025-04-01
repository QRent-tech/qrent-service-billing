package ee.qrental.transaction.spring.config;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.UpdateRequestValidator;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
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
  UpdateRequestValidator<TransactionUpdateRequest> getTransactionUpdateRequestValidator(
      final GetQWeekQuery qWeekQuery,
      final BalanceLoadPort balanceLoadPort,
      final TransactionLoadPort transactionLoadPort) {
    return new TransactionUpdateRequestValidator(qWeekQuery, balanceLoadPort, transactionLoadPort);
  }

  @Bean
  AddRequestValidator<TransactionAddRequest> getTransactionAddRequestValidator(
      final GetQWeekQuery qWeekQuery,
      final BalanceLoadPort balanceLoadPort,
      final TransactionLoadPort transactionLoadPort) {
    return new TransactionAddRequestValidator(qWeekQuery, balanceLoadPort, transactionLoadPort);
  }

  @Bean
  DeleteRequestValidator<TransactionDeleteRequest> getTransactionDeleteRequestValidator(
      final GetQWeekQuery qWeekQuery,
      final BalanceLoadPort balanceLoadPort,
      final TransactionLoadPort transactionLoadPort) {
    return new TransactionDeleteRequestValidator(qWeekQuery, balanceLoadPort, transactionLoadPort);
  }
}
