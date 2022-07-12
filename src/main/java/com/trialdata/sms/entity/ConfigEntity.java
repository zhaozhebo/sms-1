package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("config")
public class ConfigEntity extends BaseEntity {

  private String name;

  private String domain;

  private String accessKeyId;

  @TableField(select = false)
  private String accessKeySecret;

  private Integer isActive = 1;

  private Integer isEnable = 1;
}
