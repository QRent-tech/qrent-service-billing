package ee.qrent.invoice.api.in.usecase;

import ee.qrent.invoice.api.in.request.InvoiceSendByEmailRequest;

public interface InvoiceSendByEmailUseCase {

  void sendByEmail(final InvoiceSendByEmailRequest request);
}
