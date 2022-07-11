package com.trialdata.sms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trialdata.sms.dto.XiAoSmsMessageDto;
import com.trialdata.sms.entity.ConfigEntity;
import com.trialdata.sms.util.SpringUtils;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * 希奥短信服务
 *
 * @author
 */
@Slf4j
@Service
public class XiAoSmsService {

  private static final String jsonUrl = "ordinaryjson";

  private final ConfigService configService;

  public XiAoSmsService(ConfigService configService) {
    this.configService = configService;
  }


  protected String sendSms(
      String mobile, Map<String, String> params) {

    ConfigEntity configEntity = configService.findByName("XiAo");

    XiAoSmsMessageDto xiAoSmsMessage = XiAoSmsMessageDto.builder()
        .uid(Integer.parseInt(configEntity.getAccessKeyId()))
        .password(configEntity
            .getAccessKeySecret()).
            mobile(mobile)
        .msg(params.get("msg"))
        .build();

    String url = configEntity.getDomain() + jsonUrl;

    ResponseEntity<Object> responseEntity = SpringUtils.getBean(RestTemplate.class)
        .postForEntity(url, xiAoSmsMessage, Object.class);
    HttpStatus statusCode = responseEntity.getStatusCode();

    if (HttpStatus.OK.equals(statusCode)) {
      log.info("httpRequest access success, StatusCode is:{}", statusCode);
      Object body = responseEntity.getBody();
      assert body != null;
      JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(body));
      if (jsonObject.containsKey("code") && jsonObject.getInteger("code") == 0) {
        return body.toString();
      } else {
        return failResponse(jsonObject.getString("message"), body.toString());
      }
    } else {
      log.error("httpRequest access fail ,StatusCode is:{}", statusCode);
    }

    return null;
  }

  protected String failResponse(String msg, String response) {
    return "FAIL@#@" + msg + "@#@" + response;
  }
}

