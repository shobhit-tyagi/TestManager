package com.tm.service;

import com.tm.dao.entity.TmCalendar;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.CalendarBean;
import com.tm.util.date.calendar.beans.Calendar;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;
import java.util.Map;

public interface CalendarService extends DtoAssemblerFacade<TmCalendar, CalendarBean> {

	Calendar createCalendar(int month, int year);

	Map<String, List<CalendarBean>> getCalendar(long userId) throws DtoConversionException;
}
