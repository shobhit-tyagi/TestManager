package com.tm.util.date.calendar.beans;

import java.util.List;

public class Day {
	
	private String day;
	private List<Date> dates;
	
	public String getDay() {
		return day;
	}
	
	public void setDay(final String day) {
		this.day = day;
	}
	
	public List<Date> getDates() {
		return dates;
	}
	
	public void setDates(final List<Date> dates) {
		this.dates = dates;
	}
}
