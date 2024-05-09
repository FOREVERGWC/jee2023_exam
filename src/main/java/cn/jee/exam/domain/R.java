package cn.jee.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 响应对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {
  @Serial
  private static final long serialVersionUID = -7038250660860376052L;
  /**
   * 响应状态码
   */
  private Integer code;
  /**
   * 响应消息
   */
  private String msg;
  /**
   * 响应体
   */
  private Map<String, Object> data = new HashMap<>();

  public R(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static R success() {
    return new R(HttpStatus.OK.value(), "请求成功！");
  }

  public static R success(String msg) {
    return new R(HttpStatus.OK.value(), msg);
  }

  public static R error() {
    return new R(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求失败！");
  }

  public static R error(String msg) {
    return new R(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
  }

  public static R error(Integer code, String msg) {
    return new R(code, msg);
  }

  public R put(String key, Object value) {
    this.data.put(key, value);
    return this;
  }
}
