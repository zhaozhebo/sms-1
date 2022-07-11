package com.trialdata.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.trialdata.sms.mapper")
public class SmsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmsApplication.class, args);
  }

}
