package ee.qrental.user.repository.spring;

import ee.qrental.user.entity.jakarta.UserAccountJakartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAccountSpringDataRepository
        extends JpaRepository<UserAccountJakartaEntity, Long> {
    UserAccountJakartaEntity findByEmail(final String email);
    UserAccountJakartaEntity findByUsername(final String username);
    @Query(
            value ="select ua.* from user_account ua " +
                    "LEFT JOIN role_x_user_account rxua " +
                    "ON ua.id = rxua.user_account_id " +
                    "where rxua.role_id = :roleId",
            nativeQuery = true)
    List<UserAccountJakartaEntity> findAllByRoleId( @Param("roleId") final Long roleId);
}
