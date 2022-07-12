package com.trialdata.sms.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("start insert .. ");
    setFieldValByName("createTime", LocalDateTime.now(), metaObject);
    setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("start update .. ");
    setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
  }
}

