package com.trialdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trialdata.sms.dto.PhoneNumberDto;
import com.trialdata.sms.entity.PhoneNumberEntity;
import java.util.List;

public interface PhoneNumberService extends IService<PhoneNumberEntity> {

  void addPhoneList(List<PhoneNumberDto> phoneList);

  void checkPhoneNumber(String mobile);
}