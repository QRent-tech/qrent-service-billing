package ee.qrent.ui.controller.transaction.assembler;

import static java.util.stream.Collectors.toList;

import ee.qrent.billing.bonus.api.in.query.GetObligationQuery;
import ee.qrent.driver.api.in.query.GetDriverQuery;
import ee.qrent.driver.api.in.response.DriverResponse;
import ee.qrent.transaction.api.in.query.balance.GetBalanceQuery;
import ee.qrent.ui.controller.transaction.model.DriversBalanceModel;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DriverBalanceAssembler {

  private final GetBalanceQuery balanceQuery;
  private final GetDriverQuery driverQuery;
  private final GetObligationQuery obligationQuery;

  public List<DriversBalanceModel> getDriversBalanceModels() {
    final var start = System.currentTimeMillis();
    final var models =
        driverQuery.getAll().parallelStream().map(this::assembleModel).collect(toList());
    final var end = System.currentTimeMillis();
    System.out.println("Balances calculation took: " + (end - start) + " ms");

    return models;
  }

  private DriversBalanceModel assembleModel(final DriverResponse driver) {
    final var driverId = driver.getId();
    final var rawBalance = balanceQuery.getRawCurrentByDriver(driverId);
    final var rawTotal =
        rawBalance
            .getFeeAbleAmount()
            .add(rawBalance.getNonFeeAbleAmount())
            .add(rawBalance.getPositiveAmount());
    final var fee = rawBalance.getFeeAmount();
    final var obligationAmount =
        obligationQuery.getRawObligationAmountForCurrentWeekByDriverId(driverId);
    final var preCurrentWeekObligation =
        obligationQuery.getObligationAmountForPreCurrentWeekByDriverId(driverId);
    final var obligationMatchCount =
        preCurrentWeekObligation == null ? null : preCurrentWeekObligation.getMatchCount();

    return DriversBalanceModel.builder()
        .driverId(driverId)
        .firstName(driver.getFirstName())
        .lastName(driver.getLastName())
        .phone(driver.getPhone())
        .callSign(driver.getCallSign())
        .rawTotal(rawTotal)
        .fee(fee)
        .obligationAmount(obligationAmount)
        .obligationMatchCount(obligationMatchCount)
        .build();
  }
}
