package ee.qrental.ui.controller.home;

import ee.qrental.car.api.in.query.GetCarQuery;
import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.ui.formatter.QDateFormatter;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.OperationResponseBody;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import static ee.qrental.ui.formatter.QDateFormatter.MODEL_ATTRIBUTE_DATE_FORMATTER;
import static ee.qrental.ui.controller.ControllerUtils.HOME_ROOT_PATH;

@Controller
@RequestMapping(HOME_ROOT_PATH)
@AllArgsConstructor
public class IndexPageController {
  private final QDateFormatter qDateFormatter;
  private final GetCarQuery carQuery;
  private final GetDriverQuery driverQuery;
  private final List<InfoContributor> infoContributors;

  @GetMapping
  public String getFreeCarView(final Model model) {
    model.addAttribute("cars", carQuery.getAvailableCars());
    model.addAttribute("drivers", driverQuery.getDriversWithZeroMatchCountForLatestCalculation());
    model.addAttribute(MODEL_ATTRIBUTE_DATE_FORMATTER, qDateFormatter);

    final var builder = new Info.Builder();
    for (InfoContributor contributor : this.infoContributors) {
      contributor.contribute(builder);
    }
    final var details = builder.build().getDetails();

    final var gitInfo = (Map<String, Object>) details.get("git");
    model.addAttribute("gitBranch", gitInfo.get("branch"));
    final var gitCommitInfo = (Map<String, Object>) gitInfo.get("commit");

    model.addAttribute("gitCommit", gitCommitInfo.get("id"));

    return "index";
  }
}
