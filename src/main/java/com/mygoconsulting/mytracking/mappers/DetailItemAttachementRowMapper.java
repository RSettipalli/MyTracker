package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_ITEM_ATTACHM;

@Component("DetailItemAttachementRowMapper")
public class DetailItemAttachementRowMapper implements RowMapper<IMY_MGOL_OD_ITEM_ATTACHM> {

	@Override
	public IMY_MGOL_OD_ITEM_ATTACHM mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_OD_ITEM_ATTACHM iMyDetailItemAttachm = new IMY_MGOL_OD_ITEM_ATTACHM();
		iMyDetailItemAttachm.setDOKAR(rs.getString("DOKAR"));
		iMyDetailItemAttachm.setDOKNR(rs.getString("DOKNR"));
		iMyDetailItemAttachm.setDOKTL(rs.getString("DOKTL"));
		iMyDetailItemAttachm.setDOKVR(rs.getString("DOKVR"));
		iMyDetailItemAttachm.setOBJKY(rs.getString("OBJKY"));
		iMyDetailItemAttachm.setORDER_NBR_OD_DETAIL(rs.getString("ORDER_NBR_OD_DETAIL"));
		iMyDetailItemAttachm.setORDER_LINE_NBR_OD_DETAIL(rs.getString("ORDER_LINE_NBR_OD_DETAIL"));
		return iMyDetailItemAttachm;
	}
	

}
