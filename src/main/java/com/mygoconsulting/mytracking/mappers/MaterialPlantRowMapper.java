package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;

@Component("MaterialPlantRowMapper")
public class MaterialPlantRowMapper implements RowMapper<IMY_MAT_WERKS> {

	@Override
	public IMY_MAT_WERKS mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		IMY_MAT_WERKS materialPlant = new IMY_MAT_WERKS();
//		IMY_MAT_STORAGE_DETIALS storageDetails = new IMY_MAT_STORAGE_DETIALS();
//		storageDetails.setSTO_LOCATION(rs.getString("MATERIAL_CD_REF"));
//		materialPlant.setIMY_MAT_STORAGE_DETIALS(storageDetails);
		materialPlant.setMAINT_STATUS(rs.getString("MAINT_STATUS"));
		materialPlant.setMRP_CONT(rs.getString("MRP_CONT"));
		materialPlant.setMRP_TYPE(rs.getString("MRP_TYPE"));
		materialPlant.setPLANT(rs.getString("PLANT_CD"));
		//materialPlant.set
		//materialPlant.setSEGMENT(rs.getString("STO_LOCATION"));
		return materialPlant;
	}

}
