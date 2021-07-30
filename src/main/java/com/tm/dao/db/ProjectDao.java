package com.tm.dao.db;

import com.tm.dao.entity.TmProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends CrudRepository<TmProject, Long> {

	List<TmProject> findByProjOwner(Long id);
}
