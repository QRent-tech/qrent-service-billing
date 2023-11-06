package ee.qrental.transaction.repository.spring.rent;

import ee.qrental.transaction.entity.jakarta.rent.RentCalculationJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentCalculationSpringDataRepository
    extends JpaRepository<RentCalculationJakartaEntity, Long> {

  @Query(
      value =
          "select qw.id "
              + "from rent_calculation rc "
              + "         LEFT JOIN q_week qw on rc.q_week_id = q_week_id "
              + "order by qw.year, qw.number desc "
              + "limit 1;",
      nativeQuery = true)
  Long getLastCalculationQWeekId();

}
