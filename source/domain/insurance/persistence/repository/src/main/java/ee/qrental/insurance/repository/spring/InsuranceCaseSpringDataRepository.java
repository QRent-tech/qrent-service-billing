package ee.qrental.insurance.repository.spring;

import ee.qrental.insurance.entity.jakarta.InsuranceCaseJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceCaseSpringDataRepository
    extends JpaRepository<InsuranceCaseJakartaEntity, Long> {
  @Query(
      value =
          "select ic.* from insurance_case ic "
              + "LEFT JOIN q_week qw on qw.id = ic.start_q_week_id "
              + "where "
              + "ic.active = true "
              + "and ic.driver_id = :driverId "
              + "and qw.year <= (select year from q_week where id = :qWeekId) "
              + "and qw.number <= (select number from q_week where id = :qWeekId);",
      nativeQuery = true)
  List<InsuranceCaseJakartaEntity> findAllByActiveIsTrueAndDriverIdAndQWeekId(
      final @Param("driverId") Long driverId, final @Param("qWeekId") Long qWeekId);

  @Query(
      value =
          "select ic.* from insurance_case ic "
              + "LEFT JOIN q_week qw on qw.id = ic.start_q_week_id "
              + "where "
              + "ic.active = true "
              + "and qw.year <= (select year from q_week where id = :qWeekId) "
              + "and qw.number <= (select number from q_week where id = :qWeekId);",
      nativeQuery = true)
  List<InsuranceCaseJakartaEntity> findAllByActiveIsTrueAndQweekId(
      final @Param("qWeekId") Long qWeekId);

  @Query(value = "SELECT * FROM insurance_case ic where ic.active is true", nativeQuery = true)
  List<InsuranceCaseJakartaEntity> findActive();

  @Query(value = "SELECT * FROM insurance_case ic where ic.active is false", nativeQuery = true)
  List<InsuranceCaseJakartaEntity> findClosed();

  @Query(
      value = "SELECT count(*) FROM insurance_case ic where ic.active is true",
      nativeQuery = true)
  Long findCountActive();

  @Query(
      value = "SELECT count(*) FROM insurance_case ic where ic.active is false",
      nativeQuery = true)
  Long findCountClosed();

  @Query(
      value =
          "SELECT * FROM insurance_case ic where ic.active is true and ic.driver_id = :driverId",
      nativeQuery = true)
  List<InsuranceCaseJakartaEntity> findActiveByDriverId(final @Param("driverId") Long driverId);
}
