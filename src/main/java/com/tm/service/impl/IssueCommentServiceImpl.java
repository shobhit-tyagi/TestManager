package com.tm.service.impl;

import com.tm.dao.entity.TmIssueComment;
import com.tm.dao.db.IssueCommentDao;
import com.tm.dao.db.UserDao;
import com.tm.model.ui.IssueCommentBean;
import com.tm.service.IssueCommentService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueCommentServiceImpl extends DtoAssemblerFacadeImpl<TmIssueComment, IssueCommentBean> implements IssueCommentService {

	private final IssueCommentDao issueCommentDao;
	private final UserDao userDao;

	@Override
	public List<IssueCommentBean> getIssueComments(final long issueId) throws DtoConversionException {
		final List<TmIssueComment> issueCommentEntityList = issueCommentDao.findByIssId(issueId);
		final List<IssueCommentBean> issueCommentBeanList = new ArrayList<IssueCommentBean>();
		for(final TmIssueComment issueCommentEntity : issueCommentEntityList) {
			final IssueCommentBean issueCommentBean = toBean(issueCommentEntity);
			issueCommentBean.setUserIdString(userDao.findById(issueCommentBean.getUserId()).get().getUserId());
			issueCommentBeanList.add(issueCommentBean);
		}
		return issueCommentBeanList;
	}

	@Override
	public IssueCommentBean addCommentToIssue(IssueCommentBean issueCommentBean) throws DtoConversionException {
		final TmIssueComment issueCommentEntity = toEntity(issueCommentBean);
		issueCommentDao.save(issueCommentEntity);
		issueCommentBean = toBean(issueCommentEntity);
		issueCommentBean.setUserIdString(userDao.findById(issueCommentBean.getUserId()).get().getUserId());
		return issueCommentBean;
	}
}
