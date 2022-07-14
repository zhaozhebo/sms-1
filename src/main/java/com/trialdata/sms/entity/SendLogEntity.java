package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.trialdata.sms.tools.exception.SmsException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("send_log")
public class SendLogEntity extends BaseEntity {

  private String mobile;

  private String configName;

  private String request;

  private String response;

  private String error;

  private Long useTime;

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
