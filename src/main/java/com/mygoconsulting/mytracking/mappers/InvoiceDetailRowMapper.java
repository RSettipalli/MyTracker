package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;

@Component("InvoiceDetailRowMapper")
public class InvoiceDetailRowMapper implements
		RowMapper<IMY_MGOL_INV_DETAIL> {

	@Override
	public IMY_MGOL_INV_DETAIL mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MGOL_INV_DETAIL invoiceDetail = new IMY_MGOL_INV_DETAIL();

		invoiceDetail.setORDER_NBR(rs.getString("ORDER_NBR"));
		invoiceDetail.setORDER_LINE_NBR(rs.getString("ORDER_LINE_NBR"));
		invoiceDetail.setPRODUCT_NBR(rs.getString("PRODUCT_NBR"));
		invoiceDetail.setOVERRIDE_PRODUCT(rs.getString("OVERRIDE_PRODUCT"));
		invoiceDetail.setBASE_UOM(rs.getString("BASE_UOM"));
		invoiceDetail.setITEM_CAT(rs.getString("ITEM_CAT"));
		invoiceDetail.setNET_VAL(rs.getString("NET_VAL"));		
		//TODO need to check if foreign key relation is required or not
		return invoiceDetail;
	}

}
