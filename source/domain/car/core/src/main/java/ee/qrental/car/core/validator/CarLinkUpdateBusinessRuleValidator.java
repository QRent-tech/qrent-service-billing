package ee.qrental.car.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.validation.ViolationsCollector;
import ee.qrental.car.api.in.request.CarLinkUpdateRequest;
import ee.qrental.car.api.out.CarLinkLoadPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarLinkUpdateBusinessRuleValidator {

  private final CarLinkLoadPort loadPort;

  public ViolationsCollector validate(final CarLinkUpdateRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request, violationsCollector);

    return violationsCollector;
  }

  private void checkExistence(
      final CarLinkUpdateRequest request, final ViolationsCollector violationsCollector) {
    final var carLinkId = request.getId();
    if (loadPort.loadById(carLinkId) == null) {
      violationsCollector.collect(format("Car Link with id: %d was not found.", carLinkId));
    }
  }
}
