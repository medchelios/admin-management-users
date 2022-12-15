package com.bafagroupe.admin.management.users.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.bafagroupe.admin.management.users.model.AppUserRole;

@Data
@NoArgsConstructor
public class UserDataDTO {
  
  @ApiModelProperty(position = 0)
  private String username;
  @ApiModelProperty(position = 1)
  private String email;
  @ApiModelProperty(position = 2)
  private String password;
  @ApiModelProperty(position = 3)
  List<AppUserRole> appUserRoles;

  @ApiModelProperty(position =4 )
  private String passwordConfirmed;
  @ApiModelProperty(position = 5)
  private String emailOld;
  @ApiModelProperty(position = 6)
  private boolean validated;
  @ApiModelProperty(position = 7)
  private boolean activated;

}
