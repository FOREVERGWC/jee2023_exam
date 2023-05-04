package cn.jee.exam.dao;

import cn.jee.exam.bean.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
  List<Book> getBooksByCategoryId(Integer categoryId);

  @Modifying
  @Transactional
  @Query("update Book set coverImageUrl=:url where id=:bookId")
  Integer upDateCoverImageUrl(Integer bookId, String url);
}
