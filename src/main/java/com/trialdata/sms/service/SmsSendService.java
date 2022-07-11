package com.trialdata.sms.service;

import com.alibaba.fastjson.JSON;
import com.trialdata.sms.dto.SmsParamsDto;
import com.trialdata.sms.entity.ReceiveLogEntity;
import com.trialdata.sms.entity.SendLogEntity;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SmsSendService {

  private final XiAoSmsService xiAoSmsService;
  private final SendLogService sendLogService;
  private final ReceiveLogService receiveLogService;

  public SmsSendService(
      XiAoSmsService xiAoSmsService,
      SendLogService sendLogService, ReceiveLogService receiveLogService) {
    this.xiAoSmsService = xiAoSmsService;
    this.sendLogService = sendLogService;
    this.receiveLogService = receiveLogService;
  }

  public void sendMessage(SmsParamsDto smsParamsDto) {

    String mobile = smsParamsDto.getMobile();
    Map<String, String> params = smsParamsDto.getParams();
    long begin = System.currentTimeMillis();

    ReceiveLogEntity receiveLog = new ReceiveLogEntity();
    receiveLog.setMobile(mobile);
    receiveLog.setRequest(JSON.toJSONString(params));
    receiveLogService.save(receiveLog);

    SendLogEntity sendLog = new SendLogEntity();

    sendLog.setMobile(mobile);
    sendLog.setRequest(JSON.toJSONString(smsParamsDto));
    sendLog.setStatus(1);

    try {
      String response = xiAoSmsService.sendSms(mobile, params);

      sendLog.checkResponse(response);
      // 发送成功
      log.info("发送成功：{}", response);
    } catch (Exception e) {
      log.warn("发送异常 返回值：{}", sendLog.getResponse(), e);
      sendLog.setStatus(0);
      sendLog.setError(getExceptionMessage(e));

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
