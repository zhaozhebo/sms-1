package com.trialdata.sms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.trialdata.sms.entity.ReceiveLogEntity;
import com.trialdata.sms.service.ReceiveLogService;
import com.trialdata.sms.tools.R;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "接收发送请求日志管理")
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
      @RequestParam(required = false) String configName,
      @RequestParam(required = false) String mobile,
      @RequestParam(required = false) Integer status) {

    log.info("查询接收日志,供应商：{}，手机号：{}，状态；{}", configName, mobile, status);

    LambdaQueryWrapper<ReceiveLogEntity> wrapper = new LambdaQueryWrapper<>();
    if (isNotNull(configName)) {
      wrapper.eq(ReceiveLogEntity::getConfigName, configName);
    }
    if (isNotNull(mobile)) {
      wrapper.eq(ReceiveLogEntity::getMobile, mobile);
    }
    if (status != null) {
      wrapper.eq(ReceiveLogEntity::getStatus, status);
    }
    List<ReceiveLogEntity> list = receiveLogService.list(wrapper);
    return R.success(list);
  }

  private boolean isNotNull(String value) {
    return value != null && !value.trim().equals("") && !value.trim().equals("null");
  }
}
