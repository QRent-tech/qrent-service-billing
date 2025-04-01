package ee.qrent.transaction.adapter.repository.kind;

import ee.qrent.transaction.entity.jakarta.kind.TransactionKindJakartaEntity;
import java.util.List;

public interface TransactionKindRepository {
  TransactionKindJakartaEntity save(final TransactionKindJakartaEntity entity);

  void deleteById(final Long id);

  List<TransactionKindJakartaEntity> findAll();

  TransactionKindJakartaEntity getReferenceById(final Long id);

  TransactionKindJakartaEntity findByName(final String name);

  TransactionKindJakartaEntity findByCode(final String code);

  List<TransactionKindJakartaEntity> findAllByCodeIn(final List<String> codes);
}
