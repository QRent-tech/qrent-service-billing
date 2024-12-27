package ee.qrental.driver.core.service;

import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.driver.api.in.request.CallSignAddRequest;
import ee.qrental.driver.api.in.request.CallSignDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignUpdateRequest;
import ee.qrental.driver.api.in.usecase.CallSignAddUseCase;
import ee.qrental.driver.api.in.usecase.CallSignDeleteUseCase;
import ee.qrental.driver.api.in.usecase.CallSignUpdateUseCase;
import ee.qrental.driver.api.out.CallSignAddPort;
import ee.qrental.driver.api.out.CallSignDeletePort;
import ee.qrental.driver.api.out.CallSignUpdatePort;
import ee.qrental.driver.core.mapper.CallSignAddRequestMapper;
import ee.qrental.driver.core.mapper.CallSignUpdateRequestMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignUseCaseService
    implements CallSignAddUseCase, CallSignUpdateUseCase, CallSignDeleteUseCase {

  private final CallSignAddPort addPort;
  private final CallSignUpdatePort updatePort;
  private final CallSignDeletePort deletePort;
  private final CallSignAddRequestMapper addRequestMapper;
  private final CallSignUpdateRequestMapper updateRequestMapper;
  private final AddRequestValidator<CallSignAddRequest> addRequestValidator;
  private final UpdateRequestValidator<CallSignUpdateRequest> updateRequestValidator;
  private final DeleteRequestValidator<CallSignDeleteRequest> deleteRequestValidator;

  @Override
  public Long add(final CallSignAddRequest request) {
    final var domain = addRequestMapper.toDomain(request);
    final var violationsCollector = addRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return null;
    }

    return addPort.add(addRequestMapper.toDomain(request)).getId();
  }

  @Override
  public void update(final CallSignUpdateRequest request) {
    final var domain = updateRequestMapper.toDomain(request);
    final var violationsCollector = updateRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    updatePort.update(updateRequestMapper.toDomain(request));
  }

  @Override
  public void delete(final CallSignDeleteRequest request) {
    final var violationsCollector = deleteRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return;
    }
    deletePort.delete(request.getId());
  }
}
