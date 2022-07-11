package com.trialdata.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trialdata.sms.entity.ConfigEntity;
import com.trialdata.sms.mapper.ConfigMapper;
import com.trialdata.sms.service.ConfigService;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, ConfigEntity> implements
    ConfigService {


  @Override
  public ConfigEntity findByName(String name) {
    LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(ConfigEntity::getName, "XiAo");

    return baseMapper.selectOne(wrapper);
  }
}
