package ee.qrental.transaction.spring.config;

import ee.qrent.common.in.validation.AddRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.api.out.balance.BalanceLoadPort;
import ee.qrental.transaction.core.validator.TransactionAddRequestValidator;
import ee.qrental.transaction.core.validator.TransactionRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionValidatorConfig {

  @Bean
  TransactionRequestValidator getTransactionBusinessRuleValidator(
      final GetQWeekQuery qWeekQuery,
      final TransactionLoadPort transactionLoadPort,
      final BalanceLoadPort balanceLoadPort) {
    return new TransactionRequestValidator(qWeekQuery, transactionLoadPort, balanceLoadPort);
  }

  @Bean
  AddRequestValidator<TransactionAddRequest> getTransactionAddRequestValidator(
      final GetQWeekQuery qWeekQuery, final BalanceLoadPort balanceLoadPort) {
    return new TransactionAddRequestValidator(qWeekQuery, balanceLoadPort);
  }
}
