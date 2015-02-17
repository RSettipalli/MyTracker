package com.mygoconsulting.mytracking.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.dao.CompanyCodeDAO;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;

@Component("CompanyManager")
public class CompanyManager {
	private IMY_COMPANY imyCompany = null;
	@Autowired
	@Qualifier("CompanyCode")
	private CompanyCodeDAO companyCodeDao;
	public CompanyManager(){
		
	}
	
	public IMY_COMPANY getCompanyInfo(String companyCode) {
		System.out.println("Company Code "+ companyCode);
		imyCompany = new IMY_COMPANY();
		imyCompany = companyCodeDao.getIMyCompanyByBUKRS(companyCode);
		return imyCompany;
	}
	
	public IMY_SHIP_POINT getShipPoints(String companyCode){
		IMY_SHIP_POINT shipPoints = companyCodeDao.getShipPointsList(companyCode);
		return shipPoints;
	}
}