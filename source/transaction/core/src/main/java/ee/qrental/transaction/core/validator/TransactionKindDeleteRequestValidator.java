package ee.qrental.transaction.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.transaction.api.in.request.kind.TransactionKindDeleteRequest;
import ee.qrental.transaction.api.in.request.kind.TransactionKindUpdateRequest;
import ee.qrental.transaction.api.out.kind.TransactionKindLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionKindDeleteRequestValidator
    implements DeleteRequestValidator<TransactionKindDeleteRequest> {

  private final TransactionKindLoadPort loadPort;

  public ViolationsCollector validate(final TransactionKindDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    // check references

    return violationsCollector;
  }
}
