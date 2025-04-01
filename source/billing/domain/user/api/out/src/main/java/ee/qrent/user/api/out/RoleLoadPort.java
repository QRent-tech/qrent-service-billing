package ee.qrent.user.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrent.driver.domain.Role;

public interface RoleLoadPort extends LoadPort<Role> {
    Role loadByName(final String name);
}
