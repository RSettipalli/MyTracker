package com.mygoconsulting.mytracking.batch.util;

import org.apache.log4j.Logger;

public class MygoLogger {
	
	private static final char LOG_SEPARATOR = '|';
		
		Logger logger;
		
		public MygoLogger(String name) {
			this.logger = Logger.getLogger(name);
		}
		
		private StackTraceElement findCaller() {
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			// find first line of stack trace that is not part of this logger class
			for (int i = 2; i < st.length; i++) {
				if (st[i].getClassName() != st[1].getClassName())
					return st[i];
			}
			throw new RuntimeException("Could not get stack trace");
		}
		
		private String formatMessage(Object message) {
			StackTraceElement caller = findCaller();
			return caller.getClassName() + LOG_SEPARATOR + caller.getMethodName() + ":" + caller.getLineNumber()
					+ LOG_SEPARATOR + message;
		}
		
		public boolean isTraceEnabled() {
			return logger.isTraceEnabled();
		}
		
		public void trace(Object message) {
			if (logger.isTraceEnabled())
				logger.trace(formatMessage(message));
		}
		
		public void trace(Object message, Throwable t) {
			if (logger.isTraceEnabled())
				logger.trace(formatMessage(message), t);
		}
		
		public boolean isDebugEnabled() {
			return logger.isDebugEnabled();
		}

		public void debug(Object message) {
			if (logger.isDebugEnabled())
				logger.debug(formatMessage(message));
		}

		public void debug(Object message, Throwable t) {
			if (logger.isDebugEnabled())
				logger.debug(formatMessage(message), t);
		}
		
		public boolean isInfoEnabled() {
			return logger.isInfoEnabled();
		}

		public void info(Object message) {
			if (logger.isInfoEnabled())
				logger.info(formatMessage(message));
		}

		public void info(Object message, Throwable t) {
			if (logger.isInfoEnabled())
				logger.info(formatMessage(message), t);
		}
		
		public void warn(Object message) {
			logger.warn(formatMessage(message));
		}

		public void warn(Object message, Throwable t) {
			logger.warn(formatMessage(message), t);
		}

		public void error(Object message) {
			logger.error(formatMessage(message));
		}

		public void error(Object message, Throwable t) {
			logger.error(formatMessage(message), t);
		}

		public void fatal(Object message) {
			logger.fatal(formatMessage(message));
		}

		public void fatal(Object message, Throwable t) {
			logger.fatal(formatMessage(message), t);
		}
}
	
