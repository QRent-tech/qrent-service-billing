package ee.qrent.transaction.adapter.adapter;

import ee.qrent.transaction.adapter.mapper.TransactionAdapterMapper;
import ee.qrent.transaction.adapter.repository.TransactionRepository;
import ee.qrent.transaction.api.out.TransactionAddPort;
import ee.qrent.transaction.api.out.TransactionDeletePort;
import ee.qrent.transaction.api.out.TransactionUpdatePort;
import ee.qrent.transaction.domain.Transaction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionPersistenceAdapter
    implements TransactionAddPort, TransactionUpdatePort, TransactionDeletePort {

  private final TransactionRepository repository;
  private final TransactionAdapterMapper mapper;

  @Override
  public Transaction add(final Transaction transaction) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(transaction)));
  }

  @Override
  public Transaction update(final Transaction transaction) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(transaction)));
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
