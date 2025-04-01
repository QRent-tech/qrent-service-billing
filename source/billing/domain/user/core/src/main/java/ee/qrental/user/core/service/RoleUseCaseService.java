package ee.qrental.user.core.service;

import ee.qrental.user.api.in.request.*;
import ee.qrental.user.api.in.usecase.*;
import ee.qrental.user.api.out.*;
import ee.qrental.user.core.mapper.RoleAddRequestMapper;
import ee.qrental.user.core.mapper.RoleUpdateRequestMapper;
import ee.qrental.user.core.validator.RoleRequestValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleUseCaseService implements RoleAddUseCase, RoleUpdateUseCase, RoleDeleteUseCase {

  private final RoleAddPort addPort;
  private final RoleUpdatePort updatePort;
  private final RoleDeletePort deletePort;
  private final RoleLoadPort loadPort;
  private final RoleAddRequestMapper addRequestMapper;
  private final RoleUpdateRequestMapper updateRequestMapper;
  private final RoleRequestValidator requestValidator;

  @Transactional
  @Override
  public Long add(final RoleAddRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return null;
    }
    final var domain = addRequestMapper.toDomain(request);
    final var savedDomain = addPort.add(domain);

    return savedDomain.getId();
  }

  @Override
  public void update(final RoleUpdateRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }
    updatePort.update(updateRequestMapper.toDomain(request));
  }

  @Override
  public void delete(final RoleDeleteRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }
    deletePort.delete(request.getId());
  }
}
