package com.trialdata.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SmsParamsDto {

  @NotBlank
  @Pattern(regexp = "/^1[3456789]\\d{9}$/")
  @ApiModelProperty("手机号")
  private String mobile;

  @NotBlank
  @ApiModelProperty("短信内容")
  private String message;
}
