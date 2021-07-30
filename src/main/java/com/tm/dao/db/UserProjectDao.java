package com.tm.dao.db;

import com.tm.dao.entity.TmUserProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectDao extends CrudRepository<TmUserProject, Long> {

	List<TmUserProject> findByProjId(long projectId);

}
