package com.trialdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trialdata.sms.entity.ConfigEntity;

public interface ConfigService extends IService<ConfigEntity> {

  ConfigEntity findByName(String name);
}
