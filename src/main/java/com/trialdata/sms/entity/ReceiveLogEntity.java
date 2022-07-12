package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("receive_log")
public class ReceiveLogEntity extends BaseEntity {

  private String configName;

  private String mobile;

  private String request;

  // "状态：0失败，1成功"
  private int status = 1;
}
