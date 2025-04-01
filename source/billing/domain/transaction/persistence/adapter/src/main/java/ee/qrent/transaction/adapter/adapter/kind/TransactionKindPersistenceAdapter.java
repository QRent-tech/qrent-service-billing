package ee.qrent.transaction.adapter.adapter.kind;

import ee.qrent.transaction.adapter.mapper.kind.TransactionKindAdapterMapper;
import ee.qrent.transaction.adapter.repository.kind.TransactionKindRepository;
import ee.qrent.transaction.api.out.kind.TransactionKindAddPort;
import ee.qrent.transaction.api.out.kind.TransactionKindDeletePort;
import ee.qrent.transaction.api.out.kind.TransactionKindUpdatePort;

import ee.qrent.transaction.domain.kind.TransactionKind;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionKindPersistenceAdapter
    implements TransactionKindAddPort, TransactionKindUpdatePort, TransactionKindDeletePort {

  private final TransactionKindRepository repository;
  private final TransactionKindAdapterMapper mapper;

  @Override
  public TransactionKind add(final TransactionKind transactionKind) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(transactionKind)));
  }

  @Override
  public TransactionKind update(final TransactionKind transactionKind) {
    final var kindFromDb = repository.getReferenceById(transactionKind.getId());
    kindFromDb.setName(transactionKind.getName());
    kindFromDb.setComment(transactionKind.getComment());

    return mapper.mapToDomain(repository.save(kindFromDb));
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
