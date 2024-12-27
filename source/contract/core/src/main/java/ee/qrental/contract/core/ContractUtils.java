package ee.qrental.contract.core;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

@UtilityClass
public class ContractUtils {

  public static String generateContractNumber(final Long driverId, final LocalDateTime startTime) {
    final var dateTimeString = startTime.format(ofPattern("yyyyMMdd-HHmm"));

    return String.format("%s-%d", dateTimeString, driverId);
  }
}
