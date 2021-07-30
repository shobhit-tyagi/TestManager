package com.tm.service;

import com.tm.dao.entity.TmIssueHistory;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.IssueHistoryBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;
import java.util.Properties;

public interface IssueHistoryService extends DtoAssemblerFacade<TmIssueHistory, IssueHistoryBean> {

	static String DEFAULT_MESSG = " updated the issue";
	
	List<IssueHistoryBean> getIssueHistory(long issueId) throws DtoConversionException;

	Properties getHistoryProperties();
}
