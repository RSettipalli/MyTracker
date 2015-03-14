package com.mygoconsulting.mytracking.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;

@Component("CompanyCode")
public class CompanyCodeDAO extends BaseDAO implements IDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	@Autowired
	@Qualifier("ShipPointRowMapper")
	RowMapper<IMY_SHIP_POINT> shipPointRowMapper;
	
	@Autowired
	@Qualifier("CompanyCodeMapper")
	RowMapper<IMY_COMPANY> companyCodeRowMapper;

	public void persist(IDOC doc) {
		logger.debug("BEGIN");
		List<IMY_COMPANY> iMyCompanies = doc.getIMY_COMPANY();
		
		// create company code
		createCompany(iMyCompanies);
		
		// create Ship Point
		createShipPoint(iMyCompanies);
		logger.debug("END");
	}

	private void createCompany(List<IMY_COMPANY> iMyCompanies) {
		logger.debug("BEGIN");
		for(IMY_COMPANY iMyCompany :iMyCompanies){
			String selectQuery = new String("select * from COMPANY_CODE where BUKRS= ?");
			Object[] selectParams = { iMyCompany.getBUKRS() };
			if (!isExists(selectQuery, selectParams, companyCodeRowMapper)) {
				logger.debug("Company Code inserting");
				String sqlQuery = new String(
						"insert into COMPANY_CODE (BUKRS, BUTXT, ORT01, WAERS, SPRAS, ADRNR, ADDRESS1, ADDRESS2, "
						+ "COUNTRY, ZIP, PHONE, FAX) Values(?,?,?,?, ?,?,?,?, ?,?,?,?)");
				Object[] params = { iMyCompany.getBUKRS(), iMyCompany.getBUTXT(),
						iMyCompany.getORT01(), iMyCompany.getWAERS(), iMyCompany.getSPRAS(),
						iMyCompany.getADRNR(),iMyCompany.getADDRESS1(),
						iMyCompany.getADDRESS2(), iMyCompany.getCOUNTRY(),
						iMyCompany.getZIP(), iMyCompany.getPHONE(), iMyCompany.getFAX() };
				insertOrUpdate(sqlQuery, params);
			} else {
				logger.debug("Company Code updating");
				String sqlQuery = new String(
						"update COMPANY_CODE SET BUTXT=?, ORT01=?, WAERS=?, SPRAS=?, "
						+ "ADRNR=?, ADDRESS1=?, ADDRESS2=?, COUNTRY=?, ZIP=?, PHONE=?, FAX=? where BUKRS=?");
				Object[] updateParams = { iMyCompany.getBUTXT(), iMyCompany.getORT01(),
						iMyCompany.getWAERS(), iMyCompany.getSPRAS(), 
						iMyCompany.getADRNR(),iMyCompany.getADDRESS1(),
						iMyCompany.getADDRESS2(), iMyCompany.getCOUNTRY(),
						iMyCompany.getZIP(), iMyCompany.getPHONE(), iMyCompany.getFAX(),
						iMyCompany.getBUKRS() };
				insertOrUpdate(sqlQuery, updateParams);
			}
		}
		logger.debug("END");
	}

	private void createShipPoint(List<IMY_COMPANY> iMyCompanies) {
		logger.debug("BEGIN");
		for(IMY_COMPANY iMyCompany :iMyCompanies){
			List<IMY_SHIP_POINT> imyShipPoints = iMyCompany.getIMY_SHIP_POINT();
			for(IMY_SHIP_POINT imyShipPoint:imyShipPoints){			
				String selectQuery = new String(
						"select * from SHIP_POINT where SHIP_POINT= ? and BUKRS = ?");
				Object[] selectParams = { imyShipPoint.getSHIP_POINT(), imyShipPoint.getBUKRS()  };
				if (!isExists(selectQuery, selectParams, shipPointRowMapper)) {
					logger.debug("ShipPoint inserting");

					String sqlQuery = new String(
							"insert into SHIP_POINT (SHIP_POINT, ADDRESS1, ADDRESS2, CITY, COUNTRY, ZIP, PHONE, FAX, LANGUAGE, BUKRS) Values(?,?,?,?, ?,?,?,?, ?,?)");
					Object[] params = { imyShipPoint.getSHIP_POINT(),
							imyShipPoint.getADDRESS1(), imyShipPoint.getADDRESS2(),
							imyShipPoint.getCITY(), imyShipPoint.getCOUNTRY(),
							imyShipPoint.getZIP(), imyShipPoint.getPHONE(),
							imyShipPoint.getFAX(), imyShipPoint.getLANGUAGE(), imyShipPoint.getBUKRS() };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Ship point updating");
					String sqlQuery = new String(
						"update SHIP_POINT SET ADDRESS1=?, ADDRESS2=?, CITY=?, COUNTRY=?, ZIP=?, PHONE=?, FAX=?, LANGUAGE=? where SHIP_POINT=?");
					Object[] updateParams = { imyShipPoint.getADDRESS1(),
							imyShipPoint.getADDRESS2(), imyShipPoint.getCITY(),
							imyShipPoint.getCOUNTRY(), imyShipPoint.getZIP(),
							imyShipPoint.getPHONE(), imyShipPoint.getFAX(),
							imyShipPoint.getLANGUAGE(),
							imyShipPoint.getSHIP_POINT() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}	
		logger.debug("END");
	}
	
	public IMY_COMPANY getIMyCompanyByBUKRS(String bukrs){
		logger.debug("BEGIN");
		logger.debug("BUKRS value is "+bukrs);
		String selectQuery = new String("select * from COMPANY_CODE where BUKRS= ?");
		Object[] selectParams = { bukrs };
		IMY_COMPANY iMyCompany = (IMY_COMPANY) get(selectQuery, selectParams, companyCodeRowMapper);
		logger.debug("BUKRS value from DB is "+iMyCompany.getBUKRS());
		logger.debug("END");
		return iMyCompany;
	}
	
	public IMY_SHIP_POINT getShipPointsList(String bukrs){
		logger.debug("BEGIN");
		logger.debug("BUKRS value is "+bukrs);
		String selectQuery = new String("select * from SHIP_POINT where BUKRS= ?");
		Object[] selectParams = { bukrs };
		IMY_SHIP_POINT iMyShipPoints = (IMY_SHIP_POINT) get(selectQuery, selectParams, shipPointRowMapper);
		logger.debug("END");
		return iMyShipPoints;
	}
	
	public List<String> getALLBUKRS(){
		logger.debug("BEGIN");
		List<String> companyCodesList = new ArrayList<String>();
		companyCodesList.add("0005");
		companyCodesList.add("0006");
		String selectQuery = new String("select BUKRS from COMPANY_CODE");
		
		 List<Map<String, Object>> companyCodesMapList = (List<Map<String, Object>>) get(selectQuery);
		if(!companyCodesMapList.isEmpty()){
			for(Map.Entry<String, Object> entry: companyCodesMapList.get(0).entrySet()) {
			    System.out.println(entry.getKey() + " : " + entry.getValue().toString());
			}
		}
		
		logger.debug("END");		
		return companyCodesList;
	}
}
