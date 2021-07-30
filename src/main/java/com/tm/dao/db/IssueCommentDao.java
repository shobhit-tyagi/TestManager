package com.tm.dao.db;

import com.tm.dao.entity.TmIssueComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueCommentDao extends CrudRepository<TmIssueComment, Long> {

	List<TmIssueComment> findByIssId(long issueId);
}
