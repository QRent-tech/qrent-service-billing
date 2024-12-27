package ee.qrental.transaction.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.transaction.api.in.request.kind.TransactionKindUpdateRequest;
import ee.qrental.transaction.api.out.kind.TransactionKindLoadPort;
import lombok.AllArgsConstructor;

import javax.xml.validation.Validator;

@AllArgsConstructor
public class TransactionKindUpdateRequestValidator
    implements UpdateRequestValidator<TransactionKindUpdateRequest> {

  private final TransactionKindLoadPort loadPort;

  public ViolationsCollector validate(final TransactionKindUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request, violationsCollector);

    return violationsCollector;
  }

  private void checkExistence(
      final TransactionKindUpdateRequest updateRequest,
      final ViolationsCollector violationCollector) {
    final var id = updateRequest.getId();
    if (loadPort.loadById(id) == null) {
      violationCollector.collect("Update of Transaction Kind failed. No Record with id = " + id);
      throw new RuntimeException();
    }
  }
}
