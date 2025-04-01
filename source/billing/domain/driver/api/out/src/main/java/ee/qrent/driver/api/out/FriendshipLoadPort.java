package ee.qrent.driver.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrent.driver.domain.Friendship;

import java.util.List;

public interface FriendshipLoadPort extends LoadPort<Friendship> {
  List<Friendship> loadByDriverId(final Long driverId);
}
