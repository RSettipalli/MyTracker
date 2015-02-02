package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;

@Service
public class CompanyManager {
	List<IMY_COMPANY> imyCompanyList;
	public List<IMY_COMPANY> getCompanyInfo() {
		 IDOC idoc = new IDOC();  
		 imyCompanyList = idoc.getIMY_COMPANY();			
		  return imyCompanyList;
	}
}