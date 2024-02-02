package ee.qrental.bonus.core.mapper;

import ee.qrental.bonus.api.in.response.BonusProgramResponse;
import ee.qrental.bonus.domain.BonusProgram;
import ee.qrental.common.core.in.mapper.ResponseMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BonusProgramResponseMapper
    implements ResponseMapper<BonusProgramResponse, BonusProgram> {

  @Override
  public BonusProgramResponse toResponse(final BonusProgram domain) {
    if (domain == null) {
      return null;
    }

    return BonusProgramResponse.builder()
        .id(domain.getId())
        .code(domain.getCode())
        .nameEng(domain.getNameEng())
        .nameEst(domain.getNameEst())
        .nameRus(domain.getNameRus())
        .description(domain.getDescription())
        .active(domain.getActive())
        .build();
  }

  @Override
  public String toObjectInfo(final BonusProgram domain) {
    return null;
  }
}
