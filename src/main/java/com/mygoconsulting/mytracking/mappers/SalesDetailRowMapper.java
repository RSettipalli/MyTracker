package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;

@Component("SalesDetailRowMapper")
public class SalesDetailRowMapper implements RowMapper<IMY_MGOL_SO_DETAIL> {

	@Override
	public IMY_MGOL_SO_DETAIL mapRow(ResultSet rs, int rowNum)	throws SQLException {
		IMY_MGOL_SO_DETAIL salesDetail = new IMY_MGOL_SO_DETAIL();
		salesDetail.setORDER_NBR(rs.getString("ORDER_NBR"));
		salesDetail.setORDER_LINE_NBR(rs.getString("ORDER_LINE_NBR"));
		salesDetail.setPRODUCT_NBR(rs.getString("PRODUCT_NBR"));
		salesDetail.setOVERRIDE_PRODUCT(rs.getString("OVERRIDE_PRODUCT"));
		salesDetail.setBASE_UOM(rs.getString("BASE_UOM"));
		salesDetail.setITEM_CAT(rs.getString("ITEM_CAT"));
		salesDetail.setNET_VAL(rs.getString("NET_VAL"));
		salesDetail.setORD_QTY(rs.getString("ORD_QTY"));
		salesDetail.setORD_UOM_DESC(rs.getString("ORD_UOM_DESC"));
		salesDetail.setBASE_UOM_DESC(rs.getString("BASE_UOM_DESC"));
		salesDetail.setBASE_PRICE(rs.getString("BASE_PRICE"));
		return salesDetail;
	}
}
