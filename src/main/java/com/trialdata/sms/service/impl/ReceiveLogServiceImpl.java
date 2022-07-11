package com.trialdata.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trialdata.sms.entity.ReceiveLogEntity;
import com.trialdata.sms.mapper.ReceiveLogMapper;
import com.trialdata.sms.service.ReceiveLogService;
import org.springframework.stereotype.Service;

@Service
public class ReceiveLogServiceImpl extends
    ServiceImpl<ReceiveLogMapper, ReceiveLogEntity> implements
    ReceiveLogService {

}
