package ee.qrent.driver.adapter.adapter;

import static java.util.stream.Collectors.toList;

import ee.qrent.driver.adapter.mapper.CallSignAdapterMapper;
import ee.qrent.driver.adapter.repository.CallSignRepository;
import ee.qrent.driver.api.out.CallSignLoadPort;
import ee.qrent.driver.domain.CallSign;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignLoadAdapter implements CallSignLoadPort {

  private final CallSignRepository repository;
  private final CallSignAdapterMapper mapper;

  @Override
  public List<CallSign> loadAll() {
    return repository.findAll().stream().map(mapper::mapToDomain).collect(toList());
  }

  @Override
  public List<CallSign> loadAvailable() {
    final var nowDate = LocalDate.now();

    return repository.findAvailable(nowDate).stream().map(mapper::mapToDomain).collect(toList());
  }

  @Override
  public CallSign loadById(Long id) {
    return mapper.mapToDomain(repository.getReferenceById(id));
  }

  @Override
  public CallSign loadByCallSign(final Integer callSign) {
    return mapper.mapToDomain(repository.findByCallSign(callSign));
  }
}
