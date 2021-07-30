package com.tm.service.impl;

import com.tm.dao.entity.TmCalendar;
import com.tm.dao.db.CalendarDao;
import com.tm.model.ui.CalendarBean;
import com.tm.service.CalendarService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.date.calendar.CalendarUtils;
import com.tm.util.date.calendar.beans.Calendar;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CalendarServiceImpl extends DtoAssemblerFacadeImpl<TmCalendar, CalendarBean> implements CalendarService {

	private final CalendarDao calendarDao;

	@Override
	public Calendar createCalendar(final int month, final int year) {
		return CalendarUtils.createCalendar(year, month);
	}

	@Override
	public Map<String, List<CalendarBean>> getCalendar(final long userId) throws DtoConversionException {
		final List<TmCalendar> calendarEntityList = calendarDao.findByUserId(userId);
		final Map<String, List<CalendarBean>> calendarMap = new HashMap<String, List<CalendarBean>>();
		for(final TmCalendar calendarEntity : calendarEntityList) {
			final Timestamp eventDate = calendarEntity.getEventDate();
			final java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTimeInMillis(eventDate.getTime());
			final StringBuffer temp = new StringBuffer(String.valueOf(cal.get(java.util.Calendar.DATE)));
			
			final int month = cal.get(java.util.Calendar.MONTH) + 1;
			if(month < 10) {
				temp.append("0");
			}
			temp.append(month);
			temp.append(cal.get(java.util.Calendar.YEAR));
			List<CalendarBean> tempCalendarBeanList = null;
			final String key = temp.toString();
			if(calendarMap.containsKey(key)) {
				tempCalendarBeanList = calendarMap.get(key);
			} else {
				tempCalendarBeanList = new ArrayList<CalendarBean>();
			}
			
			tempCalendarBeanList.add(toBean(calendarEntity));
			calendarMap.put(key, tempCalendarBeanList);
		}
		
		return calendarMap;
	}
}
