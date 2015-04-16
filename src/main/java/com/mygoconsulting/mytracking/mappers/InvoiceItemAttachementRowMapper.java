package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_ITEM_ATMT;

@Component("InvoiceItemAttachementRowMapper")
public class InvoiceItemAttachementRowMapper implements RowMapper<IMY_MGOL_INV_ITEM_ATMT> {

	@Override
	public IMY_MGOL_INV_ITEM_ATMT mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_INV_ITEM_ATMT iMyInvItemAtmt = new IMY_MGOL_INV_ITEM_ATMT();
		iMyInvItemAtmt.setDOKAR(rs.getString("DOKAR"));
		iMyInvItemAtmt.setDOKNR(rs.getString("DOKNR"));
		iMyInvItemAtmt.setDOKTL(rs.getString("DOKTL"));
		iMyInvItemAtmt.setDOKVR(rs.getString("DOKVR"));
		iMyInvItemAtmt.setOBJKY(rs.getString("OBJKY"));
		iMyInvItemAtmt.setINVOI_NUM_SO(rs.getString("INVOI_NUM_SO"));
		iMyInvItemAtmt.setINVOI_ORD_NUM(rs.getString("INVOI_ORD_NUM"));
		return iMyInvItemAtmt;
	}

}
