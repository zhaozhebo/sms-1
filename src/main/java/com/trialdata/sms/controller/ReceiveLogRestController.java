package com.trialdata.sms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trialdata.sms.entity.ReceiveLogEntity;
import com.trialdata.sms.service.ReceiveLogService;
import com.trialdata.sms.tools.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "接收请求日志管理")
@Slf4j
@RequestMapping("receiveLog")
@RestController
public class ReceiveLogRestController {

  private final ReceiveLogService receiveLogService;

  public ReceiveLogRestController(ReceiveLogService receiveLogService) {
    this.receiveLogService = receiveLogService;
  }

  @GetMapping("/condition")
  public R getReceiveLog(
      @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "10") int size,
      @RequestParam(required = false) String mobile,
      @RequestParam(required = false) Integer status) {

    log.info("查询接收日志,手机号：{}，状态；{}", mobile, status);

    Page<ReceiveLogEntity> pageParams = new Page<>(page < 1 ? 1 : page, size);

    IPage<ReceiveLogEntity> result = receiveLogService
        .selectPage(pageParams, mobile, status);

    return R.success(result);
  }
}
