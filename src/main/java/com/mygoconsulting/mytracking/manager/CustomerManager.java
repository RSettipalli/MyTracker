package com.mygoconsulting.mytracking.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.CustomerDAO;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUSTOMER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUST_BANK;

@Component("CustomerManager")
public class CustomerManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	@Qualifier("Customer")
	private CustomerDAO customerDao;
	
	IMY_MGOL_CUSTOMER imyCustomer;
	public IMY_MGOL_CUSTOMER getCustomerInfo(String kunnr) {
		logger.debug("BEGIN");
		imyCustomer = customerDao.getIMyCustomerByKUNNR(kunnr);
		logger.debug("END");
		return imyCustomer;
	}
	
	public IMY_MGOL_CUST_BANK getCustomerBank(String kunnr){
		logger.debug("BEGIN");
		IMY_MGOL_CUST_BANK customerBank = customerDao.getCustBank(kunnr);
		logger.debug("END");
		return customerBank;
	}
	
	public Map<String,List<String>> getCustomerCodes() {
		logger.debug("BEGIN");
		Map<String,List<String>> customerMap = customerDao.getALLKUNNR();
		logger.debug("END");
		return customerMap;
	}
}