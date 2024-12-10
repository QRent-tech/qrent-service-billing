package ee.qrental.contract.repository.spring;

import ee.qrental.contract.entity.jakarta.AbsenceJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbsenceSpringDataRepository extends JpaRepository<AbsenceJakartaEntity, Long> {

  @Query(
      value =
          "SELECT ab.* "
              + "FROM absence ab "
              + "WHERE ab.driver_id = :driverId "
              + "AND ab.q_week_id IN (SELECT qw.id FROM q_week qw "
              + "WHERE (qw.year = (SELECT year FROM q_week WHERE id = :startQWeekId) AND "
              + "qw.number > (SELECT number FROM q_week WHERE id = :startQWeekId)) "
              + "OR qw.year > (SELECT year FROM q_week WHERE id = :startQWeekId));",
      nativeQuery = true)
  List<AbsenceJakartaEntity> findByDriverIdAndStartQWeekId(
      @Param("driverId") final Long driverId, @Param("startQWeekId") final Long startQWeekId);
}
