package ee.qrent.transaction.adapter.adapter.type;

import ee.qrent.transaction.adapter.mapper.type.TransactionTypeAdapterMapper;
import ee.qrent.transaction.adapter.repository.TransactionTypeRepository;
import ee.qrent.transaction.api.out.type.TransactionTypeAddPort;
import ee.qrent.transaction.api.out.type.TransactionTypeDeletePort;
import ee.qrent.transaction.api.out.type.TransactionTypeUpdatePort;
import ee.qrent.transaction.domain.type.TransactionType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionTypePersistenceAdapter
    implements TransactionTypeAddPort, TransactionTypeUpdatePort, TransactionTypeDeletePort {

  private final TransactionTypeRepository repository;
  private final TransactionTypeAdapterMapper mapper;

  @Override
  public TransactionType add(final TransactionType transactionType) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(transactionType)));
  }

  @Override
  public TransactionType update(final TransactionType transactionType) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(transactionType)));
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
