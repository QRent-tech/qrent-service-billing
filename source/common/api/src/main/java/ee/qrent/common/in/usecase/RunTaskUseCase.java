package ee.qrent.common.in.usecase;

public interface RunTaskUseCase {
  void run(final Runnable task, final String taskName);
}
