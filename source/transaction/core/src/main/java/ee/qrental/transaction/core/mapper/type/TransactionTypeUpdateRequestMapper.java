package ee.qrental.transaction.core.mapper.type;

import ee.qrental.common.core.in.mapper.UpdateRequestMapper;
import ee.qrental.transaction.api.in.request.type.TransactionTypeUpdateRequest;
import ee.qrental.transaction.domain.kind.TransactionKind;
import ee.qrental.transaction.domain.type.TransactionType;

public class TransactionTypeUpdateRequestMapper
    implements UpdateRequestMapper<TransactionTypeUpdateRequest, TransactionType> {

  @Override
  public TransactionType toDomain(final TransactionTypeUpdateRequest request) {
    return TransactionType.builder()
        .id(request.getId())
        .name(request.getName())
        .description(request.getDescription())
        .invoiceName(request.getInvoiceName())
        .kind(TransactionKind.builder().id(request.getTransactionKindId()).build())
        .comment(request.getComment())
        .build();
  }

  @Override
  public TransactionTypeUpdateRequest toRequest(final TransactionType domain) {
    final var kind = domain.getKind();
    final var kindId = kind == null ? null : kind.getId();

    return TransactionTypeUpdateRequest.builder()
        .id(domain.getId())
        .name(domain.getName())
        .description(domain.getDescription())
        .invoiceName(domain.getInvoiceName())
        .transactionKindId(kindId)
        .comment(domain.getComment())
        .build();
  }
}
