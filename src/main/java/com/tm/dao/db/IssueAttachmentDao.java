package com.tm.dao.db;

import com.tm.dao.entity.TmIssueAttachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueAttachmentDao extends CrudRepository<TmIssueAttachment, Long> {

	List<TmIssueAttachment> findByIssId(long issueId);

}
