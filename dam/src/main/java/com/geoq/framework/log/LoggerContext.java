package com.geoq.framework.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerContext
{
	private static LoggerContext logContext;
	
	private  Logger logger;
	
	private LoggerContext() 
	{
		logger =  LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	}
	
	public static LoggerContext instance()
	{
		if(logContext==null)
			logContext = new LoggerContext();
		return logContext;
	}
	
	public void error(String message)
	{
		logger.error(message);
	}
	
	public void error(Exception ex)
	{
		logger.error("", ex);
	}
	
	public void warn(String message)
	{
		logger.warn(message);
	}
	
}
