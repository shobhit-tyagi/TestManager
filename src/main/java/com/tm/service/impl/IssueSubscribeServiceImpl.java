package com.tm.service.impl;

import com.tm.dao.entity.TmIssueSubscribe;
import com.tm.dao.db.IssueSubscribeDao;
import com.tm.dao.db.UserDao;
import com.tm.model.ui.IssueSubscribeBean;
import com.tm.service.IssueSubscribeService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueSubscribeServiceImpl extends DtoAssemblerFacadeImpl<TmIssueSubscribe, IssueSubscribeBean> implements IssueSubscribeService {

	private final IssueSubscribeDao issueSubscribeDao;
	private final UserDao userDao;

	@Override
	public List<IssueSubscribeBean> getIssueSubscribers(final long issueId) throws DtoConversionException {
		final List<TmIssueSubscribe> issueSubscribeEntityList = issueSubscribeDao.findByIssId(issueId);
		final List<IssueSubscribeBean> issueSubscribeBeanList = new ArrayList<IssueSubscribeBean>();
		for(final TmIssueSubscribe issueSubscribeEntity : issueSubscribeEntityList) {
			final IssueSubscribeBean issueSubscribeBean = toBean(issueSubscribeEntity);
			issueSubscribeBean.setUserIdString(userDao.findById(issueSubscribeBean.getUserId()).get().getUserId());
			issueSubscribeBeanList.add(issueSubscribeBean);
		}
		return issueSubscribeBeanList;
	}

	@Override
	public IssueSubscribeBean addSubscription(final long userId, final long issueId) throws DtoConversionException {
		final TmIssueSubscribe issueSubscribeEntity = new TmIssueSubscribe();
		issueSubscribeEntity.setIssId(issueId);
		issueSubscribeEntity.setUserId(userId);
		issueSubscribeEntity.setSubCreated(new Timestamp(new Date().getTime()));
		issueSubscribeDao.save(issueSubscribeEntity);
		final IssueSubscribeBean issueSubscribeBean = toBean(issueSubscribeEntity);
		issueSubscribeBean.setUserIdString(userDao.findById(issueSubscribeBean.getUserId()).get().getUserId());
		return issueSubscribeBean;
	}

	@Override
	public void removeSubscription(final long id) {
		final TmIssueSubscribe issueSubscribeEntity = issueSubscribeDao.findById(id).get();
		issueSubscribeDao.delete(issueSubscribeEntity);
	}
}
