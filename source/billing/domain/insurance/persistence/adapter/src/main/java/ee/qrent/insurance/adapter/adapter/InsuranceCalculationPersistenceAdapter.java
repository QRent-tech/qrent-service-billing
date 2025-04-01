package ee.qrent.insurance.adapter.adapter;

import ee.qrent.insurance.adapter.mapper.InsuranceCalculationAdapterMapper;
import ee.qrent.insurance.adapter.mapper.InsuranceCaseBalanceAdapterMapper;
import ee.qrent.insurance.adapter.repository.InsuranceCalculationRepository;
import ee.qrent.insurance.adapter.repository.InsuranceCaseBalanceRepository;
import ee.qrent.insurance.adapter.repository.InsuranceCaseBalanceTransactionRepository;
import ee.qrent.insurance.api.out.InsuranceCalculationAddPort;
import ee.qrent.insurance.domain.InsuranceCalculation;
import ee.qrent.insurance.domain.InsuranceCaseBalance;
import ee.qrent.insurance.entity.jakarta.InsuranceCalculationJakartaEntity;
import ee.qrent.insurance.entity.jakarta.InsuranceCaseBalanceJakartaEntity;
import ee.qrent.insurance.entity.jakarta.InsuranceCaseBalanceTransactionJakartaEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuranceCalculationPersistenceAdapter implements InsuranceCalculationAddPort {

  private final InsuranceCalculationRepository calculationRepository;
  private final InsuranceCaseBalanceRepository balanceRepository;
  private final InsuranceCaseBalanceTransactionRepository balanceTransactionRepository;

  private final InsuranceCalculationAdapterMapper calculationMapper;
  private final InsuranceCaseBalanceAdapterMapper balanceMapper;

  @Override
  public InsuranceCalculation add(final InsuranceCalculation domain) {
    final var savedEntity = calculationRepository.save(calculationMapper.mapToEntity(domain));
    domain.getInsuranceCaseBalances().stream()
        .forEach(balance -> saveBalance(balance, savedEntity.getId()));

    return calculationMapper.mapToDomain(savedEntity);
  }

  private void saveBalance(final InsuranceCaseBalance balance, final Long calculationId) {
    final var balanceToSave = balanceMapper.mapToEntity(balance);
    balanceToSave.setInsuranceCalculation(
        InsuranceCalculationJakartaEntity.builder().id(calculationId).build());
    final var savedBalance = balanceRepository.save(balanceToSave);
    saveBalanceTransactions(balance, savedBalance);
  }

  private void saveBalanceTransactions(
      final InsuranceCaseBalance balance, final InsuranceCaseBalanceJakartaEntity savedBalance) {
    balance.getTransactionIds().stream()
        .forEach(
            transactionId ->
                balanceTransactionRepository.save(
                    InsuranceCaseBalanceTransactionJakartaEntity.builder()
                        .balance(savedBalance)
                        .transactionId(transactionId)
                        .build()));
  }
}
