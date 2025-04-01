package ee.qrent.user.core.mapper;


import ee.qrent.common.in.mapper.ResponseMapper;
import ee.qrent.driver.domain.Role;
import ee.qrent.user.api.in.response.RoleResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleResponseMapper implements ResponseMapper<RoleResponse, Role> {
  @Override
  public RoleResponse toResponse(final Role domain) {

    return RoleResponse.builder()
        .id(domain.getId())
        .name(domain.getName())
        .comment(domain.getComment())
        .build();
  }

  @Override
  public String toObjectInfo(Role domain) {
    return domain.getName();
  }
}
