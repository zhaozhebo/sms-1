package com.trialdata.sms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trialdata.sms.entity.SendLogEntity;

public interface SendLogService extends IService<SendLogEntity> {

  IPage<SendLogEntity> selectPage(Page<SendLogEntity> pageParams, String configName, String mobile);
}
