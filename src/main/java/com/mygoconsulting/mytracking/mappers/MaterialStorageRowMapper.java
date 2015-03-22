package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MAT_STORAGE_DETIALS;

@Component("StorageDetailsRowMapper")
public class MaterialStorageRowMapper implements RowMapper<IMY_MAT_STORAGE_DETIALS> {

	@Override
	public IMY_MAT_STORAGE_DETIALS mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MAT_STORAGE_DETIALS storageDetails = new IMY_MAT_STORAGE_DETIALS();
		storageDetails.setMAINT_STATUS(rs.getString("MAINT_STATUS"));
		storageDetails.setSTO_LOCATION(rs.getString("STO_LOCATION"));
		storageDetails.setSTOC_IN_QLTY_INS(rs.getString("STOC_IN_QLTY_INS"));
		return storageDetails;
	}

}
