package com.mygoconsulting.mytracking.batch;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class MygoTrackingBatchJob {
	
	@Autowired
	@Qualifier("syncXml")
	TimerTask syncXmlDataTask;

	@PostConstruct
	public void init() {
		System.out.println("In init:::::syncXmlDataTask: "+syncXmlDataTask);
		//TimerTask syncXmlDataTask = new SyncXMLDataTask();

		Timer timer = new Timer(true);
		timer.schedule(syncXmlDataTask, 0, 30* 60 * 1000);
	}

	public void cleanUp() {
		System.out.println("MygoTracking job shutdown");
	}	
}