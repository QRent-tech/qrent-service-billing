package ee.qrent.insurance.adapter.adapter;

import ee.qrent.insurance.adapter.mapper.InsuranceCaseBalanceAdapterMapper;
import ee.qrent.insurance.adapter.repository.InsuranceCaseBalanceRepository;
import ee.qrent.insurance.api.out.InsuranceCaseBalanceAddPort;
import ee.qrent.insurance.domain.InsuranceCaseBalance;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceCaseBalancePersistenceAdapter implements InsuranceCaseBalanceAddPort {

  private final InsuranceCaseBalanceRepository repository;
  private final InsuranceCaseBalanceAdapterMapper mapper;

  @Override
  public InsuranceCaseBalance add(final InsuranceCaseBalance domain) {
    final var savedEntity = repository.save(mapper.mapToEntity(domain));
    return mapper.mapToDomain(savedEntity);
  }
}
