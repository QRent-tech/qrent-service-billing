package ee.qrent.driver.adapter.adapter;

import ee.qrent.driver.adapter.mapper.FirmLinkAdapterMapper;
import ee.qrent.driver.adapter.repository.FirmLinkRepository;
import ee.qrent.driver.api.out.*;
import ee.qrent.driver.domain.FirmLink;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FirmLinkPersistenceAdapter
    implements FirmLinkAddPort, FirmLinkUpdatePort, FirmLinkDeletePort {

  private final FirmLinkRepository repository;
  private final FirmLinkAdapterMapper mapper;

  @Override
  public FirmLink add(final FirmLink domain) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(domain)));
  }

  @Override
  public FirmLink update(final FirmLink domain) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(domain)));
  }

  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }
}
