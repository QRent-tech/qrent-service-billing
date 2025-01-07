package ee.qrental.transaction.core.service;

import ee.qrental.transaction.api.in.request.TransactionAddRequest;
import ee.qrental.transaction.api.in.request.TransactionDeleteRequest;
import ee.qrental.transaction.api.in.request.TransactionUpdateRequest;
import ee.qrental.transaction.api.in.usecase.TransactionAddUseCase;
import ee.qrental.transaction.api.in.usecase.TransactionDeleteUseCase;
import ee.qrental.transaction.api.in.usecase.TransactionUpdateUseCase;
import ee.qrental.transaction.api.out.TransactionAddPort;
import ee.qrental.transaction.api.out.TransactionDeletePort;
import ee.qrental.transaction.api.out.TransactionUpdatePort;
import ee.qrental.transaction.core.mapper.TransactionAddRequestMapper;
import ee.qrental.transaction.core.mapper.TransactionUpdateRequestMapper;
import ee.qrental.transaction.core.validator.TransactionAddRequestValidator;
import ee.qrental.transaction.core.validator.TransactionRequestValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionUseCaseService
    implements TransactionAddUseCase, TransactionUpdateUseCase, TransactionDeleteUseCase {

  private final TransactionAddPort addPort;
  private final TransactionUpdatePort updatePort;
  private final TransactionDeletePort deletePort;
  private final TransactionAddRequestMapper addRequestMapper;
  private final TransactionUpdateRequestMapper updateRequestMapper;
  private final TransactionRequestValidator requestValidator;
  private final TransactionAddRequestValidator addRequestValidator;

  @Override
  public Long add(final TransactionAddRequest request) {
    final var violationsCollector = addRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return null;
    }
    return addPort.add(addRequestMapper.toDomain(request)).getId();
  }

  @Override
  public void update(final TransactionUpdateRequest request) {

    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var domain = updateRequestMapper.toDomain(request);
    updatePort.update(domain);
  }

  @Override
  public void delete(final TransactionDeleteRequest request) {
    final var transactionId = request.getId();
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    deletePort.delete(transactionId);
  }
}
