package com.trialdata.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import lombok.Data;

@Data
public class SmsParamsDto {

  @ApiModelProperty("手机号")
  private String mobile;

  @ApiModelProperty("参数")
  private Map<String, String> params;
}
