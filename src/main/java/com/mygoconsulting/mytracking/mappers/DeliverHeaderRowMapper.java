package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;

@Component("DeliveryHeaderRowMapper")
public class DeliverHeaderRowMapper implements RowMapper<IMY_MGOL_OD_HEADER> {

	@Override
	public IMY_MGOL_OD_HEADER mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MGOL_OD_HEADER deliveryHeader = new IMY_MGOL_OD_HEADER();

		deliveryHeader.setSOLD_TO_COMPANY_CD(rs.getString("SOLD_TO_COMPANY_CD"));
		deliveryHeader.setSHIP_TO_COMPANY_CD(rs.getString("SHIP_TO_COMPANY_CD"));
		deliveryHeader.setDELVI_NBR(rs.getString("DELVI_NBR"));
		deliveryHeader.setORDER_PLANT_CD(rs.getString("ORDER_PLANT_CD"));
		deliveryHeader.setORDER_STATUS_CD(rs.getString("ORDER_STATUS_CD"));
		deliveryHeader.setCUSTOMER_PO(rs.getString("CUSTOMER_PO"));
		deliveryHeader.setEND_USER(rs.getString("END_USER"));
		deliveryHeader.setEND_USER_COMPANY_CD(rs.getString("END_USER_COMPANY_CD"));

		deliveryHeader.setOVERRIDE_COMPANY_NAME(rs.getString("OVERRIDE_COMPANY_NAME"));
		deliveryHeader.setOVERRIDE_ADDRESS1(rs.getString("OVERRIDE_ADDRESS1"));
		deliveryHeader.setOVERRIDE_ADDRESS2(rs.getString("OVERRIDE_ADDRESS2"));
		deliveryHeader.setOVERRIDE_CITY(rs.getString("OVERRIDE_CITY"));
		deliveryHeader.setOVERRIDE_STATE(rs.getString("OVERRIDE_STATE"));
		deliveryHeader.setOVERRIDE_ZIP(rs.getString("OVERRIDE_ZIP"));		
		deliveryHeader.setORDER_REF_NUM(rs.getString("ORDER_REF_NUM"));
				
		return deliveryHeader;
	}

}
