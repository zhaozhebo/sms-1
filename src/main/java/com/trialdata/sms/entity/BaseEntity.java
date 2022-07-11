package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BaseEntity {

  @TableId
  private String id;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  private Integer isDelete;
}
