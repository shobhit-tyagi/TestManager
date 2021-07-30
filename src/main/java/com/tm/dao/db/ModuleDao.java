package com.tm.dao.db;

import com.tm.dao.entity.TmModule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModuleDao extends CrudRepository<TmModule, Long> {

	List<TmModule> findByProjId(long projectId);
}
