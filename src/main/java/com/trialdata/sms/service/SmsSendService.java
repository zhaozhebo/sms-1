package com.trialdata.sms.service;

import com.alibaba.fastjson.JSON;
import com.trialdata.sms.dto.SmsParamsDto;
import com.trialdata.sms.entity.ReceiveLogEntity;
import com.trialdata.sms.entity.SendLogEntity;
import com.trialdata.sms.tools.exception.SmsException;
import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SmsSendService {

  private final XiAoSmsService xiAoSmsService;
  private final SendLogService sendLogService;
  private final ReceiveLogService receiveLogService;
  private final PhoneNumberService phoneNumberService;

  public SmsSendService(
      XiAoSmsService xiAoSmsService,
      SendLogService sendLogService,
      ReceiveLogService receiveLogService,
      PhoneNumberService phoneNumberService) {
    this.xiAoSmsService = xiAoSmsService;
    this.sendLogService = sendLogService;
    this.receiveLogService = receiveLogService;
    this.phoneNumberService = phoneNumberService;
  }

  public void sendMessage(SmsParamsDto smsParamsDto) {

    String mobile = smsParamsDto.getMobile();
    String message = smsParamsDto.getMessage();
    long begin = System.currentTimeMillis();

    ReceiveLogEntity receiveLog = new ReceiveLogEntity();
    receiveLog.setMobile(mobile);
    receiveLog.setRequest(message);
    receiveLogService.save(receiveLog);

    phoneNumberService.checkPhoneNumber(mobile);

    SendLogEntity sendLog = new SendLogEntity();

    sendLog.setMobile(mobile);
    sendLog.setRequest(JSON.toJSONString(smsParamsDto));
    sendLog.setStatus(1);

    try {
      sendLog.setConfigName("XiAo");
      String response = xiAoSmsService.sendSms(mobile, message);

      sendLog.checkResponse(response);
      // 发送成功
      log.info("发送成功：{}", response);
    } catch (Exception e) {
      log.warn("发送异常 返回值：{}", sendLog.getResponse(), e);
      sendLog.setStatus(0);
      sendLog.setError(getExceptionMessage(e));
      throw new SmsException("信息发送异常！");

    } finally {
      long end = System.currentTimeMillis();
      sendLog.setUseTime(end - begin);

      sendLogService.save(sendLog);
    }
  }

  private String getExceptionMessage(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter printWriter = new PrintWriter(sw);
    e.printStackTrace(printWriter);
    return sw.toString();
  }
}
