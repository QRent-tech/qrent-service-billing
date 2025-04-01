package ee.qrent.transaction.api.out.type;

import ee.qrent.common.out.port.LoadPort;
import ee.qrent.transaction.domain.type.TransactionType;
import java.util.List;

public interface TransactionTypeLoadPort extends LoadPort<TransactionType> {

  TransactionType loadByName(final String name);

  List<TransactionType> loadByKindCodesIn(final List<String> kindCodes);
  List<TransactionType> loadByNameIn(final List<String> names);
}
