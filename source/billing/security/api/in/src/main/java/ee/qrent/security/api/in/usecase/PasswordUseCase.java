package ee.qrent.security.api.in.usecase;

public interface PasswordUseCase {
  String encode(final String pwd);
}
