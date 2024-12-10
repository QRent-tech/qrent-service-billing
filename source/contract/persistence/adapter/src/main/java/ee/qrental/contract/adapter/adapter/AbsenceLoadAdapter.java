package ee.qrental.contract.adapter.adapter;

import static java.util.stream.Collectors.toList;

import ee.qrental.contract.adapter.mapper.AbsenceAdapterMapper;
import ee.qrental.contract.adapter.repository.AbsenceRepository;
import ee.qrental.contract.api.out.AbsenceLoadPort;

import ee.qrental.contract.domain.Absence;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsenceLoadAdapter implements AbsenceLoadPort {

  private final AbsenceRepository repository;
  private final AbsenceAdapterMapper mapper;

  @Override
  public Absence loadByDriverIdAndQWekId(Long driverId, Long qWeekId) {
    return null;
  }

  @Override
  public List<Absence> loadByDriverIdAndStartQWeekId(final Long driverId, final Long startQWeekId) {
    return repository.findByDriverIdAndStartQWeekId(driverId, startQWeekId).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public List<Absence> loadAll() {
    return repository.findAll().stream().map(mapper::mapToDomain).collect(toList());
  }

  @Override
  public Absence loadById(Long id) {
    return mapper.mapToDomain(repository.findById(id));
  }
}
