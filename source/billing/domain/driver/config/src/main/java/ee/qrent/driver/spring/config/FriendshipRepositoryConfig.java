package ee.qrent.driver.spring.config;

import ee.qrent.driver.adapter.repository.FriendshipRepository;
import ee.qrent.driver.repository.impl.FriendshipRepositoryImpl;
import ee.qrent.driver.repository.spring.FriendshipSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FriendshipRepositoryConfig {

  @Bean
  FriendshipRepository getFriendshipRepository(
      final FriendshipSpringDataRepository springDataRepository) {
    return new FriendshipRepositoryImpl(springDataRepository);
  }
}
