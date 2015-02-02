package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_ITEM_ATTACHM;

@Component("SalesDetailItemAttachementRowMapper")
public class SalesDetailItemAttachementRowMapper implements RowMapper<IMY_MGOL_SO_ITEM_ATTACHM> {

	@Override
	public IMY_MGOL_SO_ITEM_ATTACHM mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_SO_ITEM_ATTACHM iMySalesItemAttachement = new IMY_MGOL_SO_ITEM_ATTACHM();
		iMySalesItemAttachement.setDOKAR(rs.getString("DOKAR"));
		iMySalesItemAttachement.setDOKNR(rs.getString("DOKNR"));
		iMySalesItemAttachement.setDOKTL(rs.getString("DOKTL"));
		iMySalesItemAttachement.setDOKVR(rs.getString("DOKVR"));
		iMySalesItemAttachement.setOBJKY(rs.getString("OBJKY"));		
		return iMySalesItemAttachement;
	}

}
