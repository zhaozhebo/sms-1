package com.trialdata.sms.util;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

public class SpringUtils {

  private static ApplicationContext applicationContext;
  private static ApplicationContext parentApplicationContext;

  public SpringUtils() {
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static void setApplicationContext(ApplicationContext ctx) {
    Assert.notNull(ctx, "SpringUtil injection ApplicationContext is null");
    applicationContext = ctx;
    parentApplicationContext = ctx.getParent();
  }

  public static Object getBean(String name) {
    Assert.hasText(name, "SpringUtil name is null or empty");

    try {
      return applicationContext.getBean(name);
    } catch (Exception var2) {
      return parentApplicationContext.getBean(name);
    }
  }

  public static <T> T getBean(String name, Class<T> type) {
    Assert.hasText(name, "SpringUtil name is null or empty");
    Assert.notNull(type, "SpringUtil type is null");

    try {
      return applicationContext.getBean(name, type);
    } catch (Exception var3) {
      return parentApplicationContext.getBean(name, type);
    }
  }

  public static <T> T getBean(Class<T> type) {
    Assert.notNull(type, "SpringUtil type is null");

    try {
      return applicationContext.getBean(type);
    } catch (Exception var2) {
      return parentApplicationContext.getBean(type);
    }
  }

  public static <T> Map<String, T> getBeansOfType(Class<T> type) {
    Assert.notNull(type, "SpringUtil type is null");

    try {
      return applicationContext.getBeansOfType(type);
    } catch (Exception var2) {
      return parentApplicationContext.getBeansOfType(type);
    }
  }
}

