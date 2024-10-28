package ee.qrental.ui.controller.driver;

import static ee.qrental.ui.controller.formatter.QDateFormatter.MODEL_ATTRIBUTE_DATE_FORMATTER;
import static ee.qrental.ui.controller.util.ControllerUtils.CONTRACT_ROOT_PATH;
import static ee.qrental.ui.controller.util.ControllerUtils.COUNTERS_ATTRIBUTE;

import ee.qrental.contract.api.in.query.GetContractQuery;
import ee.qrental.contract.api.in.request.ContractSendByEmailRequest;
import ee.qrental.contract.api.in.usecase.ContractPdfUseCase;
import ee.qrental.contract.api.in.usecase.ContractSendByEmailUseCase;
import ee.qrental.ui.controller.formatter.QDateFormatter;
import ee.qrental.ui.service.driver.DriverCounterService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CONTRACT_ROOT_PATH)
@AllArgsConstructor
public class ContractQueryController {

  private final GetContractQuery contractQuery;
  private final ContractSendByEmailUseCase contractSendByEmailUseCase;
  private final ContractPdfUseCase pdfUseCase;
  private final QDateFormatter qDateFormatter;
  private final DriverCounterService driverCounterService;

  @GetMapping(value = "/active")
  public String getActiveContractView(final Model model) {
    model.addAttribute(MODEL_ATTRIBUTE_DATE_FORMATTER, qDateFormatter);
    model.addAttribute("contractsActive", contractQuery.getActive());
    populateLinksCounts(model);

    return "contractsActive";
  }

  @GetMapping(value = "/closed")
  public String getClosedContractView(final Model model) {
    model.addAttribute(MODEL_ATTRIBUTE_DATE_FORMATTER, qDateFormatter);
    model.addAttribute("contractsClosed", contractQuery.getClosed());
    populateLinksCounts(model);

    return "contractsClosed";
  }

  @GetMapping("/pdf/{id}")
  @ResponseBody
  public ResponseEntity<InputStreamResource> getPdf(@PathVariable("id") long id) {

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(pdfUseCase.getPdfInputStreamById(id)));
  }

  @GetMapping(value = "/email/send-form/{id}")
  public String addForm(@PathVariable("id") long id, final Model model) {
    final var emailSendRequest = new ContractSendByEmailRequest();
    emailSendRequest.setId(id);
    model.addAttribute("emailSendRequest", emailSendRequest);

    return "forms/emailSendContract";
  }

  @PostMapping("/email/send")
  public String sendByEmail(final ContractSendByEmailRequest emailSendRequest) {
    contractSendByEmailUseCase.sendByEmail(emailSendRequest);

    return "redirect:" + CONTRACT_ROOT_PATH;
  }

  private void populateLinksCounts(final Model model) {
    model.addAttribute(COUNTERS_ATTRIBUTE, driverCounterService.getDriverCounts());
  }
}
