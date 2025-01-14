package ee.qrental.driver.core.service;

import ee.qrental.driver.api.in.request.CallSignAddRequest;
import ee.qrental.driver.api.in.request.CallSignDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignUpdateRequest;
import ee.qrental.driver.api.in.usecase.CallSignAddUseCase;
import ee.qrental.driver.api.in.usecase.CallSignDeleteUseCase;
import ee.qrental.driver.api.in.usecase.CallSignUpdateUseCase;
import ee.qrental.driver.api.out.CallSignAddPort;
import ee.qrental.driver.api.out.CallSignDeletePort;
import ee.qrental.driver.api.out.CallSignLoadPort;
import ee.qrental.driver.api.out.CallSignUpdatePort;
import ee.qrental.driver.core.mapper.CallSignAddRequestMapper;
import ee.qrental.driver.core.mapper.CallSignUpdateRequestMapper;
import ee.qrental.driver.core.validator.CallSignRequestValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignUseCaseService
    implements CallSignAddUseCase, CallSignUpdateUseCase, CallSignDeleteUseCase {

  private final CallSignAddPort addPort;
  private final CallSignUpdatePort updatePort;
  private final CallSignDeletePort deletePort;
  private final CallSignAddRequestMapper addRequestMapper;
  private final CallSignUpdateRequestMapper updateRequestMapper;
  private final CallSignRequestValidator requestValidator;

  @Override
  public Long add(final CallSignAddRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return null;
    }

    final var domain = addRequestMapper.toDomain(request);
    return addPort.add(domain).getId();
  }

  @Override
  public void update(final CallSignUpdateRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var domain = updateRequestMapper.toDomain(request);
    updatePort.update(domain);
  }

  @Override
  public void delete(final CallSignDeleteRequest request) {
    final var violationsCollector = requestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }
    deletePort.delete(request.getId());
  }
}
