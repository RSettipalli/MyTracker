package com.mygoconsulting.mytracking.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.EDI_DC40;
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
	
	@Autowired
	@Qualifier("EDIDC40Mapper")
	RowMapper<EDI_DC40> ediDC40Mapper;
	
	public void persist(IDOC doc) {
		System.out.println("BEGIN");		 
		
		List<IMY_COMPANY> iMyCompanies = doc.getIMY_COMPANY();
		
		createEDI_DC40(doc.getEDI_DC40());
		
		// create company code
		createCompany(iMyCompanies,doc.getEDI_DC40().getDOCNUM());
		
		// create Ship Point
		createShipPoint(iMyCompanies);
		System.out.println("END");
	}

	private void createEDI_DC40(EDI_DC40 ediDC40){
		if(ediDC40 != null){
			String selectQuery = new String("select * from EDI_DC40 where DOCNUM = ?");
			Object[] selectParams = { ediDC40.getDOCNUM() };
			if(!isExists(selectQuery,selectParams,ediDC40Mapper)){
				String sqlQuery = new String(
						"insert into EDI_DC40 (TABNAM, MANDT, DOCNUM, DOCREL, STATUS, DIRECT, OUTMOD, IDOCTYP, MESTYP,"
						+ " SNDPOR, SNDPRT, SNDPRN, RCVPOR, RCVPRT, RCVPRN, CREDAT, CRETIM, SERIAL) values ( "
						+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				Object[] params = { ediDC40.getTABNAM(), ediDC40.getMANDT(), ediDC40.getDOCNUM(), ediDC40.getDOCREL(),
						ediDC40.getSTATUS(), ediDC40.getDIRECT(), ediDC40.getOUTMOD(), ediDC40.getIDOCTYP(), 
						ediDC40.getMESTYP(), ediDC40.getSNDPOR(), ediDC40.getSNDPRT(), ediDC40.getSNDPRN(), 
						ediDC40.getRCVPOR(), ediDC40.getRCVPRT(), ediDC40.getRCVPRN(), ediDC40.getCREDAT(),
						ediDC40.getCRETIM(),ediDC40.getSERIAL()};
				insertOrUpdate(sqlQuery, params);
			}
		}
	}
	
	private void createCompany(List<IMY_COMPANY> iMyCompanies,String docNum) {
		logger.debug("BEGIN");
		for(IMY_COMPANY iMyCompany :iMyCompanies){
			String selectQuery = new String("select * from COMPANY_CODE where BUKRS= ?");
			Object[] selectParams = { iMyCompany.getBUKRS() };
			if (!isExists(selectQuery, selectParams, companyCodeRowMapper)) {
				logger.debug("Company Code inserting");
				String sqlQuery = new String(
						"insert into COMPANY_CODE (BUKRS, BUTXT, ORT01, WAERS, SPRAS, ADRNR, ADDRESS1, ADDRESS2, "
						+ "COUNTRY, ZIP, PHONE, FAX, DOCNUM) Values(?,?,?,?, ?,?,?,?, ?,?,?,?,?)");
				Object[] params = { iMyCompany.getBUKRS(), iMyCompany.getBUTXT(),
						iMyCompany.getORT01(), iMyCompany.getWAERS(), iMyCompany.getSPRAS(),
						iMyCompany.getADRNR(),iMyCompany.getADDRESS1(),
						iMyCompany.getADDRESS2(), iMyCompany.getCOUNTRY(),
						iMyCompany.getZIP(), iMyCompany.getPHONE(), iMyCompany.getFAX(), docNum };
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
	
	public List<IMY_SHIP_POINT> getShipPointsList(String bukrs){
		logger.debug("BEGIN");
		System.out.println("BUKRS value is "+bukrs);
		String selectQuery = new String("select * from SHIP_POINT where BUKRS= '"+bukrs+"'");
		List<Object> objectsList = null;
		List<IMY_SHIP_POINT> iMyShipPoints = new ArrayList<IMY_SHIP_POINT>();
		if(bukrs != null){
			objectsList = getObjects(selectQuery, shipPointRowMapper);
			if(objectsList != null){
				for(Object obj: objectsList){
					IMY_SHIP_POINT imyShipPoint = (IMY_SHIP_POINT) obj;
					iMyShipPoints.add(imyShipPoint);
				}
			}
		}
		logger.debug("END");
		return iMyShipPoints;
	}
	
	public Map<String,List<String>> getALLBUKRS(){
		logger.debug("BEGIN");
		Map<String,List<String>> companyCodesMap = new HashMap<String,List<String>>();
		List<String> companyCodesList = new ArrayList<String>();
		String selectQuery = new String("select BUKRS from COMPANY_CODE");
		
		List<Map<String, Object>> companyCodesMapList = (List<Map<String, Object>>) get(selectQuery);
		if(!companyCodesMapList.isEmpty()){
			for(int i=0;i<companyCodesMapList.size();i++){
				for(Map.Entry<String, Object> entry: companyCodesMapList.get(i).entrySet()) {
					companyCodesList.add(entry.getValue().toString());
				}
			}
			companyCodesMap.put("BUKRS",companyCodesList);
		}
		
		logger.debug("END");		
		return companyCodesMap;
	}
}
