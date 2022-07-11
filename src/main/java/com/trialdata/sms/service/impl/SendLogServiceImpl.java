package com.trialdata.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trialdata.sms.entity.SendLogEntity;
import com.trialdata.sms.mapper.SendLogMapper;
import com.trialdata.sms.service.SendLogService;
import org.springframework.stereotype.Service;

@Service
public class SendLogServiceImpl extends ServiceImpl<SendLogMapper, SendLogEntity> implements
    SendLogService {

}
