package ee.qrental.contract.core.service;

import static java.util.stream.Collectors.toList;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.constant.api.in.query.GetQWeekQuery;
import ee.qrental.contract.api.in.query.GetAbsenceQuery;
import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.request.AbsenceUpdateRequest;
import ee.qrental.contract.api.in.response.AbsenceResponse;
import ee.qrental.contract.api.in.response.ContractResponse;
import ee.qrental.contract.api.out.AbsenceLoadPort;
import ee.qrental.contract.core.mapper.AbsenceResponseMapper;
import ee.qrental.contract.core.mapper.AbsenceUpdateRequestMapper;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbsenceQueryService implements GetAbsenceQuery {

  private static final Long DEFAULT_PERIOD_IN_WEEKS = 10L;

  private final GetContractQuery contractQuery;
  private final AbsenceLoadPort loadPort;
  private final AbsenceResponseMapper mapper;
  private final AbsenceUpdateRequestMapper updateRequestMapper;
  private final QDateTime qDateTime;

  @Override
  public List<AbsenceResponse> getAll() {
    return loadPort.loadAll().stream().map(mapper::toResponse).collect(toList());
  }

  @Override
  public AbsenceResponse getById(final Long id) {
    return mapper.toResponse(loadPort.loadById(id));
  }

  @Override
  public String getObjectInfo(Long id) {
    return mapper.toObjectInfo(loadPort.loadById(id));
  }

  @Override
  public AbsenceUpdateRequest getUpdateRequestById(Long id) {

    return updateRequestMapper.toRequest(loadPort.loadById(id));
  }

  @Override
  public List<AbsenceResponse> getActualAbsencesByDriverId(final Long driverId) {
    final var activeContract = contractQuery.getCurrentActiveByDriverId(driverId);
    final var startDate = getContractLastProlongationStartDate(activeContract);

    return loadPort
        .loadByDriverIdAndDateStart(driverId, startDate)
        .stream()
        .map(mapper::toResponse)
        .collect(toList());
  }

  private LocalDate getContractLastProlongationStartDate(final ContractResponse activeContract) {
    if (activeContract == null) {
      final var today = qDateTime.getToday();

      return today.minusWeeks(DEFAULT_PERIOD_IN_WEEKS);
    }

    final var contractEndDate = activeContract.getDateEnd();
    final var contractDuration = activeContract.getDuration();

    return contractEndDate.minusWeeks(contractDuration);
  }
}
