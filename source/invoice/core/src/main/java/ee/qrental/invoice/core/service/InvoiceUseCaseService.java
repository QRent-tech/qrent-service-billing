package ee.qrental.invoice.core.service;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.invoice.api.in.request.InvoiceAddRequest;
import ee.qrental.invoice.api.in.request.InvoiceDeleteRequest;
import ee.qrental.invoice.api.in.request.InvoiceUpdateRequest;
import ee.qrental.invoice.api.in.usecase.InvoiceAddUseCase;
import ee.qrental.invoice.api.in.usecase.InvoiceDeleteUseCase;
import ee.qrental.invoice.api.in.usecase.InvoiceUpdateUseCase;
import ee.qrental.invoice.api.out.InvoiceAddPort;
import ee.qrental.invoice.api.out.InvoiceDeletePort;
import ee.qrental.invoice.api.out.InvoiceUpdatePort;
import ee.qrental.invoice.core.mapper.InvoiceAddRequestMapper;
import ee.qrental.invoice.core.mapper.InvoiceUpdateRequestMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional(SUPPORTS)
@AllArgsConstructor
public class InvoiceUseCaseService
    implements InvoiceAddUseCase, InvoiceUpdateUseCase, InvoiceDeleteUseCase {

  private final InvoiceAddPort addPort;
  private final InvoiceUpdatePort updatePort;
  private final InvoiceDeletePort deletePort;
  private final InvoiceAddRequestMapper addRequestMapper;
  private final InvoiceUpdateRequestMapper updateRequestMapper;
  private final AddRequestValidator<InvoiceAddRequest> addRequestValidator;
  private final UpdateRequestValidator<InvoiceUpdateRequest> updateRequestValidator;
  private final DeleteRequestValidator<InvoiceDeleteRequest> deleteRequestValidator;

  @Override
  public Long add(final InvoiceAddRequest request) {
    final var violationsCollector = addRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return null;
    }
    final var invoice = addRequestMapper.toDomain(request);
    final var savedInvoice = addPort.add(invoice);

    return savedInvoice.getId();
  }

  @Override
  public void update(final InvoiceUpdateRequest request) {
    final var violationsCollector = updateRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    updatePort.update(updateRequestMapper.toDomain(request));
  }

  @Transactional
  @Override
  public void delete(final InvoiceDeleteRequest request) {
    final var violationsCollector = deleteRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }

    deletePort.delete(request.getId());
  }
}
