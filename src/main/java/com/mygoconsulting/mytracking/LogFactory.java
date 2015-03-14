package com.mygoconsulting.mytracking;

import com.mygoconsulting.mytracking.batch.util.MygoLogger;

public class LogFactory {
	
public static final String LOGGER_NAME = "MyTracking";
	
private static final MygoLogger LOG = new MygoLogger(LOGGER_NAME);
	
	public static MygoLogger getMygoLogger() {
		return LOG;
	}
	
}
