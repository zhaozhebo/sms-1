package com.trialdata.sms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trialdata.sms.entity.ReceiveLogEntity;

public interface ReceiveLogService extends IService<ReceiveLogEntity> {

  IPage<ReceiveLogEntity> selectPage(
      Page<ReceiveLogEntity> pageParams,
      String mobile,
      Integer status);
}
