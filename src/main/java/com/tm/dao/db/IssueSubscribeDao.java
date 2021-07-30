package com.tm.dao.db;

import com.tm.dao.entity.TmIssueSubscribe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueSubscribeDao extends CrudRepository<TmIssueSubscribe, Long> {

	List<TmIssueSubscribe> findByIssId(long issueId);
}
