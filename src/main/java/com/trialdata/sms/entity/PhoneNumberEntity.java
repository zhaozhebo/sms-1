package com.trialdata.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("phone_number")
public class PhoneNumberEntity extends BaseEntity {

  private TYPE type;
  private String mobile;

  public enum TYPE {
    WHITE_LIST,
    BLACK_LIST
  }

}
