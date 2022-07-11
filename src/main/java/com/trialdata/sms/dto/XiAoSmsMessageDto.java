package com.trialdata.sms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class XiAoSmsMessageDto {

  int uid;

  String password;

  String mobile;

  String msg;

  String extend;

  String customId;

  String time;
}
