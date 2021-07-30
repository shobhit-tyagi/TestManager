package com.tm.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.model.ui.CalendarBean;
import com.tm.service.CalendarService;
import com.tm.util.date.calendar.beans.Calendar;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/tmCalendar")
public class 	CalendarController {
	
	private final CalendarService calendarService;

	@RequestMapping(method = RequestMethod.GET, value="/createCalendar")
	public Calendar createCalendar(@RequestParam("month") final int month, @RequestParam("year") final int year) throws InternalApplicationException, JsonProcessingException {
		try {
			return calendarService.createCalendar(month, year);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getCalendar")
	public Map<String, List<CalendarBean>> getCalendar(@RequestParam("userId") final long userId) throws InternalApplicationException {
		Map<String, List<CalendarBean>> calendarBeanMap = null;
		try {
			return calendarService.getCalendar(userId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
}
