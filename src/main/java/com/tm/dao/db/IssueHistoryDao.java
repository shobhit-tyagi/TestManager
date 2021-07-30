package com.tm.dao.db;

import com.tm.dao.entity.TmIssueHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueHistoryDao extends CrudRepository<TmIssueHistory, Long> {

	List<TmIssueHistory> findByIssId(long issueId);

}
