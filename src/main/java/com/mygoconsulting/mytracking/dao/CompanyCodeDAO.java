package com.mygoconsulting.mytracking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;

@Component("CompanyCode")
public class CompanyCodeDAO extends BaseDAO implements IDAO {

	@Autowired
	@Qualifier("ShipPointRowMapper")
	RowMapper<IMY_SHIP_POINT> shipPointRowMapper;
	
	@Autowired
	@Qualifier("CompanyCodeMapper")
	RowMapper<IMY_COMPANY> companyCodeRowMapper;

	public void persist(IDOC doc) {

		List<IMY_COMPANY> iMyCompanies = doc.getIMY_COMPANY();
		
		// create company code
		createCompany(iMyCompanies);
		
		// create Ship Point
		createShipPoint(iMyCompanies);
	}

	private void createCompany(List<IMY_COMPANY> iMyCompanies) {
		for(IMY_COMPANY iMyCompany :iMyCompanies){
			String selectQuery = new String("select * from COMPANY_CODE where BUKRS= ?");
			Object[] selectParams = { iMyCompany.getBUKRS() };
			if (!isExists(selectQuery, selectParams, companyCodeRowMapper)) {
				System.out.println("Company Code inserting");
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
				System.out.println("Company Code updating");
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
	}

	private void createShipPoint(List<IMY_COMPANY> iMyCompanies) {
		for(IMY_COMPANY iMyCompany :iMyCompanies){
			List<IMY_SHIP_POINT> imyShipPoints = iMyCompany.getIMY_SHIP_POINT();
			for(IMY_SHIP_POINT imyShipPoint:imyShipPoints){			
				String selectQuery = new String(
						"select * from SHIP_POINT where SHIP_POINT= ? and BUKRS = ?");
				Object[] selectParams = { imyShipPoint.getSHIP_POINT(), imyShipPoint.getBUKRS()  };
				if (!isExists(selectQuery, selectParams, shipPointRowMapper)) {
					System.out.println("ShipPoint inserting");

					String sqlQuery = new String(
							"insert into SHIP_POINT (SHIP_POINT, ADDRESS1, ADDRESS2, CITY, COUNTRY, ZIP, PHONE, FAX, LANGUAGE, BUKRS) Values(?,?,?,?, ?,?,?,?, ?,?)");
					Object[] params = { imyShipPoint.getSHIP_POINT(),
							imyShipPoint.getADDRESS1(), imyShipPoint.getADDRESS2(),
							imyShipPoint.getCITY(), imyShipPoint.getCOUNTRY(),
							imyShipPoint.getZIP(), imyShipPoint.getPHONE(),
							imyShipPoint.getFAX(), imyShipPoint.getLANGUAGE(), imyShipPoint.getBUKRS() };
					insertOrUpdate(sqlQuery, params);
				} else {
					System.out.println("Ship point updating");
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

	}

}
