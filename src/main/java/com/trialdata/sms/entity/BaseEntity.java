package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long id;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  private Integer isDelete;
}
