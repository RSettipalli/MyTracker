package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;

@Component("DeliveryDetailRowMapper")
public class DeliveryDetailRowMapper implements
		RowMapper<IMY_MGOL_OD_DETAIL> {

	@Override
	public IMY_MGOL_OD_DETAIL mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MGOL_OD_DETAIL deliveryDetail = new IMY_MGOL_OD_DETAIL();

		deliveryDetail.setORDER_NBR(rs.getString("ORDER_NBR"));
		deliveryDetail.setORDER_LINE_NBR(rs.getString("ORDER_LINE_NBR"));
		deliveryDetail.setPRODUCT_NBR(rs.getString("PRODUCT_NBR"));
		deliveryDetail.setOVERRIDE_PRODUCT(rs.getString("OVERRIDE_PRODUCT"));
		deliveryDetail.setBASE_UOM(rs.getString("BASE_UOM"));
		deliveryDetail.setITEM_CAT(rs.getString("ITEM_CAT"));
		deliveryDetail.setNET_VAL(rs.getString("NET_VAL"));
		deliveryDetail.setORD_QTY(rs.getString("ORD_QTY"));
		deliveryDetail.setORD_UOM_DESC(rs.getString("ORD_UOM_DESC"));
		deliveryDetail.setBASE_UOM_DESC(rs.getString("BASE_UOM_DESC"));
		deliveryDetail.setBASE_PRICE(rs.getString("BASE_PRICE"));
		return deliveryDetail;
	}

}
