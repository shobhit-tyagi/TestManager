package com.tm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tm.dao.entity.TmNotification;
import com.tm.dao.entity.TmNotificationVariable;
import com.tm.dao.db.NotificationDao;
import com.tm.dao.db.NotificationVariableDao;
import com.tm.service.helper.NotificationHelper;
import com.tm.model.ui.NotificationBean;
import com.tm.service.NotificationService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl extends DtoAssemblerFacadeImpl<TmNotification, NotificationBean> implements NotificationService {

	private final NotificationDao notificationDao;
	private final NotificationVariableDao notificationVariableDao;

	@Override
	public List<NotificationBean> getUserNotifications(final long userId) throws DtoConversionException {
		final List<TmNotification> notificationEntityList = notificationDao.findByUserId(userId);
		final List<NotificationBean> notificationBeanList = new ArrayList<NotificationBean>();
		for(final TmNotification notificationEntity : notificationEntityList) {
			NotificationBean notificationBean = toBean(notificationEntity);
			final List<TmNotificationVariable> notificationVariableEntityList = notificationVariableDao.findByNotId(notificationBean.getId());
			notificationBean = NotificationHelper.formatNotification(notificationBean, notificationVariableEntityList);
			notificationBeanList.add(notificationBean);
		}
		return notificationBeanList;
	}
	
	@Override
	public void deleteUserNotifications(final List<Integer> notificationIdList) {
		notificationDao.deleteAllById(notificationIdList.stream()
				.map(id -> (long)id)
				.collect(Collectors.toList()));
	}

	@Override
	public String markNotificationsAsRead(final List<Integer> notificationIdList) {
		String result = "Fail";
		for(final int notificationId : notificationIdList) {
			final TmNotification notificationEntity = notificationDao.findById((long) notificationId).get();
			if(notificationEntity.isNotIsUnread()) {
				notificationEntity.setNotIsUnread(false);
				notificationDao.save(notificationEntity);
				if(result.equals("Fail")) {
					result = "Success";
				}
			}
		}
		
		return result;
	}

	@Override
	public void archiveUserNotifications(final List<Integer> notificationIdList) {
		for(final int notificationId : notificationIdList) {
			final TmNotification notificationEntity = notificationDao.findById((long) notificationId).get();
			notificationEntity.setVisible(false);
			notificationDao.save(notificationEntity);
		}
	}
	
	@Override
	public void unArchiveUserNotifications(final List<Integer> notificationIdList) {
		for(final int notificationId : notificationIdList) {
			final TmNotification notificationEntity = notificationDao.findById((long) notificationId).get();
			notificationEntity.setVisible(true);
			notificationDao.save(notificationEntity);
		}
	}
}
