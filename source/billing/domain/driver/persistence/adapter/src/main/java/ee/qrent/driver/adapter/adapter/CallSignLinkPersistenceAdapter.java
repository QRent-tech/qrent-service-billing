package ee.qrent.driver.adapter.adapter;

import ee.qrent.driver.adapter.mapper.CallSignLinkAdapterMapper;
import ee.qrent.driver.adapter.repository.CallSignLinkRepository;
import ee.qrent.driver.api.out.CallSignLinkAddPort;
import ee.qrent.driver.api.out.CallSignLinkDeletePort;
import ee.qrent.driver.api.out.CallSignLinkUpdatePort;
import ee.qrent.driver.domain.CallSignLink;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignLinkPersistenceAdapter
    implements CallSignLinkAddPort, CallSignLinkUpdatePort, CallSignLinkDeletePort {

  private final CallSignLinkRepository repository;
  private final CallSignLinkAdapterMapper mapper;

  @Override
  public CallSignLink add(final CallSignLink domain) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(domain)));
  }

  @Override
  public CallSignLink update(final CallSignLink domain) {
    return mapper.mapToDomain(repository.save(mapper.mapToEntity(domain)));
  }

  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }
}
