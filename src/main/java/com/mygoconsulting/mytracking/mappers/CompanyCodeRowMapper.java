package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_COMPANY;

@Component("CompanyCodeMapper")
public class CompanyCodeRowMapper implements RowMapper<IMY_COMPANY> {

	@Override
	public IMY_COMPANY mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_COMPANY iMYCompany = new IMY_COMPANY();
		iMYCompany.setBUKRS(rs.getString("BUKRS"));
		iMYCompany.setBUTXT(rs.getString("BUTXT"));
		iMYCompany.setORT01(rs.getString("ORT01"));
		iMYCompany.setWAERS(rs.getString("WAERS"));
		iMYCompany.setSPRAS(rs.getString("SPRAS"));
		iMYCompany.setADRNR(rs.getString("ADRNR"));
		iMYCompany.setADDRESS1(rs.getString("ADDRESS1"));
		iMYCompany.setADDRESS2(rs.getString("ADDRESS2"));
		iMYCompany.setCOUNTRY(rs.getString("COUNTRY"));
		iMYCompany.setFAX(rs.getString("FAX"));
		iMYCompany.setPHONE(rs.getString("PHONE"));
		iMYCompany.setZIP(rs.getString("ZIP"));
		return iMYCompany;
	}

}