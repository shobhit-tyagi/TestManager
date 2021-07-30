package com.tm.web.controller;

import com.tm.model.ui.NotificationBean;
import com.tm.service.NotificationService;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tmNotification")
public class NotificationController {
	
	private final NotificationService notificationService;

	@RequestMapping(method = RequestMethod.GET, value="/getUserNotifications")
	public List<NotificationBean> getUserNotifications(@RequestParam("userId") final long userId) throws InternalApplicationException {
		try {
			return  notificationService.getUserNotifications(userId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/markNotificationsAsRead")
	public String markNotificationsAsRead(@RequestBody final List<Integer> notificationIdList) throws InternalApplicationException {
		try {
			return notificationService.markNotificationsAsRead(notificationIdList);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/deleteUserNotifications")
	public void deleteUserNotifications(@RequestBody final List<Integer> notificationIdList) throws InternalApplicationException {
		try {
			notificationService.deleteUserNotifications(notificationIdList);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/archiveUserNotifications")
	public void archiveUserNotifications(@RequestBody final List<Integer> notificationIdList) throws InternalApplicationException {
		try {
			notificationService.archiveUserNotifications(notificationIdList);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/unArchiveUserNotifications")
	public void unArchiveUserNotifications(@RequestBody final List<Integer> notificationIdList) throws InternalApplicationException {
		try {
			notificationService.unArchiveUserNotifications(notificationIdList);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
}
