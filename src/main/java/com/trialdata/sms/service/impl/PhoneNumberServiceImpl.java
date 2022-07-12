package com.trialdata.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trialdata.sms.dto.PhoneNumberDto;
import com.trialdata.sms.entity.PhoneNumberEntity;
import com.trialdata.sms.mapper.PhoneNumberMapper;
import com.trialdata.sms.service.PhoneListService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl extends
    ServiceImpl<PhoneNumberMapper, PhoneNumberEntity> implements
    PhoneListService {

  @Override
  public void addPhoneList(List<PhoneNumberDto> phoneList) {

    if (phoneList.size() > 0) {
      List<PhoneNumberEntity> phoneNumbers = new ArrayList<>();
      phoneList.forEach(
          phoneNumber -> {
            PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
            boolean exists = query()
                .eq("mobile", phoneNumber.getMobile())
                .eq("type", phoneNumber.getType())
                .eq("is_delete", 0)
                .exists();
            if (exists) {
              return;
            }
            BeanUtils.copyProperties(phoneNumber, phoneNumberEntity);
            phoneNumbers.add(phoneNumberEntity);
          }
      );
      saveBatch(phoneNumbers);
    }
  }
}
