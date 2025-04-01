package ee.qrent.ui.controller.insurance;

import ee.qrent.ui.formatter.QDateFormatter;
import ee.qrent.ui.service.insurance.InsuranceCounterService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;

import static ee.qrent.ui.formatter.QDateFormatter.MODEL_ATTRIBUTE_DATE_FORMATTER;
import static ee.qrent.ui.controller.ControllerUtils.COUNTERS_ATTRIBUTE;

@AllArgsConstructor
public class AbstractInsuranceQueryController {
  private final QDateFormatter qDateFormatter;
  private final InsuranceCounterService insuranceCounterService;

  protected void addInsuranceCounts(final Model model) {
    model.addAttribute(COUNTERS_ATTRIBUTE, insuranceCounterService.getInsuranceCounts());
  }

  protected void addDateFormatter(final Model model) {
    model.addAttribute(MODEL_ATTRIBUTE_DATE_FORMATTER, qDateFormatter);
  }
}
