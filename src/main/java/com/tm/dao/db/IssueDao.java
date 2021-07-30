package com.tm.dao.db;

import com.tm.dao.entity.TmIssue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueDao extends CrudRepository<TmIssue, Long> {

	List<TmIssue> findByModId(long moduleId);
}
