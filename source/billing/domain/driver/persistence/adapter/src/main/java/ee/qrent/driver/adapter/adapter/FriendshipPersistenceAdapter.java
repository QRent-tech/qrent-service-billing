package ee.qrent.driver.adapter.adapter;

import ee.qrent.driver.adapter.mapper.FriendshipAdapterMapper;
import ee.qrent.driver.adapter.repository.FriendshipRepository;
import ee.qrent.driver.api.out.FriendshipAddPort;
import ee.qrent.driver.api.out.FriendshipDeletePort;
import ee.qrent.driver.api.out.FriendshipUpdatePort;
import ee.qrent.driver.domain.Friendship;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FriendshipPersistenceAdapter
    implements FriendshipAddPort, FriendshipUpdatePort, FriendshipDeletePort {
  private final FriendshipRepository friendshipRepository;
  private final FriendshipAdapterMapper friendshipAdapterMapper;

  @Override
  public void delete(final Long id) {}

  @Override
  public Friendship update(final Friendship domain) {
    return null;
  }

  @Override
  public Friendship add(final Friendship domain) {
    final var entityToSave = friendshipAdapterMapper.mapToEntity(domain);
    final var entitySaved = friendshipRepository.save(entityToSave);

    return friendshipAdapterMapper.mapToDomain(entitySaved);
  }
}
