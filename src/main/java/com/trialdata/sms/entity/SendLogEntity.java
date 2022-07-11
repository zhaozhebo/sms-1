package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("send_log")
public class SendLogEntity extends BaseEntity {

  private String platformId;

  private String platformName;

  private String configIds;

  private String mobile;

  private String request;

  private String response;

  private String error;

  private Long useTime;

  // "状态：0失败，1成功"
  private int status;

  private String remark;
}
