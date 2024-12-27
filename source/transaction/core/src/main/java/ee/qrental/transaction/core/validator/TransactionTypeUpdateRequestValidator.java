package ee.qrental.transaction.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.transaction.api.in.request.type.TransactionTypeUpdateRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionTypeUpdateRequestValidator
    implements UpdateRequestValidator<TransactionTypeUpdateRequest> {

  public ViolationsCollector validate(final TransactionTypeUpdateRequest updateRequest) {
    final var violationsCollector = new ViolationsCollector();
    // TODO add update check

    return violationsCollector;
  }
}
