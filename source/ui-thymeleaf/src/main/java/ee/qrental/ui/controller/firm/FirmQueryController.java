package ee.qrental.ui.controller.firm;

import ee.qrental.firm.api.in.query.GetFirmQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static ee.qrental.ui.controller.ControllerUtils.FIRM_ROOT_PATH;


@Controller
@RequestMapping(FIRM_ROOT_PATH)
@AllArgsConstructor
public class FirmQueryController {

  private final GetFirmQuery firmQuery;

  @GetMapping
  public String getFirmView(final Model model) {
    model.addAttribute("firms", firmQuery.getAll());

    return "firms";
  }
}
