package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_CUST_BANK;
@Component("CustBankRowMapper")
public class CustBankRowMapper implements RowMapper<IMY_MGOL_CUST_BANK> {

	@Override
	public IMY_MGOL_CUST_BANK mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_CUST_BANK iMYCustomer = new IMY_MGOL_CUST_BANK();
		iMYCustomer.setBANK_ACC(rs.getString("BANK_ACC"));
		iMYCustomer.setBANK_COUNTRY(rs.getString("BANK_COUNTRY"));
		iMYCustomer.setBANK_KEY(rs.getString("BANK_KEY"));
		iMYCustomer.setBANK_TYPE(rs.getString("BANK_TYPE"));
		iMYCustomer.setCUST_NUMBER(rs.getString("CUST_NUMBER"));
		return iMYCustomer;
	}
	
	
}
