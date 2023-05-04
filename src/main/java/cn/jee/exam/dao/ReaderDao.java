package cn.jee.exam.dao;

import cn.jee.exam.bean.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReaderDao extends JpaRepository<Reader,Integer>, JpaSpecificationExecutor<Reader> {
}
