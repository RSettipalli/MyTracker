package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;

@Component("InvoiceHeaderRowMapper")
public class InvoiceHeaderRowMapper implements RowMapper<IMY_MGOL_INV_HEADER> {

	@Override
	public IMY_MGOL_INV_HEADER mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MGOL_INV_HEADER invoiceHeader = new IMY_MGOL_INV_HEADER();
		
		invoiceHeader.setSOLD_FROM_COMPANY_CD(rs.getString("SOLD_FROM_COMPANY_CD"));
		invoiceHeader.setSOLD_TO_COMPANY_CD(rs.getString("SOLD_TO_COMPANY_CD"));
		invoiceHeader.setBILL_TO_COMPANY_CD(rs.getString("BILL_TO_COMPANY_CD"));
		invoiceHeader.setINVOI_NBR(rs.getString("INVOI_NBR"));
		invoiceHeader.setORDER_PLANT_CD(rs.getString("ORDER_PLANT_CD"));
		invoiceHeader.setORDER_STATUS_CD(rs.getString("ORDER_STATUS_CD"));
		invoiceHeader.setCUSTOMER_PO(rs.getString("CUSTOMER_PO"));
		invoiceHeader.setEND_USER_COMPANY_CD(rs.getString("END_USER_COMPANY_CD"));
		invoiceHeader.setOVERRIDE_COMPANY_NAME(rs.getString("OVERRIDE_COMPANY_NAME"));
		invoiceHeader.setOVERRIDE_ADDRESS1(rs.getString("OVERRIDE_ADDRESS1"));
		invoiceHeader.setOVERRIDE_ADDRESS2(rs.getString("OVERRIDE_ADDRESS2"));
		invoiceHeader.setOVERRIDE_CITY(rs.getString("OVERRIDE_CITY"));
		invoiceHeader.setOVERRIDE_STATE(rs.getString("OVERRIDE_STATE"));
		invoiceHeader.setOVERRIDE_ZIP(rs.getString("OVERRIDE_ZIP"));
		invoiceHeader.setDOCNUM(rs.getString("DOCNUM"));
		invoiceHeader.setORDER_REF_NUM(rs.getString("ORDER_REF_NUM"));
		invoiceHeader.setBILLED_PRICE(rs.getString("BILLED_PRICE"));
		invoiceHeader.setCURRENCY(rs.getString("CURRENCY"));
		invoiceHeader.setCREATE_DATE(rs.getString("CREATE_DATE"));
		
		return invoiceHeader;
	}

}
