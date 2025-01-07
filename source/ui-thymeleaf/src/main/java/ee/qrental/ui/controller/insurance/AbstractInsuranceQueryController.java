package ee.qrental.ui.controller.insurance;

import ee.qrental.ui.formatter.QDateFormatter;
import ee.qrental.ui.service.insurance.InsuranceCounterService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;

import static ee.qrental.ui.formatter.QDateFormatter.MODEL_ATTRIBUTE_DATE_FORMATTER;
import static ee.qrental.ui.controller.ControllerUtils.COUNTERS_ATTRIBUTE;

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
