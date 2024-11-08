package ee.qrental.driver.core.service;

import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.request.ContractUpdateRequest;
import ee.qrental.contract.api.in.usecase.ContractUpdateUseCase;
import ee.qrental.driver.api.in.request.DriverAddRequest;
import ee.qrental.driver.api.in.request.DriverDeleteRequest;
import ee.qrental.driver.api.in.request.DriverUpdateRequest;
import ee.qrental.driver.api.in.usecase.DriverAddUseCase;
import ee.qrental.driver.api.in.usecase.DriverDeleteUseCase;
import ee.qrental.driver.api.in.usecase.DriverUpdateUseCase;
import ee.qrental.driver.api.out.*;
import ee.qrental.driver.core.mapper.DriverAddRequestMapper;
import ee.qrental.driver.core.mapper.DriverUpdateRequestMapper;
import ee.qrental.driver.core.validator.DriverUpdateBusinessRuleValidator;
import ee.qrental.driver.domain.Driver;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DriverUseCaseService
    implements DriverAddUseCase, DriverUpdateUseCase, DriverDeleteUseCase {

  private final GetContractQuery contractQuery;
  private final ContractUpdateUseCase contractUpdateUseCase;
  private final DriverAddPort driverAddPort;
  private final DriverUpdatePort updatePort;
  private final DriverDeletePort deletePort;
  private final DriverLoadPort loadPort;
  private final DriverAddRequestMapper addRequestMapper;
  private final DriverUpdateRequestMapper updateRequestMapper;
  private final DriverUpdateBusinessRuleValidator updateBusinessRuleValidator;

  @Transactional
  @Override
  public Long add(final DriverAddRequest request) {
    final var driverId = driverAddPort.add(addRequestMapper.toDomain(request)).getId();

    return driverId;
  }

  @Transactional
  @Override
  public void update(final DriverUpdateRequest request) {
    final var driverId = request.getId();
    checkExistence(driverId);

    final var violationsCollector = updateBusinessRuleValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var driverFromDb = loadPort.loadById(driverId);
    final var contractUpdateRequired = isContractUpdateRequired(request, driverFromDb);
    if (contractUpdateRequired) {
      updateContract(driverId, request.getQFirmId());
    }
    updatePort.update(updateRequestMapper.toDomain(request));
  }

  private void updateContract(final Long driverId, final Long qFirmId) {
    final var currentActiveContract = contractQuery.getCurrentActiveByDriverId(driverId);
    if (currentActiveContract == null) {
      System.out.println("Driver has no Active Contract. Contract update not required");

      return;
    }
    final var contractUpdateRequest = new ContractUpdateRequest();
    contractUpdateRequest.setId(currentActiveContract.getId());
    contractUpdateRequest.setQFirmId(qFirmId);

    contractUpdateUseCase.update(contractUpdateRequest);
  }

  private boolean isContractUpdateRequired(
      final DriverUpdateRequest request, final Driver driverFromDb) {
    return driverFromDb.getQFirmId() != request.getQFirmId()
        || request.getCompanyName() != driverFromDb.getCompanyName()
        || request.getCompanyCeoFirstName() != driverFromDb.getCompanyCeoFirstName()
        || request.getCompanyCeoLastName() != driverFromDb.getCompanyCeoLastName()
        || request.getCompanyCeoTaxNumber() != driverFromDb.getCompanyCeoTaxNumber()
        || request.getRegNumber() != driverFromDb.getCompanyRegistrationNumber()
        || request.getCompanyAddress() != driverFromDb.getCompanyAddress()
        || request.getFirstName() != driverFromDb.getFirstName()
        || request.getTaxNumber() != driverFromDb.getTaxNumber()
        || request.getDriverLicenseNumber() != driverFromDb.getDriverLicenseNumber()
        || request.getEmail() != driverFromDb.getEmail()
        || request.getPhone() != driverFromDb.getPhone();
  }

  @Transactional
  @Override
  public void delete(final DriverDeleteRequest request) {
    deletePort.delete(request.getId());
  }

  private void checkExistence(final Long id) {
    if (loadPort.loadById(id) == null) {
      throw new RuntimeException("Update of Driver failed. No Record with id = " + id);
    }
  }
}
