package com.mygoconsulting.mytracking.manager;

import java.util.List;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUSTOMER;

public class CustomerManager {
	List<IMY_MGOL_CUSTOMER> imyCustomer;
	public List<IMY_MGOL_CUSTOMER> getCustomerInfo() {
		IDOC idoc = new IDOC();
		imyCustomer = idoc.getIMY_MGOL_CUSTOMER();
		  return imyCustomer;
	}
}