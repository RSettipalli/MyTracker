package com.mygoconsulting.mytracking.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.CompanyCodeDAO;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;

@Component("CompanyManager")
public class CompanyManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	private IMY_COMPANY imyCompany = null;
	@Autowired
	@Qualifier("CompanyCode")
	private CompanyCodeDAO companyCodeDao;
	
	public IMY_COMPANY getCompanyInfo(String companyCode) {
		logger.debug("BEGIN");
		logger.debug("Company Code "+ companyCode);
		if(companyCode != null)
			imyCompany = companyCodeDao.getIMyCompanyByBUKRS(companyCode);
		logger.debug("END");
		return imyCompany;
	}
	
	public Map<String,List<String>> getCompanyCodes() {
		logger.debug("BEGIN");
		Map<String,List<String>> companyCodesMap = companyCodeDao.getALLBUKRS();
		logger.debug("END");
		return companyCodesMap;
	}
	
	public List<IMY_SHIP_POINT> getShipPoints(String companyCode){
		logger.debug("BEGIN");
		List<IMY_SHIP_POINT> shipPoints = companyCodeDao.getShipPointsList(companyCode);
		logger.debug("END");
		return shipPoints;
	}
}