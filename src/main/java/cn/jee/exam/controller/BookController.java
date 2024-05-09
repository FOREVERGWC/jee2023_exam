package cn.jee.exam.controller;

import cn.hutool.core.util.StrUtil;
import cn.jee.exam.domain.entity.Book;
import cn.jee.exam.domain.entity.Category;
import cn.jee.exam.dao.BookDao;
import cn.jee.exam.domain.R;
import cn.jee.exam.domain.dto.BookDto;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
  @Resource
  private BookDao bookDao;

  @GetMapping
  public R findAll(@RequestParam Integer pageNo,
                   @RequestParam Integer pageSize,
                   @RequestParam(required = false) Integer id,
                   @RequestParam(required = false) String name,
                   @RequestParam(required = false) Double price,
                   @RequestParam(required = false) String profile,
                   @RequestParam(required = false) Integer categoryId) {
    Specification<Book> specification = (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (id != null) {
        predicates.add(criteriaBuilder.equal(root.get("id"), id));
      }
      if (StrUtil.isNotBlank(name)) {
        predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }
      if (price != null) {
        predicates.add(criteriaBuilder.equal(root.get("price"), price));
      }
      if (StrUtil.isNotBlank(profile)) {
        predicates.add(criteriaBuilder.like(root.get("profile"), "%" + profile + "%"));
      }
      Join<Book, Category> join = root.join("category", JoinType.INNER);
      if (categoryId != null) {
        predicates.add(criteriaBuilder.equal(join.get("id"), categoryId));
      }
      Predicate[] pre = new Predicate[predicates.size()];
      return query.where(predicates.toArray(pre)).getRestriction();
    };
    PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.ASC, "id");
    Page<Book> page = bookDao.findAll(specification, pageRequest);
    return R.success().put("page", page);
  }

  @GetMapping("/{id}")
  public R getReferenceById(@PathVariable Integer id) {
    Book book = bookDao.getReferenceById(id);
    return R.success().put("book", book);
  }

  @PostMapping
  public R saveAndFlush(@Valid @RequestBody BookDto dto) {
    Book book = new Book();
    BeanUtils.copyProperties(dto, book);
    book.setCategory(new Category(dto.getCategoryId()));
    bookDao.saveAndFlush(book);
    return R.success();
  }

  @DeleteMapping
  public R delete(@RequestParam List<Integer> ids) {
    bookDao.deleteAllByIdInBatch(ids);
    return R.success();
  }
}
