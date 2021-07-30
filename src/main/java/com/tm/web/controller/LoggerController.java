package com.tm.web.controller;

import com.tm.service.LoggerService;
import com.tm.util.exceptions.FileLoadException;
import com.tm.util.file.PropertyUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/tmLogger")
public class LoggerController {
	
//	private final LoggerService loggerService;
	
//	private static String isLoggingEnabled;
//	static {
//		try {
//			isLoggingEnabled = PropertyUtils.loadProperties("application.properties").getProperty("tm.logs.enabled");
//		} catch (final FileLoadException e) {
//			e.printStackTrace();
//		}
//	}
	
//	@RequestMapping(method = RequestMethod.GET, value="/getLogs")
//	public @ResponseBody String getLogs() throws InternalApplicationException {
//		if(isLoggingEnabled.equals("0"))
//			return JsonUtils.toJson(new ArrayList<LoggerBean>());
//		return JsonUtils.toJson(loggerService.getLogsByLevel(LogLevel.ALL));
//	}
}
