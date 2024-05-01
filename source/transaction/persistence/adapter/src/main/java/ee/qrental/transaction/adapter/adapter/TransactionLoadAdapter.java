package ee.qrental.transaction.adapter.adapter;

import static java.util.stream.Collectors.toList;

import ee.qrental.transaction.adapter.mapper.TransactionAdapterMapper;
import ee.qrental.transaction.adapter.repository.TransactionRepository;
import ee.qrental.transaction.api.out.TransactionLoadPort;
import ee.qrental.transaction.domain.Transaction;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionLoadAdapter implements TransactionLoadPort {

  private final TransactionRepository repository;

  private final TransactionAdapterMapper mapper;

  @Override
  public Transaction loadById(final Long id) {
    return mapper.mapToDomain(repository.getReferenceById(id));
  }

  @Override
  public List<Transaction> loadAll() {
    return repository.findAll().stream().map(mapper::mapToDomain).collect(toList());
  }

  @Override
  public List<Transaction> loadAllByDriverId(final Long driverId) {
    return repository.findByDriverId(driverId).stream().map(mapper::mapToDomain).collect(toList());
  }

  @Override
  public List<Transaction> loadAllByIds(final List<Long> ids) {
    return repository.findByIds(ids).stream().map(mapper::mapToDomain).collect(toList());
  }

  @Override
  public List<Transaction> loadAllBetweenDays(final LocalDate dateStart, final LocalDate dateEnd) {
    return repository.findAllByDateBetween(dateStart, dateEnd).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public List<Transaction> loadAllByDriverIdAndBetweenDays(
      final Long driverId, final LocalDate dateStart, final LocalDate dateEnd) {
    return repository.findAllByDateBetweenAndDriverId(dateStart, dateEnd, driverId).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public List<Transaction> loadAllByDriverIdAndKindIdAndBetweenDays(
      final Long driverId,
      final List<Long> kindIds,
      final LocalDate dateStart,
      final LocalDate dateEnd) {
    return repository
        .findAllByDriverIdAndKindIdAndBetweenDays(driverId, kindIds, dateStart, dateEnd)
        .stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public List<Transaction> loadAllNonFeeByDriverIdAndBetweenDays(
      Long driverId, LocalDate dateStart, LocalDate dateEnd) {
    return repository.findAllNonFeeByDateBetweenAndDriverId(dateStart, dateEnd, driverId).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public List<Transaction> loadAllFeeByDriverIdAndBetweenDays(
      Long driverId, LocalDate dateStart, LocalDate dateEnd) {
    return repository.findAllFeeByDateBetweenAndDriverId(dateStart, dateEnd, driverId).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public List<Transaction> loadAllByRentCalculationId(final Long rentCalculationId) {
    return repository.findAllByRentCalculationId(rentCalculationId).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }

  @Override
  public List<Transaction> loadAllByBonusCalculationId(final Long bonusCalculationId) {
    return repository.findAllByBonusCalculationId(bonusCalculationId).stream()
        .map(mapper::mapToDomain)
        .collect(toList());
  }
}
