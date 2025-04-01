package ee.qrent.driver.core.mapper;

import ee.qrent.driver.api.in.request.DriverAddRequest;
import ee.qrent.driver.api.in.request.DriverUpdateRequest;
import ee.qrent.driver.domain.Friendship;

import java.time.LocalDate;

public class FriendshipDomainMapper {

  public Friendship toDomain(final DriverAddRequest request, final Long driverId) {
    return Friendship.builder()
        .id(null)
        .driverId(request.getRecommendedByDriverId())
        .friendId(driverId)
        .startDate(LocalDate.now())
        .build();
  }

  public Friendship toDomain(final DriverUpdateRequest request) {
    return Friendship.builder()
        .id(null)
        .driverId(request.getRecommendedByDriverId())
        .friendId(request.getId())
        .startDate(LocalDate.now())
        .build();
  }
}
