package ee.qrent.invoice.api.in.query;

import ee.qrent.common.in.query.BaseGetQuery;
import ee.qrent.invoice.api.in.request.InvoiceCalculationUpdateRequest;
import ee.qrent.invoice.api.in.response.InvoiceCalculationResponse;

public interface GetInvoiceCalculationQuery
    extends BaseGetQuery<InvoiceCalculationUpdateRequest, InvoiceCalculationResponse> {

  Long getLastCalculatedQWeekId();
}
