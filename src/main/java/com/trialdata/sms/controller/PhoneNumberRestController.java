package com.trialdata.sms.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.trialdata.sms.dto.PhoneNumberDto;
import com.trialdata.sms.entity.PhoneNumberEntity;
import com.trialdata.sms.entity.PhoneNumberEntity.TYPE;
import com.trialdata.sms.service.PhoneListService;
import com.trialdata.sms.tools.R;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "黑白名单")
@Slf4j
@RequestMapping("phone")
@RestController
public class PhoneNumberRestController {

  private final PhoneListService phoneListService;

  public PhoneNumberRestController(PhoneListService phoneListService) {
    this.phoneListService = phoneListService;
  }

  @PostMapping("/add")
  public R<Boolean> addPhoneList(@RequestBody List<PhoneNumberDto> phoneList) {

    log.info("向名单中添加手机号:{}", phoneList);

    phoneListService.addPhoneList(phoneList);
    return R.success();
  }

  @GetMapping("/type")
  public R getPhoneListByType(@RequestParam TYPE type) {

    log.info("根据类型查看所有的名单:{}", type);

    List<PhoneNumberEntity> list = phoneListService.query()
        .eq("type", type)
        .eq("is_delete", 0)
        .list();
    return R.success(list);
  }

  @PostMapping("/update/type")
  public R<Boolean> updatePhoneType(@RequestBody PhoneNumberDto phoneNumber) {

    log.info("更新手机号的类型:{}", phoneNumber);

    boolean success = phoneListService.update()
        .set("type", phoneNumber.getType().toString())
        .eq("mobile", phoneNumber.getMobile())
        .eq("is_delete", 0)
        .update();
    return R.success(success);
  }

  @DeleteMapping("/delete/mobile")
  public R<Boolean> deleteMobile(@RequestBody PhoneNumberDto phoneNumber) {

    log.info("从表中删除手机号:{}", phoneNumber);

    LambdaUpdateWrapper<PhoneNumberEntity> wrapper = new LambdaUpdateWrapper<>();
    wrapper.eq(PhoneNumberEntity::getMobile, phoneNumber.getMobile())
        .eq(PhoneNumberEntity::getType, phoneNumber.getType())
        .eq(PhoneNumberEntity::getIsDelete, 0);
    boolean remove = phoneListService.remove(wrapper);

    return R.success(remove);
  }

  @DeleteMapping("/delete/type")
  public R<Boolean> deleteByType(@RequestParam TYPE type) {

    log.info("根据类型从表中删除手机号:{}", type);

    LambdaUpdateWrapper<PhoneNumberEntity> wrapper = new LambdaUpdateWrapper<>();
    wrapper.eq(PhoneNumberEntity::getType, type)
        .eq(PhoneNumberEntity::getIsDelete, 0);
    boolean remove = phoneListService.remove(wrapper);

    return R.success(remove);
  }
}
