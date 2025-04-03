package ee.qrent.notification.email.persistence.mapper;

import ee.qrent.notification.email.domain.EmailNotification;
import ee.qrent.notification.email.persistence.entity.jakarta.EmailNotificationJakartaEntity;

public class EmailNotificationAdapterMapper {

  public EmailNotification mapToDomain(final EmailNotificationJakartaEntity entity) {
    return EmailNotification.builder().build();
  }

  public EmailNotificationJakartaEntity mapToEntity(final EmailNotification domain) {
    return EmailNotificationJakartaEntity.builder().build();
  }
}
