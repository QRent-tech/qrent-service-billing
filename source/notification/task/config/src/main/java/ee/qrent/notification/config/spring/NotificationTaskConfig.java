package ee.qrent.notification.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class NotificationTaskConfig {

  @Bean
  public TaskScheduler getNotificationTaskScheduler() {
    final var threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    final var NOTIFICATION_EXECUTOR_POOL_SIZE = 10;
    threadPoolTaskScheduler.setPoolSize(NOTIFICATION_EXECUTOR_POOL_SIZE);
    final var NOTIFICATION_EXECUTOR_NAME = "Notification-thread-pool-scheduler";
    threadPoolTaskScheduler.setThreadNamePrefix(NOTIFICATION_EXECUTOR_NAME);

    return threadPoolTaskScheduler;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

    return new PropertySourcesPlaceholderConfigurer();
  }
}
