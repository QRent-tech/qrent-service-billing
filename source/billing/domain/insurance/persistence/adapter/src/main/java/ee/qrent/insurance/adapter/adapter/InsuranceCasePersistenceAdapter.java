package ee.qrent.insurance.adapter.adapter;

import ee.qrent.insurance.adapter.mapper.InsuranceCaseAdapterMapper;
import ee.qrent.insurance.adapter.repository.InsuranceCaseRepository;
import ee.qrent.insurance.api.out.InsuranceCaseAddPort;
import ee.qrent.insurance.api.out.InsuranceCaseUpdatePort;
import ee.qrent.insurance.domain.InsuranceCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceCasePersistenceAdapter
    implements InsuranceCaseAddPort, InsuranceCaseUpdatePort {

  private final InsuranceCaseRepository repository;
  private final InsuranceCaseAdapterMapper mapper;

  @Override
  public InsuranceCase add(final InsuranceCase domain) {
    final var savedEntity = repository.save(mapper.mapToEntity(domain));
    return mapper.mapToDomain(savedEntity);
  }

  @Override
  public InsuranceCase update(final InsuranceCase domain) {
    repository.save(mapper.mapToEntity(domain));

    return domain;
  }
}
