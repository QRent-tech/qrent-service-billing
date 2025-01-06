package ee.qrental.ui.controller.driver;

import static ee.qrental.ui.controller.ControllerUtils.*;

import ee.qrental.contract.api.in.query.GetAbsenceQuery;

import ee.qrental.ui.formatter.QDateFormatter;
import ee.qrental.ui.service.driver.DriverCounterService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(ABSENCE_ROOT_PATH)
public class AbsenceQueryController extends AbstractDriverQueryController {

  private final GetAbsenceQuery absenceQuery;

  public AbsenceQueryController(
      final DriverCounterService driverCounterService,
      final QDateFormatter qDateFormatter,
      final GetAbsenceQuery absenceQuery) {
    super(driverCounterService, qDateFormatter);
    this.absenceQuery = absenceQuery;
  }

  @GetMapping
  public String getAbsenceView(final Model model) {
    model.addAttribute("absences", absenceQuery.getAll());
    addDriverCounts(model);
    addDateFormatter(model);

    return "absences";
  }
}
