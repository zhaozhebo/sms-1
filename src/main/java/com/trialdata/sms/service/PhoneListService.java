package com.trialdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trialdata.sms.dto.PhoneNumberDto;
import com.trialdata.sms.entity.PhoneNumberEntity;
import java.util.List;

public interface PhoneListService extends IService<PhoneNumberEntity> {

  void addPhoneList(List<PhoneNumberDto> phoneList);
}