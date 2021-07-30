package com.tm.service.impl;

import com.tm.dao.db.*;
import com.tm.dao.entity.*;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.model.ui.*;
import com.tm.service.*;
import com.tm.service.helper.IssueHistoryHelper;
import com.tm.service.helper.IssueSubscribeHelper;
import com.tm.service.helper.NotificationHelper;
import com.tm.service.helper.NotificationVariableHelper;
import com.tm.util.exceptions.DaoException;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IssueServiceImpl extends DtoAssemblerFacadeImpl<TmIssue, IssueBean> implements IssueService {

	private final UserService userService;
	private final IssueAttachmentService issueAttachmentService;
	private final IssueCommentService issueCommentService;
	private final IssueHistoryService issueHistoryService;
	private final IssueSubscribeService issueSubscribeService;
	private final IssueDao issueDao;
	private final IssueCommentDao issueCommentDao;
	private final IssueAttachmentDao issueAttachmentDao;
	private final IssueSubscribeDao issueSubscribeDao;
	private final IssueHistoryDao issueHistoryDao;
	private final UserDao userDao;
	private final NotificationDao notificationDao;
	private final NotificationVariableDao notificationVariableDao;
	
	@Override
	public List<IssueBean> getIssuesByModule(final long moduleId) throws DaoException, DtoConversionException {
		final List<TmIssue> issueEntityList = issueDao.findByModId(moduleId);
		final List<IssueBean> issueList = new ArrayList<IssueBean>();
		for(final TmIssue issueEntity : issueEntityList) {
			final IssueBean issueBean = toBean(issueEntity);
			issueBean.setIssAttachments(issueAttachmentService.getIssueAttachments(issueBean.getId()));
			issueBean.setIssComments(issueCommentService.getIssueComments(issueBean.getId()));
			issueBean.setIssHistory(issueHistoryService.getIssueHistory(issueBean.getId()));
			issueBean.setIssSubscribe(issueSubscribeService.getIssueSubscribers(issueBean.getId()));
			issueBean.setUserIdString(userDao.findById(issueBean.getUserId()).get().getUserId());
			issueBean.setIssOwnerString(userDao.findById(issueBean.getIssOwner()).get().getUserId());
			issueBean.setIssStatusCoordinates(IssueStatus.valueOf(issueBean.getIssStatus()).getCoordinates());
			issueList.add(issueBean);
		}
		return issueList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IssueBean addIssueToModule(final IssueBean issueBean) throws DtoConversionException, DaoException {
		final long userAssigneeId = userService.getUserByUserId(issueBean.getUserIdString()).getId();
		issueBean.setUserId(userAssigneeId);
		issueBean.setIssStatus(IssueStatus.OPEN.toString());
		
		// ISSUE ENTITY
		final TmIssue issueEntity = toEntity(issueBean);
		final TmUserInfo issueOwnerEntity = userDao.findById(issueBean.getIssOwner()).get();
		final String issueOwner = issueOwnerEntity.getUserId();
		
		// ISSUE COMMENT ENTITY
		TmIssueComment issueCommentEntity = null;
		if(issueBean.getIssComments() != null && !issueBean.getIssComments().isEmpty()) {
			issueCommentEntity = issueCommentService.toEntity(issueBean.getIssComments().get(0));
		}
		
		// ISSUE ATTACHMENT ENTITY
		List<TmIssueAttachment> issueAttachmentEntityList = null;
		if(issueBean.getIssAttachments() != null) {
			issueAttachmentEntityList = new ArrayList<TmIssueAttachment>();
			for(final IssueAttachmentBean issueAttachmentBean : issueBean.getIssAttachments()) {
				issueAttachmentBean.setUserId(issueBean.getIssOwner());
				issueAttachmentEntityList.add(issueAttachmentService.toEntity(issueAttachmentBean));
			}
		}
		
		// ISSUE HISTORY ENTITY
		final TmIssueHistory issueHistoryEntity = IssueHistoryHelper.getHistoryEntity(issueOwnerEntity, IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_CREATE);
		
		// ISSUE SUBSCRIBE ENTITY
		final List<TmIssueSubscribe> issueSubscribeEntityList = IssueSubscribeHelper.getIssueSubscribeEntity(issueBean.getIssOwner(), issueBean.getUserId());
		
		// ISSUE BEAN
		final IssueBean returnIssueBean = toBean(issueDao.save(issueEntity));
		issueCommentEntity.setIssId(issueEntity.getId());
		issueCommentDao.save(issueCommentEntity);
		if (issueAttachmentEntityList != null) {
			for(TmIssueAttachment issueAttachmentEntity : issueAttachmentEntityList) {
				issueAttachmentEntity.setIssId(issueEntity.getId());
				issueAttachmentDao.save(issueAttachmentEntity);
			}
		}
		if (issueSubscribeEntityList != null) {
			for(TmIssueSubscribe issueSubscribeEntity : issueSubscribeEntityList) {
				issueSubscribeEntity.setIssId(issueEntity.getId());
				issueSubscribeDao.save(issueSubscribeEntity);
			}
		}
		issueHistoryEntity.setIssId(issueEntity.getId());
		issueHistoryDao.save(issueHistoryEntity);
		returnIssueBean.setUserIdString(issueBean.getUserIdString());
		returnIssueBean.setIssOwnerString(issueOwner);
		returnIssueBean.setIssStatusCoordinates(IssueStatus.valueOf(returnIssueBean.getIssStatus()).getCoordinates());
		
		// ISSUE COMMENT BEAN
		final List<IssueCommentBean> issueCommentBeanList = new ArrayList<IssueCommentBean>();
		final IssueCommentBean issueCommentBean = issueCommentService.toBean(issueCommentEntity);
		issueCommentBean.setUserIdString(issueOwner);
		issueCommentBeanList.add(issueCommentBean);
		
		// ISSUE HISTORY BEAN
		final IssueHistoryBean issueHistoryBean = issueHistoryService.toBean(issueHistoryEntity);
		issueHistoryBean.setHisContent(issueHistoryService.getHistoryProperties().getProperty(IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_CREATE.getValue(), IssueHistoryService.DEFAULT_MESSG));
		final List<IssueHistoryBean> issueHistoryBeanList = new ArrayList<IssueHistoryBean>();
		issueHistoryBeanList.add(issueHistoryBean);
		
		// ISSUE SUBSCRIBE BEAN
		final List<IssueSubscribeBean> issueSubscribeBeanList = new ArrayList<IssueSubscribeBean>();
		for(final TmIssueSubscribe issueSubscribeEntity : issueSubscribeEntityList) {
			final IssueSubscribeBean issueSubscribeBean = issueSubscribeService.toBean(issueSubscribeEntity);
			if(issueSubscribeBean.getUserId() == issueBean.getIssOwner()) {
				issueSubscribeBean.setUserIdString(issueOwner);
			} else {
				issueSubscribeBean.setUserIdString(issueBean.getUserIdString());
			}
			issueSubscribeBeanList.add(issueSubscribeBean);
		}
		
		returnIssueBean.setIssComments(issueCommentBeanList);
		returnIssueBean.setIssAttachments(issueBean.getIssAttachments());
		returnIssueBean.setIssHistory(issueHistoryBeanList);
		returnIssueBean.setIssSubscribe(issueSubscribeBeanList);
		
		//SEND NOTIFICATION
		final TmNotification notificationEntity = NotificationHelper.getNotificationEntity(userAssigneeId, NotificationHelper.NotificationType.NOTIFICATION_ISSUE_CREATE);
		
		final Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("issueId", returnIssueBean.getId());
		variableMap.put("issueOwner", returnIssueBean.getIssOwnerString());
		
		final List<TmNotificationVariable> notificationVariableEntityList = NotificationVariableHelper.getNotificationVariables(variableMap);
		notificationDao.save(notificationEntity);
		for(final TmNotificationVariable notificationVariableEntity : notificationVariableEntityList) {
			notificationVariableEntity.setNotId(notificationEntity.getId());
			notificationVariableDao.save(notificationVariableEntity);
		}
		return returnIssueBean;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IssueHistoryBean acceptIssue(final long issueId) throws DaoException, DtoConversionException {
		final TmIssue issueEntity = issueDao.findById(issueId).get();
		issueEntity.setIssStatus(IssueStatus.ACCEPTED.toString());
		final TmUserInfo issueOwnerEntity = userDao.findById(issueEntity.getIssOwner()).get();
		TmIssueHistory issueHistoryEntity = IssueHistoryHelper.getHistoryEntity(issueOwnerEntity, IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_ACCEPT);
		issueHistoryEntity.setIssId(issueId);
		issueDao.save(issueEntity);
		issueHistoryEntity = issueHistoryDao.save(issueHistoryEntity);
		final IssueHistoryBean issueHistoryBean = issueHistoryService.toBean(issueHistoryEntity);
		issueHistoryBean.setHisContent(issueHistoryService.getHistoryProperties()
				.getProperty(IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_ACCEPT.getValue(), IssueHistoryService.DEFAULT_MESSG));
		return issueHistoryBean;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IssueHistoryBean rejectIssue(final long issueId) throws DaoException, DtoConversionException {
		final TmIssue issueEntity = issueDao.findById(issueId).get();
		issueEntity.setIssStatus(IssueStatus.REJECTED.toString());
		final TmUserInfo issueOwnerEntity = userDao.findById(issueEntity.getIssOwner()).get();
		TmIssueHistory issueHistoryEntity = IssueHistoryHelper.getHistoryEntity(issueOwnerEntity, IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_REJECT);
		issueHistoryEntity.setIssId(issueId);
		issueDao.save(issueEntity);
		issueHistoryEntity = issueHistoryDao.save(issueHistoryEntity);
		final IssueHistoryBean issueHistoryBean = issueHistoryService.toBean(issueHistoryEntity);
		issueHistoryBean.setHisContent(issueHistoryService.getHistoryProperties()
				.getProperty(IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_REJECT.getValue(), IssueHistoryService.DEFAULT_MESSG));
		return issueHistoryBean;
	}
	
	@Override
	public void reAssignIssue(final long issueId, final String newUserId) throws DtoConversionException {
		final TmIssue issueEntity = issueDao.findById(issueId).get();
		issueEntity.setIssStatus(IssueStatus.OPEN.toString());
		issueEntity.setUserId(userService.getUserByUserId(newUserId).getId());
		issueDao.save(issueEntity);
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IssueHistoryBean reOpenIssue(final long issueId) throws DaoException, DtoConversionException {
		final TmIssue issueEntity = issueDao.findById(issueId).get();
		issueEntity.setIssStatus(IssueStatus.REOPENED.toString());
		final TmUserInfo issueOwnerEntity = userDao.findById(issueEntity.getIssOwner()).get();
		TmIssueHistory issueHistoryEntity = IssueHistoryHelper.getHistoryEntity(issueOwnerEntity, IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_REOPEN);
		issueHistoryEntity.setIssId(issueId);
		issueDao.save(issueEntity);
		issueHistoryEntity = issueHistoryDao.save(issueHistoryEntity);
		final IssueHistoryBean issueHistoryBean = issueHistoryService.toBean(issueHistoryEntity);
		issueHistoryBean.setHisContent(issueHistoryService.getHistoryProperties()
				.getProperty(IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_REOPEN.getValue(), IssueHistoryService.DEFAULT_MESSG));
		return issueHistoryBean;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IssueHistoryBean fixIssue(final long issueId) throws DaoException, DtoConversionException {
		final TmIssue issueEntity = issueDao.findById(issueId).get();
		issueEntity.setIssStatus(IssueStatus.FIXED.toString());
		final TmUserInfo issueOwnerEntity = userDao.findById(issueEntity.getIssOwner()).get();
		TmIssueHistory issueHistoryEntity = IssueHistoryHelper.getHistoryEntity(issueOwnerEntity, IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_MARK_FIXED);
		issueHistoryEntity.setIssId(issueId);
		issueDao.save(issueEntity);
		issueHistoryEntity = issueHistoryDao.save(issueHistoryEntity);
		final IssueHistoryBean issueHistoryBean = issueHistoryService.toBean(issueHistoryEntity);
		issueHistoryBean.setHisContent(issueHistoryService.getHistoryProperties()
				.getProperty(IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_MARK_FIXED.getValue(), IssueHistoryService.DEFAULT_MESSG));
		return issueHistoryBean;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IssueHistoryBean completeIssue(final long issueId) throws DaoException, DtoConversionException {
		final TmIssue issueEntity = issueDao.findById(issueId).get();
		issueEntity.setIssStatus(IssueStatus.COMPLETED.toString());
		final TmUserInfo issueOwnerEntity = userDao.findById(issueEntity.getIssOwner()).get();
		TmIssueHistory issueHistoryEntity = IssueHistoryHelper.getHistoryEntity(issueOwnerEntity, IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_COMPLETE);
		issueHistoryEntity.setIssId(issueId);
		issueDao.save(issueEntity);
		issueHistoryEntity = issueHistoryDao.save(issueHistoryEntity);
		final IssueHistoryBean issueHistoryBean = issueHistoryService.toBean(issueHistoryEntity);
		issueHistoryBean.setHisContent(issueHistoryService.getHistoryProperties()
				.getProperty(IssueHistoryHelper.IssueHistoryType.ISSUE_HISTORY_COMPLETE.getValue(), IssueHistoryService.DEFAULT_MESSG));
		return issueHistoryBean;
	}
	
	@Override
	public void removeIssue(final long issueId) {
		final TmIssue issueEntity = issueDao.findById(issueId).get();
		issueEntity.setIssStatus(IssueStatus.CANCELLED.toString());
		issueEntity.setVisible(false);
		issueDao.save(issueEntity);
	}
}
