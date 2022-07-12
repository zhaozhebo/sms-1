package com.trialdata.sms.controller;

import com.trialdata.sms.dto.ConfigDto;
import com.trialdata.sms.entity.ConfigEntity;
import com.trialdata.sms.service.ConfigService;
import com.trialdata.sms.tools.R;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Api(tags = "短信供应商管理")
@Slf4j
@RequestMapping("config")
@RestController
public class ConfigRestController {

  private final ConfigService configService;

  public ConfigRestController(ConfigService configService) {
    this.configService = configService;
  }

  @PostMapping("/add")
  public R<Boolean> addConfig(@RequestBody ConfigDto configDto) {

    log.info("添加短信供应商信息:{}", configDto);

    boolean exists = configService.query()
        .eq("name", configDto.getName())
        .eq("is_delete", 0)
        .exists();
    if (exists) {
      return R.fail("供应商已经存在");
    }

    ConfigEntity configEntity = new ConfigEntity();
    BeanUtils.copyProperties(configDto, configEntity);

    boolean save = configService.save(configEntity);
    return R.success(save);
  }

  @GetMapping("/all")
  public R getAll() {

    log.info("查询所有短信供应商");

    List<ConfigEntity> list = configService.query()
        .eq("is_delete", 0)
        .list();
    return R.success(list);
  }

  @PostMapping("/update/name")
  public R<Boolean> updatePhoneType(@RequestBody ConfigDto configDto) {

    log.info("更新供应商信息:{}", configDto);
    ConfigEntity config = configService.findByName(configDto.getName());
    if (config == null) {
      return R.fail("供应商不存在！");
    }
    BeanUtils.copyProperties(configDto, config);
    boolean save = configService.save(config);

    return R.success(save);
  }

  @DeleteMapping("/delete/name")
  public R<Boolean> deleteByName(@RequestParam String name) {

    log.info("根据名称删除供应商:{}", name);

    ConfigEntity config = configService.findByName(name);
    if (config == null) {
      return R.fail("供应商不存在！");
    }
    boolean success = configService.removeById(config);

    return R.success(success);
  }
}
