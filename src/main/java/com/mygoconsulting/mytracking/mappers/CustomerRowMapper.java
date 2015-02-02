package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_CUSTOMER;
@Component("CustomerRowMapper")
public class CustomerRowMapper implements RowMapper<IMY_MGOL_CUSTOMER> {

	@Override
	public IMY_MGOL_CUSTOMER mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_CUSTOMER iMYCustomer = new IMY_MGOL_CUSTOMER();
		iMYCustomer.setCITY(rs.getString("CITY"));
		iMYCustomer.setCOUNTRY(rs.getString("COUNTRY"));
		iMYCustomer.setFAX(rs.getString("FAX"));		
		iMYCustomer.setKUNNR(rs.getString("KUNNR"));
		iMYCustomer.setNAME1(rs.getString("NAME1"));
		iMYCustomer.setPOSTL_COD1(rs.getString("POSTL_COD1"));
		iMYCustomer.setREGION(rs.getString("REGION"));
		//iMYCustomer.setSEGMENT(rs.getString("SEGMENT")); //TODO : Check this once Raheem
		iMYCustomer.setSTR_SUPPL3(rs.getString("STR_SUPPL3"));
		iMYCustomer.setSTREET(rs.getString("STREET"));
		iMYCustomer.setTELEPHONE(rs.getString("TELEPHONE"));
		return iMYCustomer;
	}
	
	
}
