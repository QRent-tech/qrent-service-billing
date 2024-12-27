package ee.qrental.driver.core.service;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.common.core.validation.AddRequestValidator;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import ee.qrental.common.core.validation.UpdateRequestValidator;
import ee.qrental.driver.api.in.request.CallSignLinkAddRequest;
import ee.qrental.driver.api.in.request.CallSignLinkCloseRequest;
import ee.qrental.driver.api.in.request.CallSignLinkDeleteRequest;
import ee.qrental.driver.api.in.request.CallSignLinkUpdateRequest;
import ee.qrental.driver.api.in.usecase.CallSignLinkAddUseCase;
import ee.qrental.driver.api.in.usecase.CallSignLinkCloseUseCase;
import ee.qrental.driver.api.in.usecase.CallSignLinkDeleteUseCase;
import ee.qrental.driver.api.in.usecase.CallSignLinkUpdateUseCase;
import ee.qrental.driver.api.out.CallSignLinkAddPort;
import ee.qrental.driver.api.out.CallSignLinkDeletePort;
import ee.qrental.driver.api.out.CallSignLinkLoadPort;
import ee.qrental.driver.api.out.CallSignLinkUpdatePort;
import ee.qrental.driver.core.mapper.CallSignLinkAddRequestMapper;
import ee.qrental.driver.domain.CallSign;
import ee.qrental.driver.domain.CallSignLink;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CallSignLinkUseCaseService
    implements CallSignLinkAddUseCase,
        CallSignLinkUpdateUseCase,
        CallSignLinkCloseUseCase,
        CallSignLinkDeleteUseCase {

  private final CallSignLinkAddPort addPort;
  private final CallSignLinkUpdatePort updatePort;
  private final CallSignLinkDeletePort deletePort;
  private final CallSignLinkLoadPort loadPort;
  private final CallSignLinkAddRequestMapper addRequestMapper;
  private final AddRequestValidator<CallSignLinkAddRequest> addRequestValidator;
  private final UpdateRequestValidator<CallSignLinkUpdateRequest> updateRequestValidator;
  private final DeleteRequestValidator<CallSignLinkDeleteRequest> deleteRequestValidator;
  private final QDateTime qDateTime;

  @Override
  public Long add(final CallSignLinkAddRequest request) {
    final var violationsCollector = addRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());
      return null;
    }

    return addPort.add(addRequestMapper.toDomain(request)).getId();
  }

  @Override
  public void update(final CallSignLinkUpdateRequest request) {
    final var violationsCollector = updateRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var linkToClose = loadPort.loadById(request.getId());
    final var today = qDateTime.getToday();

    linkToClose.setDateEnd(today.minusDays(1L));
    updatePort.update(linkToClose);

    final var linkToCreate =
        CallSignLink.builder()
            .callSign(CallSign.builder().id(request.getCallSignId()).build())
            .dateStart(today)
            .driverId(request.getDriverId())
            .build();

    addPort.add(linkToCreate);
  }

  @Override
  public void delete(final CallSignLinkDeleteRequest request) {
    final var violationsCollector = deleteRequestValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    deletePort.delete(request.getId());
  }

  @Override
  public void close(final CallSignLinkCloseRequest request) {
    final var linkToClose = loadPort.loadById(request.getId());
    final var today = qDateTime.getToday();
    linkToClose.setDateEnd(today.minusDays(1L));
    updatePort.update(linkToClose);
  }
}
