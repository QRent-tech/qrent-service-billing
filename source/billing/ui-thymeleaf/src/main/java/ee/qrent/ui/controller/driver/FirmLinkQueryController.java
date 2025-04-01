package ee.qrent.ui.controller.driver;

import static ee.qrent.ui.controller.ControllerUtils.FIRM_LINK_ROOT_PATH;

import ee.qrent.driver.api.in.query.GetFirmLinkQuery;
import ee.qrent.ui.formatter.QDateFormatter;
import ee.qrent.ui.service.driver.DriverCounterService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(FIRM_LINK_ROOT_PATH)
public class FirmLinkQueryController extends AbstractDriverQueryController {

  private final GetFirmLinkQuery firmLinkQuery;

  public FirmLinkQueryController(
      final DriverCounterService driverCounterService,
      final QDateFormatter qDateFormatter,
      final GetFirmLinkQuery firmLinkQuery) {
    super(driverCounterService, qDateFormatter);
    this.firmLinkQuery = firmLinkQuery;
  }

  @GetMapping
  public String getFirmLinkView(final Model model) {
    model.addAttribute("firmLinks", firmLinkQuery.getAll());
    addDriverCounts(model);
    addDateFormatter(model);

    return "firmLinks";
  }
}
