package cn.jee.exam.dao;

import cn.jee.exam.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryDao extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {
}
