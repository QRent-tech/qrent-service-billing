package ee.qrental.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GitInfoInterceptor implements HandlerInterceptor {

  private final List<InfoContributor> infoContributors;

  @Override
  public void postHandle(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler,
      ModelAndView modelAndView)
      throws Exception {
    final var builder = new Info.Builder();
    for (InfoContributor contributor : this.infoContributors) {
      contributor.contribute(builder);
    }
    final var details = builder.build().getDetails();

    final var gitInfo = (Map<String, Object>) details.get("git");
    modelAndView.getModel().put("gitBranch", gitInfo.get("branch"));
    final var gitCommitInfo = (Map<String, Object>) gitInfo.get("commit");

    modelAndView.getModel().put("gitCommit", gitCommitInfo.get("id"));

    System.out.println("---method executed---");
  }
}
