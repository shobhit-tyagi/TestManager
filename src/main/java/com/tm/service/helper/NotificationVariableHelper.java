package com.tm.service.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tm.dao.entity.TmNotificationVariable;

public class NotificationVariableHelper {

	public static List<TmNotificationVariable> getNotificationVariables(final Map<String, Object> variableMap) {
		final List<TmNotificationVariable> notificationVariableEntityList = new ArrayList<TmNotificationVariable>();
		TmNotificationVariable notificationVariableEnitity;
		for (final Map.Entry<String, Object> entry : variableMap.entrySet()) {
			notificationVariableEnitity = new TmNotificationVariable();
			notificationVariableEnitity.setNvlKey(entry.getKey());
			notificationVariableEnitity.setNvlVal(String.valueOf(entry.getValue()));
			notificationVariableEntityList.add(notificationVariableEnitity);
		}
		
		return notificationVariableEntityList;
	}
}
