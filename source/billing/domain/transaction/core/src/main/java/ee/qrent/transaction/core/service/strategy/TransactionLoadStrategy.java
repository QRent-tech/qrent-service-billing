package ee.qrent.transaction.core.service.strategy;

import ee.qrent.transaction.api.in.query.filter.YearAndWeekAndDriverAndFeeFilter;
import ee.qrent.transaction.domain.Transaction;
import java.util.List;

public interface TransactionLoadStrategy {
    boolean canApply(final YearAndWeekAndDriverAndFeeFilter request);

    List<Transaction> load(final YearAndWeekAndDriverAndFeeFilter request);
}
