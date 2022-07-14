package com.trialdata.sms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.trialdata.sms.entity.SendLogEntity;
import com.trialdata.sms.service.SendLogService;
import com.trialdata.sms.tools.R;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "发送短信日志管理")
@Slf4j
@RequestMapping("sendLog")
@RestController
public class SendLogRestController {

  private final SendLogService sendLogService;

  public SendLogRestController(SendLogService sendLogService) {
    this.sendLogService = sendLogService;
  }

  @GetMapping("/condition")
  public R getSendLog(
      @RequestParam(required = false) String configName,
      @RequestParam(required = false) String mobile) {

    log.info("查询发送日志: 手机号：{}", mobile);

    final String regexp = "/^1[3456789]\\d{9}$/";

    LambdaQueryWrapper<SendLogEntity> wrapper = new LambdaQueryWrapper<>();

    if (isNotNull(configName)) {
      wrapper.eq(SendLogEntity::getConfigName, configName);
    }
    if (isNotNull(mobile)) {
      if (regexp.matches(mobile)) {
        wrapper.eq(SendLogEntity::getMobile, mobile);
      } else {
        return R.fail("手机号输入错误");
      }
    }
    List<SendLogEntity> list = sendLogService.list(wrapper);

    return R.success(list);
  }

  private boolean isNotNull(String value) {
    return value != null && !value.trim().equals("") && !value.trim().equals("null");
  }
}
