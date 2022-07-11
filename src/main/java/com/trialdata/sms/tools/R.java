package com.trialdata.sms.tools;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import java.util.Map;

public class R<T> {

  public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
  public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
  public static final int SUCCESS_CODE = 0;
  public static final int FAIL_CODE = -1;
  public static final int TIMEOUT_CODE = -2;
  public static final int VALID_EX_CODE = -9;
  public static final int OPERATION_EX_CODE = -10;
  @ApiModelProperty("响应编码:0/200-请求处理成功")
  private int code;
  @ApiModelProperty("响应数据")
  private T data;
  @ApiModelProperty("提示消息")
  private String msg = "ok";
  @ApiModelProperty("请求路径")
  private String path;
  @ApiModelProperty("附加数据")
  private Map<String, Object> extra;
  @ApiModelProperty("响应时间戳")
  private long timestamp = System.currentTimeMillis();

  public R(int code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;
  }

  public static <E> R<E> result(int code, E data, String msg) {
    return new R(code, data, msg);
  }

  public static <E> R<E> success(E data) {
    return new R(0, data, "ok");
  }

  public static R<Boolean> success() {
    return new R(0, true, "ok");
  }

  public static <E> R<E> success(E data, String msg) {
    return new R(0, data, msg);
  }

  public static <E> R<E> fail(int code, String msg) {
    return new R(code, (Object) null, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试");
  }

  public static <E> R<E> fail(String msg) {
    return fail(-10, msg);
  }

  public static <E> R<E> fail(String msg, Object... args) {
    String message = msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试";
    return new R(-10, (Object) null, String.format(message, args));
  }

  public static <E> R<E> fail(Throwable throwable) {
    return fail(-1, throwable != null ? throwable.getMessage() : "系统繁忙，请稍候再试");
  }

  public static <E> R<E> validFail(String msg) {
    return new R(-9, (Object) null, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试");
  }

  public static <E> R<E> validFail(String msg, Object... args) {
    String message = msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试";
    return new R(-9, (Object) null, String.format(message, args));
  }

  public static <E> R<E> timeout() {
    return fail(-2, "请求超时，请稍候再试");
  }

  public R<T> put(String key, Object value) {
    if (extra == null) {
      extra = Maps.newHashMap();
    }

    extra.put(key, value);
    return this;
  }

  public Boolean getIsSuccess() {
    return code == 0 || code == 200;
  }

  public Boolean getIsError() {
    return !getIsSuccess();
  }

  public R setPath(String path) {
    this.path = path;
    return this;
  }

  @Override
  public String toString() {
    return JSONObject.toJSONString(this);
  }

  public int getCode() {
    return code;
  }

  public T getData() {
    return data;
  }

  public String getMsg() {
    return msg;
  }

  public String getPath() {
    return path;
  }

  public Map<String, Object> getExtra() {
    return extra;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setExtra(Map<String, Object> extra) {
    this.extra = extra;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof R)) {
      return false;
    } else {
      R<?> other = (R) o;
      if (!other.canEqual(this)) {
        return false;
      } else if (getCode() != other.getCode()) {
        return false;
      } else {
        label65:
        {
          Object this$data = getData();
          Object other$data = other.getData();
          if (this$data == null) {
            if (other$data == null) {
              break label65;
            }
          } else if (this$data.equals(other$data)) {
            break label65;
          }

          return false;
        }

        Object this$msg = getMsg();
        Object other$msg = other.getMsg();
        if (this$msg == null) {
          if (other$msg != null) {
            return false;
          }
        } else if (!this$msg.equals(other$msg)) {
          return false;
        }

        Object this$path = getPath();
        Object other$path = other.getPath();
        if (this$path == null) {
          if (other$path != null) {
            return false;
          }
        } else if (!this$path.equals(other$path)) {
          return false;
        }

        label44:
        {
          Object this$extra = getExtra();
          Object other$extra = other.getExtra();
          if (this$extra == null) {
            if (other$extra == null) {
              break label44;
            }
          } else if (this$extra.equals(other$extra)) {
            break label44;
          }

          return false;
        }

        if (getTimestamp() != other.getTimestamp()) {
          return false;
        } else {
          return true;
        }
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof R;
  }

  @Override
  public int hashCode() {
    final boolean PRIME = true;
    final int code = 1;
    int result = code * 59 + getCode();
    Object $data = getData();
    result = result * 59 + ($data == null ? 43 : $data.hashCode());
    Object $msg = getMsg();
    result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
    Object $path = getPath();
    result = result * 59 + ($path == null ? 43 : $path.hashCode());
    Object $extra = getExtra();
    result = result * 59 + ($extra == null ? 43 : $extra.hashCode());
    long $timestamp = getTimestamp();
    result = result * 59 + (int) ($timestamp >>> 32 ^ $timestamp);
    return result;
  }

  public R(
      int code,
      T data,
      String msg,
      String path,
      Map<String, Object> extra,
      long timestamp) {
    this.code = code;
    this.data = data;
    this.msg = msg;
    this.path = path;
    this.extra = extra;
    this.timestamp = timestamp;
  }

  public R() {
  }
}

