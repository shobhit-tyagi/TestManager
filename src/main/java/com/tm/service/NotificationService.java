package com.tm.service;

import com.tm.dao.entity.TmNotification;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.NotificationBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;


public interface NotificationService extends DtoAssemblerFacade<TmNotification, NotificationBean> {

	List<NotificationBean> getUserNotifications(long userId) throws DtoConversionException;

	void deleteUserNotifications(List<Integer> notificationIdList);

	String markNotificationsAsRead(List<Integer> notificationIdList);

	void archiveUserNotifications(List<Integer> notificationIdList);

	void unArchiveUserNotifications(List<Integer> notificationIdList);
}
