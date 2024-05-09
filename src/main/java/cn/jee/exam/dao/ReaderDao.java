package cn.jee.exam.dao;

import cn.jee.exam.domain.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderDao extends JpaRepository<Reader, Integer>, JpaSpecificationExecutor<Reader> {

}
