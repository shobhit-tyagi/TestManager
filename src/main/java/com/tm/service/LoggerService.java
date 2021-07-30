package com.tm.service;


public interface LoggerService {

	void info(String logger, String message);
	
	void debug(String logger, String message);
	
	void trace(String logger, String message);
	
	void warn(String logger, String message);
	
	void error(Exception exception);
	
	void severe(Exception exception);
}
