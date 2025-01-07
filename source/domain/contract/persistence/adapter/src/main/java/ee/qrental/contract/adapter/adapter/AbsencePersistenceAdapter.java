package ee.qrental.contract.adapter.adapter;

import ee.qrental.contract.adapter.mapper.AbsenceAdapterMapper;
import ee.qrental.contract.adapter.repository.AbsenceRepository;
import ee.qrental.contract.api.out.*;
import ee.qrental.contract.domain.Absence;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsencePersistenceAdapter
    implements AbsenceAddPort, AbsenceUpdatePort, AbsenceDeletePort {

  private final AbsenceRepository repository;
  private final AbsenceAdapterMapper mapper;

  @Override
  public Absence add(final Absence domain) {
    final var savedEntity = repository.save(mapper.mapToEntity(domain));

    return mapper.mapToDomain(savedEntity);
  }

  @Override
  public Absence update(final Absence domain) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(domain)));
  }

  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }
}
