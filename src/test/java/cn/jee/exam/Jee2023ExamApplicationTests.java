package cn.jee.exam;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Proxy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
@Proxy(lazy = false)
@SpringBootTest
class Jee2023ExamApplicationTests {
  @Autowired
  EntityManager entityManager;

  @Test
  void contextLoads() {
  }

  @Test
  @Transactional
  @Rollback(value = false)
  void testSaveEntity(){

  }
}
