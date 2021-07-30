package com.tm.service.impl;

import com.tm.dao.entity.TmIssueAttachment;
import com.tm.dao.db.IssueAttachmentDao;
import com.tm.dao.db.UserDao;
import com.tm.model.ui.IssueAttachmentBean;
import com.tm.service.IssueAttachmentService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueAttachmentServiceImpl extends DtoAssemblerFacadeImpl<TmIssueAttachment, IssueAttachmentBean> implements IssueAttachmentService {

	private final IssueAttachmentDao issueAttachmentDao;
	private final UserDao userDao;

	@Override
	public List<IssueAttachmentBean> getIssueAttachments(final long issueId) throws DtoConversionException {
		final List<TmIssueAttachment> issueAttachmentEntityList = issueAttachmentDao.findByIssId(issueId);
		final List<IssueAttachmentBean> issueAttachmentBeanList = new ArrayList<IssueAttachmentBean>();
		for(final TmIssueAttachment issueAttachmentEntity : issueAttachmentEntityList) {
			final IssueAttachmentBean issueAttachmentBean = toBean(issueAttachmentEntity);
			issueAttachmentBean.setUserIdString(userDao.findById(issueAttachmentBean.getUserId()).get().getUserId());
			issueAttachmentBeanList.add(issueAttachmentBean);
		}
		return issueAttachmentBeanList;
	}
}
