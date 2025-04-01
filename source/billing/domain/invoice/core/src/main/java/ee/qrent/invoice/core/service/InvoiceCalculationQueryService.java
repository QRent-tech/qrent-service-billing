package ee.qrent.invoice.core.service;

import static java.util.stream.Collectors.toList;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.invoice.api.in.query.GetInvoiceCalculationQuery;
import ee.qrent.invoice.api.in.request.InvoiceCalculationUpdateRequest;
import ee.qrent.invoice.api.in.response.InvoiceCalculationResponse;
import ee.qrent.invoice.api.out.InvoiceCalculationLoadPort;
import ee.qrent.invoice.core.mapper.InvoiceCalculationResponseMapper;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceCalculationQueryService implements GetInvoiceCalculationQuery {

  private final GetQWeekQuery qWeekQuery;
  private final InvoiceCalculationLoadPort loadPort;
  private final InvoiceCalculationResponseMapper responseMapper;

  @Override
  public List<InvoiceCalculationResponse> getAll() {
    return loadPort.loadAll().stream().map(responseMapper::toResponse).collect(toList());
  }

  @Override
  public InvoiceCalculationResponse getById(final Long id) {
    return responseMapper.toResponse(loadPort.loadById(id));
  }

  @Override
  public String getObjectInfo(final Long id) {
    return responseMapper.toObjectInfo(loadPort.loadById(id));
  }

  @Override
  public InvoiceCalculationUpdateRequest getUpdateRequestById(final Long id) {
    return null;
  }

  @Override
  public Long getLastCalculatedQWeekId() {
    final var lastCalculation = loadPort.loadLastCalculation();

    return lastCalculation == null ? null : lastCalculation.getEndQWeekId();
  }
}
