package ee.qrental.driver.core.validator;


import ee.qrent.common.in.time.QDateTime;
import ee.qrent.common.in.validation.DeleteRequestValidator;
import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrent.billing.constant.api.in.query.GetQWeekQuery;
import ee.qrental.driver.api.in.request.DriverDeleteRequest;
import ee.qrental.driver.api.out.DriverLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DriverDeleteRequestValidator implements DeleteRequestValidator<DriverDeleteRequest> {

  private final DriverLoadPort loadPort;
  private final GetQWeekQuery qWeekQuery;
  private final QDateTime qDateTime;

  @Override
  public ViolationsCollector validate(final DriverDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();

    return violationsCollector;
  }
}
