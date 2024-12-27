package ee.qrental.transaction.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.transaction.api.in.request.type.TransactionTypeAddRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionTypeAddRequestValidator
    implements AddRequestValidator<TransactionTypeAddRequest> {

  public ViolationsCollector validate(final TransactionTypeAddRequest addRequest) {
    final var violationsCollector = new ViolationsCollector();

    return violationsCollector;
  }
}
