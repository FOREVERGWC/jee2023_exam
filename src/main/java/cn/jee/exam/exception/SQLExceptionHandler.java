package cn.jee.exam.exception;

import cn.jee.exam.domain.R;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class SQLExceptionHandler {
  @ExceptionHandler(value = SQLException.class)
  public R handleSQLException(SQLException e) {
    return R.error("发生SQL异常：" + e.getMessage());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public R MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
    String message = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
    return R.error(message);
  }
}
