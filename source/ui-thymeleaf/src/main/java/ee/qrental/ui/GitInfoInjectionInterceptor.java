package ee.qrental.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoPropertiesInfoContributor;
import org.springframework.boot.info.GitProperties;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@AllArgsConstructor
public class GitInfoInjectionInterceptor implements HandlerInterceptor {

  private final InfoPropertiesInfoContributor<GitProperties> gitContributor;

  @Override
  public void postHandle(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler,
      ModelAndView modelAndView) {
    final var builder = new Info.Builder();
    gitContributor.contribute(builder);

    final var details = builder.build().getDetails();

    final var gitInfo = (Map<String, Object>) details.get("git");
    modelAndView.getModel().put("gitBranch", gitInfo.get("branch"));
    final var gitCommitInfo = (Map<String, Object>) gitInfo.get("commit");
    modelAndView.getModel().put("gitCommit", gitCommitInfo.get("id"));
  }
}
