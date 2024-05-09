package cn.jee.exam;


import cn.jee.exam.dao.BookDao;
import cn.jee.exam.dao.ReaderDao;
import cn.jee.exam.domain.entity.Book;
import cn.jee.exam.domain.entity.Reader;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Proxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

@Proxy(lazy = false)
@SpringBootTest
class Jee2023ExamApplicationTests {
  @Autowired
  EntityManager entityManager;
  @Resource
  private ReaderDao readerDao;
  @Resource
  private BookDao bookDao;

  @Test
  void contextLoads() {
  }

  @Test
//  @Transactional
//  @Rollback(value = false)
  void testSaveEntity() {
    Book book = bookDao.getReferenceById(1);
    Reader reader = readerDao.getReferenceById(1);
    book.setReaders(List.of(reader));
    bookDao.save(book);
//    Book book = bookDao.getReferenceById(1);
//    Reader reader = readerDao.getReferenceById(1);
//    reader.setBooks(List.of(book));
//    readerDao.save(reader);
  }
}
