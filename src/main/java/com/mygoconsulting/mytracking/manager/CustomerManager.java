package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUSTOMER;

@Component("CustomerManager")
public class CustomerManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	List<IMY_MGOL_CUSTOMER> imyCustomer;
	public List<IMY_MGOL_CUSTOMER> getCustomerInfo() {
		logger.debug("BEGIN");
		IDOC idoc = new IDOC();
		imyCustomer = idoc.getIMY_MGOL_CUSTOMER();
		logger.debug("END");
		return imyCustomer;
	}
}