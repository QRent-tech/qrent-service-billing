package ee.qrent.transaction.api.in.request.type;

import ee.qrent.common.in.request.AbstractDeleteRequest;

public class TransactionTypeDeleteRequest extends AbstractDeleteRequest {
    public TransactionTypeDeleteRequest(final Long id) {
        super(id);
    }
}