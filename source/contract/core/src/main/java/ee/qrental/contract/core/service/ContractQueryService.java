package ee.qrental.contract.core.service;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.request.ContractUpdateRequest;
import ee.qrental.contract.api.in.response.ContractResponse;
import ee.qrental.contract.api.out.ContractLoadPort;
import ee.qrental.contract.core.mapper.ContractResponseMapper;

import java.util.Comparator;
import java.util.List;

import ee.qrental.contract.core.mapper.ContractUpdateRequestMapper;
import ee.qrental.contract.domain.ContractDuration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContractQueryService implements GetContractQuery {

  private final Comparator<ContractResponse> DEFAULT_COMPARATOR =
      comparing(ContractResponse::getCreated);

  private final ContractLoadPort loadPort;
  private final ContractResponseMapper mapper;
  private final ContractUpdateRequestMapper updateRequestMapper;
  private final QDateTime qDateTime;

  @Override
  public List<ContractResponse> getAll() {
    return loadPort.loadAll().stream()
        .map(mapper::toResponse)
        .sorted(DEFAULT_COMPARATOR.reversed())
        .collect(toList());
  }

  @Override
  public ContractResponse getById(final Long id) {
    return mapper.toResponse(loadPort.loadById(id));
  }

  @Override
  public String getObjectInfo(final Long id) {
    return mapper.toObjectInfo(loadPort.loadById(id));
  }

  @Override
  public ContractUpdateRequest getUpdateRequestById(final Long id) {

    return updateRequestMapper.toRequest(loadPort.loadById(id));
  }

  @Override
  public ContractResponse getLatestContractByDriverId(Long driverId) {
    return mapper.toResponse(loadPort.loadLatestByDriverId(driverId));
  }

  @Override
  public List<String> getAllDurations() {
    return stream(ContractDuration.values()).map(ContractDuration::getLabel).toList();
  }

  @Override
  public List<ContractResponse> getAllActive() {
    return loadPort.loadActiveByDate(qDateTime.getToday()).stream()
        .map(mapper::toResponse)
        .sorted(DEFAULT_COMPARATOR.reversed())
        .collect(toList());
  }

  @Override
  public ContractResponse getCurrentActiveByDriverId(final Long driverId) {
    final var today = qDateTime.getToday();
    final var currentActiveContract = loadPort.loadActiveByDateAndDriverId(today, driverId);

    return mapper.toResponse(currentActiveContract);
  }

  @Override
  public List<ContractResponse> getClosed() {
    return loadPort.loadClosedByDate(qDateTime.getToday()).stream()
        .map(mapper::toResponse)
        .sorted(DEFAULT_COMPARATOR.reversed())
        .collect(toList());
  }

  @Override
  public Long getCountActive() {
    return loadPort.loadCountActiveByDate(qDateTime.getToday());
  }

  @Override
  public Long getCountClosed() {
    return loadPort.loadCountClosedByDate(qDateTime.getToday());
  }
}
