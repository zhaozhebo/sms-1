package com.trialdata.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trialdata.sms.entity.SendLogEntity;
import com.trialdata.sms.mapper.SendLogMapper;
import com.trialdata.sms.service.SendLogService;
import com.trialdata.sms.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SendLogServiceImpl extends ServiceImpl<SendLogMapper, SendLogEntity> implements
    SendLogService {

  private final SendLogMapper sendLogMapper;

  public SendLogServiceImpl(SendLogMapper sendLogMapper) {
    this.sendLogMapper = sendLogMapper;
  }

  @Override
  public IPage<SendLogEntity> selectPage(
      Page<SendLogEntity> pageParams, String configName, String mobile) {

    LambdaQueryWrapper<SendLogEntity> wrapper = new LambdaQueryWrapper<>();
    if (StringUtils.isNotNull(configName)) {
      wrapper.eq(SendLogEntity::getConfigName, configName);
    }
    if (StringUtils.isNotNull(mobile)) {
      StringUtils.checkMobileRegex(mobile);
      wrapper.eq(SendLogEntity::getMobile, mobile);
    }
    wrapper.orderByDesc(SendLogEntity::getCreateTime);

    return sendLogMapper.selectPage(pageParams, wrapper);
  }
}
