package com.trialdata.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trialdata.sms.entity.ReceiveLogEntity;
import com.trialdata.sms.mapper.ReceiveLogMapper;
import com.trialdata.sms.service.ReceiveLogService;
import com.trialdata.sms.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ReceiveLogServiceImpl extends
    ServiceImpl<ReceiveLogMapper, ReceiveLogEntity> implements
    ReceiveLogService {

  private final ReceiveLogMapper receiveLogMapper;

  public ReceiveLogServiceImpl(ReceiveLogMapper receiveLogMapper) {
    this.receiveLogMapper = receiveLogMapper;
  }

  @Override
  public IPage<ReceiveLogEntity> selectPage(
      Page<ReceiveLogEntity> pageParams, String mobile, Integer status) {

    LambdaQueryWrapper<ReceiveLogEntity> wrapper = new LambdaQueryWrapper<>();
    if (StringUtils.isNotNull(mobile)) {
      StringUtils.checkMobileRegex(mobile);
      wrapper.eq(ReceiveLogEntity::getMobile, mobile);
    }
    if (status != null) {
      wrapper.eq(ReceiveLogEntity::getStatus, status);
    }
    wrapper.orderByDesc(ReceiveLogEntity::getCreateTime);

    return receiveLogMapper.selectPage(pageParams, wrapper);
  }
}
