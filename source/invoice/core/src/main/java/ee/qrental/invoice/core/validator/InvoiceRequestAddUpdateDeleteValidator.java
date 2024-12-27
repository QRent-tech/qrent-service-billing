package ee.qrental.invoice.core.validator;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.invoice.api.in.request.InvoiceAddRequest;
import ee.qrental.invoice.api.in.request.InvoiceDeleteRequest;
import ee.qrental.invoice.api.in.request.InvoiceUpdateRequest;
import ee.qrental.invoice.api.out.InvoiceLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceRequestAddUpdateDeleteValidator
    implements AddRequestValidator<InvoiceAddRequest>,
        UpdateRequestValidator<InvoiceUpdateRequest>,
        DeleteRequestValidator<InvoiceDeleteRequest> {

  private final InvoiceLoadPort loadPort;
  private final GetQWeekQuery qWeekQuery;

  @Override
  public ViolationsCollector validate(final InvoiceAddRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkUniqueness(request, violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final InvoiceUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request.getId(), violationsCollector);

    return violationsCollector;
  }

  @Override
  public ViolationsCollector validate(final InvoiceDeleteRequest request) {
    // TODO check references
    return null;
  }

  private void checkUniqueness(
      final InvoiceAddRequest request, final ViolationsCollector violationCollector) {
    //TODO change to qWeek, instead of year + week number
    final var qWeek =
        qWeekQuery.getByYearAndNumber(request.getYear(), request.getWeek().getNumber());

    final var invoiceFromDb =
        loadPort.loadByWeekAndDriverAndFirm(
            qWeek.getId(), request.getDriverId(), request.getQFirmId());
    if (invoiceFromDb == null) {
      return;
    }
    violationCollector.collect("Invoice for used Driver, Week, and Q-firm, already exist");
  }

  private void checkExistence(final Long id, final ViolationsCollector violationCollector) {
    if (loadPort.loadById(id) == null) {
      violationCollector.collect("Update of Invoice failed. No Record with id = " + id);
    }
  }
}
