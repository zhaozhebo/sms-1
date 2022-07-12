package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.trialdata.sms.tools.exception.SmsException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("send_log")
public class SendLogEntity extends BaseEntity {

  @ApiModelProperty(value = "手机号")
  private String mobile;

  @ApiModelProperty(value = "请求参数")
  private String request;

  @ApiModelProperty(value = "返回参数")
  private String response;

  @ApiModelProperty(value = "错误信息")
  private String error;

  @ApiModelProperty(value = "耗时")
  private Long useTime;

  @ApiModelProperty(value = "状态：0失败，1成功")
  private Integer status;

  public void checkResponse(String response) {
    if (response.startsWith("FAIL@#@")) {
      String[] responseArray = response.split("@#@");
      this.response = responseArray[2];
      throw new SmsException(responseArray[1]);
    } else {
      this.response = response;
    }
  }
}
