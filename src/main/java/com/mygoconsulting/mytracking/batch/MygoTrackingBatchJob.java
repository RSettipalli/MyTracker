package com.mygoconsulting.mytracking.batch;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
@Service
public class MygoTrackingBatchJob {
	private static final MygoLogger LOG = LogFactory.getMygoLogger();
	
	@Autowired
	@Qualifier("syncXml")
	TimerTask syncXmlDataTask;

	@PostConstruct
	public void init() {
		LOG.info("BEGIN");
		LOG.debug("In init:::::syncXmlDataTask: "+syncXmlDataTask);
		Timer timer = new Timer(true);
		timer.schedule(syncXmlDataTask, 0, 30* 60 * 1000);
		LOG.info("END");
	}

	public void cleanUp() {
		LOG.info("BEGIN");
		LOG.debug("MygoTracking job shutdown");
		LOG.info("END");
	}	
}