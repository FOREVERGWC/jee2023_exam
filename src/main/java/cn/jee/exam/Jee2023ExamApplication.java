package cn.jee.exam;

import org.hibernate.annotations.Proxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Proxy(lazy = false)
public class Jee2023ExamApplication {

  public static void main(String[] args) {
    SpringApplication.run(Jee2023ExamApplication.class, args);
  }

}
