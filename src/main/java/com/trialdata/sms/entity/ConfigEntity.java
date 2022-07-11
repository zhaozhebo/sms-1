package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("config")
public class ConfigEntity extends BaseEntity {

  private String name;

  private String platform;

  private String domain;

  private String accessKeyId;

  private String accessKeySecret;

  private Integer isActive;

  private Integer isEnable;

  private String remark;

  private Integer level;

  private Integer channelType;
}
