package ee.qrental.contract.core.service;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

import ee.qrental.contract.api.in.request.*;
import ee.qrental.contract.api.in.usecase.*;
import ee.qrental.contract.api.out.*;
import ee.qrental.contract.core.mapper.AbsenceAddRequestMapper;
import ee.qrental.contract.core.mapper.AbsenceUpdateRequestMapper;
import ee.qrental.contract.core.validator.AbsenceUpdateRequestValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional(SUPPORTS)
@AllArgsConstructor
public class AbsenceUseCaseService
    implements AbsenceAddUseCase, AbsenceUpdateUseCase, AbsenceDeleteUseCase {
  private final AbsenceAddPort addPort;
  private final AbsenceUpdatePort updatePort;
  private final AbsenceDeletePort deletePort;
  private final AbsenceAddRequestMapper addRequestMapper;
  private final AbsenceUpdateRequestMapper updateRequestMapper;
  private final AbsenceUpdateRequestValidator businessRuleValidator;

  @Override
  public Long add(final AbsenceAddRequest request) {
    final var violationsCollector = businessRuleValidator.validateAdd(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return null;
    }
    final var absence = addRequestMapper.toDomain(request);
    final var savedAbsence = addPort.add(absence);

    return savedAbsence.getId();
  }

  @Override
  public void update(final AbsenceUpdateRequest request) {
    final var violationsCollector = businessRuleValidator.validateUpdate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var absence = updateRequestMapper.toDomain(request);
    updatePort.update(absence);
  }

  @Override
  public void delete(final AbsenceDeleteRequest request) {
    final var violationsCollector = businessRuleValidator.validateDelete(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    deletePort.delete(request.getId());
  }
}
