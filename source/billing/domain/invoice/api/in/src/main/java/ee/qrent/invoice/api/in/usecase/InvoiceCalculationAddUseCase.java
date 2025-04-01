package ee.qrent.invoice.api.in.usecase;

import ee.qrent.invoice.api.in.request.InvoiceCalculationAddRequest;

public interface InvoiceCalculationAddUseCase {

  void add(final InvoiceCalculationAddRequest request);
}
