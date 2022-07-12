package com.trialdata.sms.dto;

import com.trialdata.sms.entity.PhoneNumberEntity.TYPE;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PhoneNumberDto {

  private TYPE type;

  @NotBlank
  @Pattern(regexp = "/^1[3456789]\\d{9}$/")
  private String mobile;
}
