package com.trialdata.sms.util;

import com.trialdata.sms.tools.exception.SmsException;

public class StringUtils {

  public static boolean isNotNull(String value) {

    return value != null && !value.trim().equals("") && !value.trim().equals("null");
  }

  public static void checkMobileRegex(String mobile) {

    final String regexp = "/^1[3456789]\\d{9}$/";
    if (!regexp.matches(mobile)) {
      throw new SmsException("手机号输入错误");
    }
  }

}
