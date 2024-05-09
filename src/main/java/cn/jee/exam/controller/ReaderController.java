package cn.jee.exam.controller;

import cn.hutool.core.util.StrUtil;
import cn.jee.exam.dao.ReaderDao;
import cn.jee.exam.domain.R;
import cn.jee.exam.domain.dto.ReaderDto;
import cn.jee.exam.domain.entity.Book;
import cn.jee.exam.domain.entity.Reader;
import jakarta.annotation.Resource;
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
@RequestMapping("/reader")
public class ReaderController {
  @Resource
  private ReaderDao readerDao;

  @GetMapping
  public R findAll(@RequestParam Integer pageNo,
                   @RequestParam Integer pageSize,
                   @RequestParam(required = false) Integer id,
                   @RequestParam(required = false) String name,
                   @RequestParam(required = false) Integer bookId) {
    Specification<Reader> specification = (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (id != null) {
        predicates.add(criteriaBuilder.equal(root.get("id"), id));
      }
      if (StrUtil.isNotBlank(name)) {
        predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }
//      Join<Reader, Book> join = root.join("book", JoinType.INNER);
//      if (bookId != null) {
//        predicates.add(criteriaBuilder.equal(join.get("id"), bookId));
//      }
      Predicate[] pre = new Predicate[predicates.size()];
      return query.where(predicates.toArray(pre)).getRestriction();
    };
    PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.ASC, "id");
    Page<Reader> page = readerDao.findAll(specification, pageRequest);
    return R.success().put("page", page);
  }

  @GetMapping("/{id}")
  public R getReferenceById(@PathVariable Integer id) {
    Reader reader = readerDao.getReferenceById(id);
    return R.success().put("reader", reader);
  }

  @PostMapping
  public R saveAndFlush(@Valid @RequestBody ReaderDto dto) {
    Reader reader = new Reader();
    BeanUtils.copyProperties(dto, reader);
    reader.setBooks(dto.getBookIds().stream().map(Book::new).toList());
    readerDao.saveAndFlush(reader);
    return R.success();
  }

  @DeleteMapping
  public R delete(@RequestParam List<Integer> ids) {
    readerDao.deleteAllByIdInBatch(ids);
    return R.success();
  }
}
