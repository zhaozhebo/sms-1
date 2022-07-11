package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("phone_list")
public class PhoneListEntity extends BaseEntity {

  private TYPE type;
  private String mobile;
  private String remark;

  public enum TYPE {
    WHITE_LIST,
    BLACK_LIST
  }

}
