package ee.qrental.ui.controller.driver;

import static ee.qrental.ui.controller.util.ControllerUtils.DRIVER_ROOT_PATH;

import ee.qrental.driver.api.in.query.GetDriverQuery;
import ee.qrental.ui.controller.formatter.QDateFormatter;
import ee.qrental.ui.service.driver.DriverCounterService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(DRIVER_ROOT_PATH)
public class DriverQueryController extends AbstractDriverQueryController {

  private final GetDriverQuery driverQuery;

  public DriverQueryController(
      final DriverCounterService driverCounterService,
      final QDateFormatter qDateFormatter,
      final GetDriverQuery driverQuery) {
    super(driverCounterService, qDateFormatter);
    this.driverQuery = driverQuery;
  }

  @GetMapping
  public String getDriverView(final Model model) {
    model.addAttribute("drivers", driverQuery.getAll());
    addDriverCounts(model);
    addDateFormatter(model);

    return "drivers";
  }
}
