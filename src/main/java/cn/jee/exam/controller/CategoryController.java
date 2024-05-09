package cn.jee.exam.controller;

import cn.hutool.core.util.StrUtil;
import cn.jee.exam.domain.entity.Category;
import cn.jee.exam.dao.CategoryDao;
import cn.jee.exam.domain.R;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
  @Resource
  private CategoryDao categoryDao;

  @GetMapping
  public R findAll(@RequestParam Integer pageNo,
                   @RequestParam Integer pageSize,
                   @RequestParam(required = false) Integer id,
                   @RequestParam(required = false) String name) {
    Specification<Category> specification = (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (id != null) {
        predicates.add(criteriaBuilder.equal(root.get("id"), id));
      }
      if (StrUtil.isNotBlank(name)) {
        predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }
      Predicate[] pre = new Predicate[predicates.size()];
      return query.where(predicates.toArray(pre)).getRestriction();
    };
    PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.ASC, "id");
    Page<Category> page = categoryDao.findAll(specification, pageRequest);
    return R.success().put("page", page);
  }

  @GetMapping("/{id}")
  public R getReferenceById(@PathVariable Integer id) {
    Category category = categoryDao.getReferenceById(id);
    return R.success().put("category", category);
  }

  @PostMapping
  public R saveAndFlush(@RequestBody Category category) {
    categoryDao.saveAndFlush(category);
    return R.success();
  }

  @DeleteMapping
  public R delete(@RequestParam List<Integer> ids) {
    categoryDao.deleteAllByIdInBatch(ids);
    return R.success();
  }
}
