package ee.qrent.notification.email.api.out;

import ee.qrent.common.out.port.LoadPort;
import ee.qrent.notification.email.domain.EmailNotification;

import java.util.List;

public interface EmailNotificationLoadPort extends LoadPort<EmailNotification> {
  List<EmailNotification> loadAllByIds(final List<Long> ids);

  List<EmailNotification> loadAll();
}
