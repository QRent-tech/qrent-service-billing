package ee.qrental.contract.api.in.query;

import ee.qrent.common.in.query.BaseGetQuery;
import ee.qrental.contract.api.in.request.ContractUpdateRequest;
import ee.qrental.contract.api.in.response.ContractResponse;

import java.util.List;

public interface GetContractQuery extends BaseGetQuery<ContractUpdateRequest, ContractResponse> {
  ContractResponse getLatestContractByDriverId(final Long driverId);

  List<String> getAllDurations();

  List<ContractResponse> getAllActive();

  ContractResponse getCurrentActiveByDriverId(Long driverId);

  List<ContractResponse> getClosed();

  Long getCountActive();

  Long getCountClosed();
}
