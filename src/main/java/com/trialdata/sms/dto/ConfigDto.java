package com.trialdata.sms.dto;

import lombok.Data;

@Data
public class ConfigDto {

  private String name;

  private String domain;

  private String accessKeyId;

  private String accessKeySecret;
}
