package com.trialdata.sms.service.impl;

import static com.trialdata.sms.entity.PhoneNumberEntity.TYPE.BLACK_LIST;
import static com.trialdata.sms.entity.PhoneNumberEntity.TYPE.WHITE_LIST;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trialdata.sms.dto.PhoneNumberDto;
import com.trialdata.sms.entity.PhoneNumberEntity;
import com.trialdata.sms.mapper.PhoneNumberMapper;
import com.trialdata.sms.service.PhoneNumberService;
import com.trialdata.sms.tools.exception.SmsException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl extends
    ServiceImpl<PhoneNumberMapper, PhoneNumberEntity> implements
    PhoneNumberService {

  private final PhoneNumberMapper phoneNumberMapper;

  public PhoneNumberServiceImpl(PhoneNumberMapper phoneNumberMapper) {
    this.phoneNumberMapper = phoneNumberMapper;
  }

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

  @Override
  public void checkPhoneNumber(String mobile) {

    LambdaQueryWrapper<PhoneNumberEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(PhoneNumberEntity::getMobile, mobile)
        .eq(PhoneNumberEntity::getType, WHITE_LIST)
        .eq(PhoneNumberEntity::getIsDelete, 0);

    boolean existInWhiteList = phoneNumberMapper.exists(wrapper);
    if (!existInWhiteList) {
      throw new SmsException("号码不存在于白名单内,咱无法发送，仅测试用！");
    }

    LambdaQueryWrapper<PhoneNumberEntity> wrapper2 = new LambdaQueryWrapper<>();
    wrapper2.eq(PhoneNumberEntity::getMobile, mobile)
        .eq(PhoneNumberEntity::getType, BLACK_LIST)
        .eq(PhoneNumberEntity::getIsDelete, 0);

    boolean existInBlackList = phoneNumberMapper.exists(wrapper2);

    if (existInBlackList) {
      throw new SmsException("号码在黑名单内，无法发送！");
    }
  }
}
