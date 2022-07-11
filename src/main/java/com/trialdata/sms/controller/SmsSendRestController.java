package com.trialdata.sms.controller;

import com.trialdata.sms.dto.SmsParamsDto;
import com.trialdata.sms.service.SmsSendService;
import com.trialdata.sms.tools.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sms")
@Api(tags = "短信")
@Slf4j
public class SmsSendRestController {

  private final SmsSendService smsSendService;

  @Autowired
  public SmsSendRestController(SmsSendService smsSendService) {
    this.smsSendService = smsSendService;
  }

  @PostMapping("send")
  @ApiOperation("发送短信")
  public R send(@RequestBody SmsParamsDto smsParamsDTO) {
    log.info("发送短信 params:{}", smsParamsDTO);
    try {
      smsSendService.sendMessage(smsParamsDTO);
    } catch (Exception e) {
      log.error("发送异常", e);
      return R.fail(e.getMessage());
    }
    return R.success();
  }

}
