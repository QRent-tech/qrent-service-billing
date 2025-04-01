package ee.qrent.driver.repository.impl;

import ee.qrent.driver.adapter.repository.FriendshipRepository;
import ee.qrent.driver.entity.jakarta.FriendshipJakartaEntity;
import ee.qrent.driver.repository.spring.FriendshipSpringDataRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FriendshipRepositoryImpl implements FriendshipRepository {

  private final FriendshipSpringDataRepository springDataRepository;

  @Override
  public FriendshipJakartaEntity save(final FriendshipJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public FriendshipJakartaEntity findById(Long id) {
    return springDataRepository.findById(id).orElse(null);
  }

  @Override
  public List<FriendshipJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public FriendshipJakartaEntity findByFriendId(final Long friendId) {
    return springDataRepository.findByFriendId(friendId);
  }

  @Override
  public List<FriendshipJakartaEntity> findByDriverId(final Long driverId) {
    return springDataRepository.findByDriverId(driverId);
  }

  @Override
  public void deleteById(Long id) {
    springDataRepository.deleteById(id);
  }
}
