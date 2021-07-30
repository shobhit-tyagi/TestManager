package com.tm.service;

import com.tm.dao.entity.TmIssue;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.IssueBean;
import com.tm.model.ui.IssueHistoryBean;
import com.tm.util.exceptions.DaoException;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;

public interface IssueService extends DtoAssemblerFacade<TmIssue, IssueBean> {

	List<IssueBean> getIssuesByModule(long moduleId) throws DaoException, DtoConversionException;

	IssueBean addIssueToModule(IssueBean issueBean) throws DtoConversionException, DaoException;

	IssueHistoryBean acceptIssue(long issueId) throws DaoException, DtoConversionException;

	IssueHistoryBean rejectIssue(long issueId) throws DaoException, DtoConversionException;

	void reAssignIssue(long issueId, String newUserId) throws DtoConversionException;

	IssueHistoryBean completeIssue(long issueId) throws DaoException, DtoConversionException;

	void removeIssue(long issueId);

	IssueHistoryBean reOpenIssue(long issueId) throws DaoException, DtoConversionException;

	IssueHistoryBean fixIssue(long issueId) throws DaoException, DtoConversionException;
}
