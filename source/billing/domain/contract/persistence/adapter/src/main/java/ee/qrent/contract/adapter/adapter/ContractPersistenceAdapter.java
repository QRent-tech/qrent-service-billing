package ee.qrent.contract.adapter.adapter;


import ee.qrent.contract.adapter.mapper.ContractAdapterMapper;
import ee.qrent.contract.adapter.repository.ContractRepository;
import ee.qrent.contract.api.out.ContractAddPort;
import ee.qrent.contract.api.out.ContractDeletePort;
import ee.qrent.contract.api.out.ContractUpdatePort;
import ee.qrent.contract.domain.Contract;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContractPersistenceAdapter
    implements ContractAddPort, ContractUpdatePort, ContractDeletePort {

  private final ContractRepository repository;
  private final ContractAdapterMapper mapper;

  @Override
  public Contract add(final Contract domain) {
    final var savedContractEntity = repository.save(mapper.mapToEntity(domain));

    return mapper.mapToDomain(savedContractEntity);
  }

  @Override
  public Contract update(final Contract domain) {
    repository.save(mapper.mapToEntity(domain));

    return domain;
  }

  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }
}
