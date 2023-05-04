package cn.jee.exam;

import cn.jee.exam.bean.Book;
import cn.jee.exam.bean.Category;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Proxy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
@Proxy(lazy = false)
@SpringBootTest
class Jee2023ExamApplicationTests {
  @Resource
  EntityManager entityManager;

  @Test
  void contextLoads() {
  }

  @Test
  @Transactional
  @Rollback(value = false)
  void testSaveEntity(){
    Category category = new Category("科幻");
    Book book = new Book("三体", 10.0, "科幻小说琉璃看空间阿萨德", "/image/1");
    book.setCategory(category);
    category.getBooks().add(book);

    entityManager.persist(category);
  }
}
