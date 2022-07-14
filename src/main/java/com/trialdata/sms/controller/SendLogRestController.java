package com.trialdata.sms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trialdata.sms.entity.SendLogEntity;
import com.trialdata.sms.service.SendLogService;
import com.trialdata.sms.tools.R;
import io.swagger.annotations.Api;
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
      @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "10") int size,
      @RequestParam(required = false) String configName,
      @RequestParam(required = false) String mobile) {

    log.info("查询发送日志: 手机号：{}", mobile);

    Page<SendLogEntity> pageParams = new Page<>(page < 1 ? 1 : page, size);

    IPage<SendLogEntity> result = sendLogService
        .selectPage(pageParams, configName, mobile);

    return R.success(result);
  }
}
