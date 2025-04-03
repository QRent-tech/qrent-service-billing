package ee.qrent.billing.task.core;

import ee.qrent.billing.constant.api.in.request.QWeekAddRequest;
import ee.qrent.billing.constant.api.in.usecase.QWeekAddUseCase;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.usecase.RunTaskUseCase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor
public class QWeekCreationTask {

  private final QWeekAddUseCase qWeekAddUseCase;
  private final RunTaskUseCase runTaskUseCase;
  private final QDateTime qDateTime;

  /**
   * +---------------- minute (0 - 59)
   * | +------------- hour (0 - 23)
   * | | +---------- day of month (1 - 31)
   * | | | +------- month (1 - 12)
   * | | | | +---- day of week (0 - 6) (Sunday=0 or 7)
   * | | | | | * * * * * command to be executed
   */

  // Scheduled task is executed at 00:02:00 AM on the MONDAY every week
  @Scheduled(cron = "0 2 * * * MON")
  public void scheduleTask() {
    final var taskName = InsuranceCaseCalculationTask.class.getSimpleName();
    runTaskUseCase.run(
        () -> {
          final var nowDate = qDateTime.getToday();
          final var addRequest = new QWeekAddRequest();
          addRequest.setWeekDate(nowDate);
          qWeekAddUseCase.add(addRequest);
        },
        taskName);
  }
}
