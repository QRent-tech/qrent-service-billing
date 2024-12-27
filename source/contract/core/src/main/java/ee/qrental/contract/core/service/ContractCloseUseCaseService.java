package ee.qrental.contract.core.service;

import ee.qrent.common.in.time.QDateTime;
import ee.qrental.contract.api.in.request.ContractCloseRequest;
import ee.qrental.contract.api.in.response.ContractPreCloseResponse;
import ee.qrental.contract.api.in.response.InsuranceCaseForContractCloseResponse;
import ee.qrental.contract.api.in.usecase.ContractCloseUseCase;
import ee.qrental.contract.api.out.ContractLoadPort;
import ee.qrental.contract.api.out.ContractUpdatePort;
import ee.qrental.contract.core.validator.ContractCloseRequestValidator;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.insurance.api.in.query.GetInsuranceCaseQuery;
import ee.qrental.insurance.api.in.request.InsuranceCaseCloseRequest;
import ee.qrental.insurance.api.in.request.InsuranceCasePreCloseResponse;
import ee.qrental.insurance.api.in.response.InsuranceCaseResponse;
import ee.qrental.insurance.api.in.usecase.InsuranceCaseCloseUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
@AllArgsConstructor
public class ContractCloseUseCaseService implements ContractCloseUseCase {
  private final ContractLoadPort contractLoadPort;
  private final ContractUpdatePort contractUpdatePort;
  private final GetDriverQuery driverQuery;
  private final GetInsuranceCaseQuery insuranceCaseQuery;
  private final InsuranceCaseCloseUseCase insuranceCaseCloseUseCase;
  private final ContractCloseRequestValidator closeRuleValidator;
  private final QDateTime qDateTime;

  @Override
  public ContractPreCloseResponse getPreCloseResponse(final Long contractId) {
    final var contractToClose = contractLoadPort.loadById(contractId);
    final var driverId = contractToClose.getDriverId();
    final var activeInsuranceCasesIds =
        insuranceCaseQuery.getActiveByDriverId(driverId).stream()
            .map(InsuranceCaseResponse::getId)
            .toList();
    final var preCloseResponses =
        insuranceCaseCloseUseCase.getPreCloseResponses(activeInsuranceCasesIds).stream()
            .map(this::mapToInsuranceCaseForContractCloseResponse)
            .toList();

    return ContractPreCloseResponse.builder()
        .insuranceCasesToClose(preCloseResponses)
        .driverInfo(driverQuery.getObjectInfo(driverId))
        .contractDuration(contractToClose.getContractDuration().getLabel())
        .build();
  }

  private InsuranceCaseForContractCloseResponse mapToInsuranceCaseForContractCloseResponse(
      final InsuranceCasePreCloseResponse insuranceCasePreCloseResponse) {

    return InsuranceCaseForContractCloseResponse.builder()
        .insuranceCaseId(insuranceCasePreCloseResponse.getInsuranceCaseId())
        .originalAmount(insuranceCasePreCloseResponse.getOriginalAmount())
        .paidAmount(insuranceCasePreCloseResponse.getPaidAmount())
        .withQKasko(insuranceCasePreCloseResponse.getWithQKasko())
        .paymentAmount(insuranceCasePreCloseResponse.getPaymentAmount())
        .build();
  }

  @Override
  public void close(final ContractCloseRequest request) {
    final var violationsCollector = closeRuleValidator.validate(request);
    if (violationsCollector.hasViolations()) {
      request.setViolations(violationsCollector.getViolations());

      return;
    }
    final var contractId = request.getId();
    final var preCloseResponse = getPreCloseResponse(contractId);
    preCloseResponse.getInsuranceCasesToClose().stream()
        .forEach(
            insuranceCase ->
                insuranceCaseCloseUseCase.close(
                    getInsuranceCaseCloseRequest(insuranceCase.getInsuranceCaseId())));
    final var contractToClose = contractLoadPort.loadById(contractId);
    contractToClose.setDateEnd(qDateTime.getToday());
    contractUpdatePort.update(contractToClose);
  }

  private InsuranceCaseCloseRequest getInsuranceCaseCloseRequest(final Long insuranceCaseId) {
    final var insuranceCaseCloseRequest = new InsuranceCaseCloseRequest();
    insuranceCaseCloseRequest.setId(insuranceCaseId);

    return insuranceCaseCloseRequest;
  }
}
