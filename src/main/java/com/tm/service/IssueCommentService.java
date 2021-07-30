package com.tm.service;

import com.tm.dao.entity.TmIssueComment;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.IssueCommentBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;

public interface IssueCommentService extends DtoAssemblerFacade<TmIssueComment, IssueCommentBean> {

	List<IssueCommentBean> getIssueComments(long issueId) throws DtoConversionException;

	IssueCommentBean addCommentToIssue(IssueCommentBean issueCommentBean) throws DtoConversionException;
}
