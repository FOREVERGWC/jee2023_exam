package cn.jee.exam.dao;

import cn.jee.exam.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
  List<Book> getBooksByCategoryId(Integer categoryId);


}
