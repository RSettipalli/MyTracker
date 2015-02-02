package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER_COMMENT;

@Component("SalesHeaderRowMapper")
public class SalesHeaderRowMapper implements RowMapper<IMY_MGOL_SO_HEADER> {

	@Override
	public IMY_MGOL_SO_HEADER mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MGOL_SO_HEADER salesHeader = new IMY_MGOL_SO_HEADER();
		
		salesHeader.setORDER_TYPE(rs.getString("ORDER_TYPE"));
		salesHeader.setSOLD_FROM_COMPANY_CD(rs.getString("SOLD_FROM_COMPANY_CD"));
		salesHeader.setSOLD_TO_COMPANY_CD(rs.getString("SOLD_TO_COMPANY_CD"));
		salesHeader.setSHIP_TO_COMPANY_CD(rs.getString("SHIP_TO_COMPANY_CD"));
		salesHeader.setORDER_NBR(rs.getString("ORDER_NBR"));
		salesHeader.setORDER_NBR_VER(rs.getString("ORDER_NBR_VER"));
		salesHeader.setORDER_PLANT_CD(rs.getString("ORDER_PLANT_CD"));
		salesHeader.setORDER_STATUS_CD(rs.getString("ORDER_STATUS_CD"));
		salesHeader.setCUSTOMER_PO(rs.getString("CUSTOMER_PO"));
		salesHeader.setEND_USER(rs.getString("END_USER"));
		salesHeader.setEND_USER_COMPANY_CD(rs.getString("END_USER_COMPANY_CD"));

		salesHeader.setOVERRIDE_COMPANY_NAME(rs.getString("OVERRIDE_COMPANY_NAME"));
		salesHeader.setOVERRIDE_ADDRESS1(rs.getString("OVERRIDE_ADDRESS1"));
		salesHeader.setOVERRIDE_ADDRESS2(rs.getString("OVERRIDE_ADDRESS2"));
		salesHeader.setOVERRIDE_CITY(rs.getString("OVERRIDE_CITY"));
		salesHeader.setOVERRIDE_STATE(rs.getString("OVERRIDE_STATE"));
		salesHeader.setOVERRIDE_ZIP(rs.getString("OVERRIDE_ZIP"));		
			
		// set the header
		List<IMY_MGOL_SO_HEADER_COMMENT> headerCommentList = new ArrayList<IMY_MGOL_SO_HEADER_COMMENT>();
		for(IMY_MGOL_SO_HEADER_COMMENT headerComment : headerCommentList ){
			headerComment.setORDER_NBR(rs.getString("ORDER_NUM_SO_HEADER"));
			salesHeader.getIMY_MGOL_SO_HEADER_COMMENT().add(headerComment);
		}
		
		return salesHeader;
	}

}
