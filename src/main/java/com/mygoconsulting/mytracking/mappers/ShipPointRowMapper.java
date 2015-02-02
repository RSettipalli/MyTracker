package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;
@Component("ShipPointRowMapper")
public class ShipPointRowMapper implements RowMapper<IMY_SHIP_POINT> {

	@Override
	public IMY_SHIP_POINT mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_SHIP_POINT iMyShipPoint = new IMY_SHIP_POINT();
		iMyShipPoint.setADDRESS1(rs.getString("ADDRESS1"));
		iMyShipPoint.setADDRESS2(rs.getString("ADDRESS2"));
		iMyShipPoint.setBUKRS(rs.getString("BUKRS"));
		iMyShipPoint.setCITY(rs.getString("CITY"));
		iMyShipPoint.setCOUNTRY(rs.getString("COUNTRY"));
		iMyShipPoint.setFAX(rs.getString("FAX"));
		iMyShipPoint.setLANGUAGE(rs.getString("LANGUAGE"));
		iMyShipPoint.setPHONE(rs.getString("PHONE"));
		//iMyShipPoint.setSEGMENT(rs.getString("SEGMENT"));
		iMyShipPoint.setSHIP_POINT(rs.getString("SHIP_POINT"));
		iMyShipPoint.setZIP(rs.getString("ZIP"));
		return iMyShipPoint;
	}
	
	
}
