package ee.qrental.contract.core.validator;

import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.contract.api.out.ContractLoadPort;
import ee.qrental.transaction.api.in.request.campaign.CampaignDeleteRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContractDeleteRequestValidator implements DeleteRequestValidator<CampaignDeleteRequest> {

  private final ContractLoadPort loadPort;

  @Override
  public ViolationsCollector validate(final CampaignDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request.getId(), violationsCollector);

    return violationsCollector;
  }

  private void checkExistence(final Long id, final ViolationsCollector violationCollector) {
    if (loadPort.loadById(id) == null) {
      violationCollector.collect("Update of Contract failed. No Record with id = " + id);
    }
  }
}
