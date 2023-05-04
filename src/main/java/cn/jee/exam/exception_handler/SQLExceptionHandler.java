package cn.jee.exam.exception_handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class SQLExceptionHandler {
  @ExceptionHandler(value = SQLException.class)
  @ResponseBody
  public String handleSQLException(Exception e) {
    return "发生SQL异常\n"+e.getMessage();
  }
}
