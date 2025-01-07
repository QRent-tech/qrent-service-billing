package ee.qrental.ui.controller.driver;

import ee.qrental.ui.formatter.QDateFormatter;
import ee.qrental.ui.service.driver.DriverCounterService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;

import static ee.qrental.ui.formatter.QDateFormatter.MODEL_ATTRIBUTE_DATE_FORMATTER;
import static ee.qrental.ui.controller.ControllerUtils.COUNTERS_ATTRIBUTE;

@AllArgsConstructor
public abstract class AbstractDriverQueryController {
  private final DriverCounterService driverCounterService;
  private final QDateFormatter qDateFormatter;

  protected void addDriverCounts(final Model model) {
    model.addAttribute(COUNTERS_ATTRIBUTE, driverCounterService.getDriverCounts());
  }

  protected void addDateFormatter(final Model model) {
    model.addAttribute(MODEL_ATTRIBUTE_DATE_FORMATTER, qDateFormatter);
  }
}
