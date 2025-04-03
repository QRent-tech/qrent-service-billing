package ee.qrent.common.core.task;

import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.usecase.RunTaskUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RunTaskUseCaseImpl implements RunTaskUseCase {

  private final QDateTime qDateTime;

  @Override
  public void run(final Runnable task, final String taskName) {
    System.out.println(taskName + "was started at: " + qDateTime.getNow());
    try {
      task.run();
    } catch (Exception e) {
      System.out.println(taskName + " failed by next reason: " + e.getMessage());
      e.printStackTrace();
    } finally {
      System.out.println(taskName + " completed at: " + qDateTime.getNow());
    }
  }
}
