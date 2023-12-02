package ee.qrental.transaction.repository.impl.kind;

import ee.qrental.transaction.adapter.repository.kind.TransactionKindRepository;
import ee.qrental.transaction.entity.jakarta.kind.TransactionKindJakartaEntity;
import ee.qrental.transaction.repository.spring.kind.TransactionKindSpringDataRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionKindRepositoryImpl implements TransactionKindRepository {

  private final TransactionKindSpringDataRepository springDataRepository;

  @Override
  public List<TransactionKindJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public TransactionKindJakartaEntity getReferenceById(final Long id) {
    return springDataRepository.getReferenceById(id);
  }

  @Override
  public TransactionKindJakartaEntity findByName(final String name) {
    return springDataRepository.findByName(name);
  }

  @Override
  public TransactionKindJakartaEntity findByCode(final String code) {
    return springDataRepository.findByCode(code);
  }

  @Override
  public TransactionKindJakartaEntity save(final TransactionKindJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public void deleteById(final Long id) {
    springDataRepository.deleteById(id);
  }
}
