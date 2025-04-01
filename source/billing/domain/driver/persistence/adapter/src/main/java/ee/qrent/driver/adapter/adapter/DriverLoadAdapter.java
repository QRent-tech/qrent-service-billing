package ee.qrent.driver.adapter.adapter;

import static java.util.stream.Collectors.toList;

import ee.qrent.driver.adapter.mapper.DriverAdapterMapper;
import ee.qrent.driver.adapter.repository.DriverRepository;
import ee.qrent.driver.api.out.DriverLoadPort;
import ee.qrent.driver.domain.Driver;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DriverLoadAdapter implements DriverLoadPort {

  private final DriverRepository repository;
  private final DriverAdapterMapper mapper;

  @Override
  public List<Driver> loadAll() {
    return repository.findAll().stream().map(mapper::mapToDomain).collect(toList());
  }

  @Override
  public Driver loadById(final Long id) {
    return mapper.mapToDomain(repository.findById(id));
  }

  @Override
  public List<Driver> loadByMatchCountAndQWeekId(final Integer matchCount, final Long qWeekId) {
    return repository.findAllByMatchCountAndQWeekId(matchCount, qWeekId).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public Driver loadByTaxNumber(final Long taxNumber) {
    return mapper.mapToDomain(repository.findByTaxNumber(taxNumber));
  }
}
