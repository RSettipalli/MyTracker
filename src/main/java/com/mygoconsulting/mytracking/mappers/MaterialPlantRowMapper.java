package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MAT_STORAGE_DETIALS;
import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;

@Component("MaterialPlantRowMapper")
public class MaterialPlantRowMapper implements RowMapper<IMY_MAT_WERKS> {

	@Override
	public IMY_MAT_WERKS mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MAT_WERKS materialPalnt = new IMY_MAT_WERKS();
		
		IMY_MAT_STORAGE_DETIALS storageDetails = new IMY_MAT_STORAGE_DETIALS();
		storageDetails.setSTO_LOCATION(rs.getString("STO_LOCATION"));
		materialPalnt.setIMY_MAT_STORAGE_DETIALS(storageDetails);
		materialPalnt.setMAINT_STATUS(rs.getString("MAINT_STATUS"));
		materialPalnt.setMRP_CONT(rs.getString("MRP_CONT"));
		materialPalnt.setMRP_TYPE(rs.getString("MRP_TYPE"));
		materialPalnt.setPLANT(rs.getString("PLANT_CD"));
		materialPalnt.setSEGMENT(rs.getString("STO_LOCATION"));
		return materialPalnt;
	}

}
