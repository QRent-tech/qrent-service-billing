package ee.qrental.user.repository.impl;

import ee.qrental.user.adapter.repository.UserAccountRepository;
import ee.qrental.user.entity.jakarta.UserAccountJakartaEntity;
import ee.qrental.user.repository.spring.UserAccountSpringDataRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAccountRepositoryImpl implements UserAccountRepository {

  private final UserAccountSpringDataRepository springDataRepository;

  @Override
  public List<UserAccountJakartaEntity> findAll() {
    return springDataRepository.findAll();
  }

  @Override
  public UserAccountJakartaEntity save(final UserAccountJakartaEntity entity) {
    return springDataRepository.save(entity);
  }

  @Override
  public UserAccountJakartaEntity getReferenceById(final Long id) {
    return springDataRepository.getReferenceById(id);
  }
  
  @Override
  public void deleteById(final Long id) {
    springDataRepository.deleteById(id);
  }

  @Override
  public UserAccountJakartaEntity findByUsername(final String username) {
    return springDataRepository.findByUsername(username);
  }

  @Override
  public UserAccountJakartaEntity findByEmail(final String email) {
    return springDataRepository.findByEmail(email);
  }
}
