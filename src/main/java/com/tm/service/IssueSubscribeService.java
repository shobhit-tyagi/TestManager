package com.tm.service;

import com.tm.dao.entity.TmIssueSubscribe;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.IssueSubscribeBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;

public interface IssueSubscribeService extends DtoAssemblerFacade<TmIssueSubscribe, IssueSubscribeBean> {

	List<IssueSubscribeBean> getIssueSubscribers(long id) throws DtoConversionException;

	IssueSubscribeBean addSubscription(long userId, long issueId) throws DtoConversionException;

	void removeSubscription(long id);
}
