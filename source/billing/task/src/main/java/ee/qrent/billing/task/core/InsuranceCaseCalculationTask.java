package ee.qrent.billing.task.core;

import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrent.billing.insurance.api.in.request.InsuranceCalculationAddRequest;
import ee.qrent.billing.insurance.api.in.usecase.InsuranceCalculationAddUseCase;
import ee.qrent.common.in.usecase.RunTaskUseCase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor
public class InsuranceCaseCalculationTask {

  private final InsuranceCalculationAddUseCase addUseCase;
  private final GetQWeekQuery getQWeekQuery;
  private final RunTaskUseCase runTaskUseCase;

  // seconds minutes hours day-of-month month day-of-week
  //   0       0      8        *         *        ?
  // For example, 25 10 8 * * ?, means that the task is executed at 08:10:25 every day.
  // For example, 25 10 8 * * ?, means that the task is executed at 08:10:25 every day.

  @Scheduled(cron = "5 5 0 * * MON") // means that the task is executed at 00:05:05 every Monday.
  public void scheduleTask() {
    final var taskName = InsuranceCaseCalculationTask.class.getSimpleName();
    runTaskUseCase.run(
        () -> {
          final var addRequest = new InsuranceCalculationAddRequest();
          addRequest.setComment("Automatically triggered Insurance Balance Calculation");
          addRequest.setQWeekId(getQWeekQuery.getCurrentWeek().getId());
          addUseCase.add(addRequest);
        },
        taskName);
  }
}
