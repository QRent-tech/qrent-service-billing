package ee.qrent.driver.adapter.mapper;

import ee.qrent.driver.domain.CallSign;
import ee.qrent.driver.entity.jakarta.CallSignJakartaEntity;

public class CallSignAdapterMapper {

  public CallSign mapToDomain(final CallSignJakartaEntity entity) {
    if (entity == null) {
      return null;
    }
    return CallSign.builder()
        .id(entity.getId())
        .callSign(entity.getCallSign())
        .comment(entity.getComment())
        .build();
  }

  public CallSignJakartaEntity mapToEntity(final CallSign domain) {
    return CallSignJakartaEntity.builder()
        .id(domain.getId())
        .callSign(domain.getCallSign())
        .comment(domain.getComment())
        .build();
  }
}
