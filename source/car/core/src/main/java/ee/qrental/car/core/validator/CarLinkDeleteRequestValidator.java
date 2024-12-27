package ee.qrental.car.core.validator;

import static java.lang.String.format;

import ee.qrent.common.in.request.ViolationsCollector;
import ee.qrental.car.api.in.request.CarLinkDeleteRequest;
import ee.qrental.car.api.out.CarLinkLoadPort;
import ee.qrental.common.core.validation.DeleteRequestValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarLinkDeleteRequestValidator implements DeleteRequestValidator<CarLinkDeleteRequest> {

  private final CarLinkLoadPort loadPort;

  public ViolationsCollector validate(final CarLinkDeleteRequest request) {
    final var violationsCollector = new ViolationsCollector();
    checkExistence(request, violationsCollector);
    // TODO add 'no balance/ calculation check' check

    return violationsCollector;
  }

  private void checkExistence(
      final CarLinkDeleteRequest request, final ViolationsCollector violationsCollector) {
    final var carLinkId = request.getId();
    if (loadPort.loadById(carLinkId) == null) {
      violationsCollector.collect(format("Car Link with id: %d was not found.", carLinkId));
    }
  }
}
