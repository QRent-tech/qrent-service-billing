package ee.qrental.car.core.service;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.car.api.in.request.CarLinkAddRequest;
import ee.qrental.car.api.in.request.CarLinkDeleteRequest;
import ee.qrental.car.api.in.request.CarLinkCloseRequest;
import ee.qrental.car.api.in.request.CarLinkUpdateRequest;
import ee.qrental.car.api.in.usecase.CarLinkAddUseCase;
import ee.qrental.car.api.in.usecase.CarLinkDeleteUseCase;
import ee.qrental.car.api.in.usecase.CarLinkCloseUseCase;
import ee.qrental.car.api.in.usecase.CarLinkUpdateUseCase;
import ee.qrental.car.api.out.CarLinkAddPort;
import ee.qrental.car.api.out.CarLinkDeletePort;
import ee.qrental.car.api.out.CarLinkLoadPort;
import ee.qrental.car.api.out.CarLinkUpdatePort;
import ee.qrental.car.core.mapper.CarLinkAddRequestMapper;
import ee.qrental.car.core.mapper.CarLinkUpdateRequestMapper;
import ee.qrental.car.core.validator.CarLinkAddBusinessRuleValidator;
import ee.qrental.car.core.validator.CarLinkUpdateBusinessRuleValidator;
import ee.qrental.car.domain.CarLink;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CarLinkUseCaseService
    implements CarLinkAddUseCase, CarLinkUpdateUseCase, CarLinkDeleteUseCase, CarLinkCloseUseCase {

  private final CarLinkAddPort addPort;
  private final CarLinkUpdatePort updatePort;
  private final CarLinkDeletePort deletePort;
  private final CarLinkLoadPort loadPort;
  private final CarLinkAddRequestMapper addRequestMapper;
  private final CarLinkUpdateRequestMapper updateRequestMapper;
  private final CarLinkAddBusinessRuleValidator addValidator;
  private final CarLinkUpdateBusinessRuleValidator updateValidator;
  private final QDateTime qDateTime;

  @Override
  public Long add(final CarLinkAddRequest request) {
    final var violationsCollector = addValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return null;
    }

    return addPort.add(addRequestMapper.toDomain(request)).getId();
  }

  @Override
  public void update(final CarLinkUpdateRequest request) {
    final var violationsCollector = updateValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var linkToClose = loadPort.loadById(request.getId());
    linkToClose.setDateEnd(getDateStart());
    updatePort.update(linkToClose);

    final var linkToCreate =
        CarLink.builder()
            .carId(request.getCarId())
            .dateStart(getDateEnd())
            .driverId(request.getDriverId())
            .comment(request.getComment())
            .build();

    addPort.add(linkToCreate);
  }

  @Override
  public void delete(final CarLinkDeleteRequest request) {
    deletePort.delete(request.getId());
  }

  @Override
  public void close(final CarLinkCloseRequest request) {
    final var linkToClose = loadPort.loadById(request.getId());
    linkToClose.setDateEnd(getDateStart());
    updatePort.update(linkToClose);
  }

  private LocalDate getDateStart() {
    final var today = qDateTime.getToday();

    return today.minusDays(1L);
  }

  private LocalDate getDateEnd() {
    return qDateTime.getToday();
  }
}
