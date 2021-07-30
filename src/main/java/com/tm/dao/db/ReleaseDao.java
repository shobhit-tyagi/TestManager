package com.tm.dao.db;

import com.tm.dao.entity.TmRelease;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseDao extends CrudRepository<TmRelease, Long> {

	List<TmRelease> findByModId(long moduleId);
}
