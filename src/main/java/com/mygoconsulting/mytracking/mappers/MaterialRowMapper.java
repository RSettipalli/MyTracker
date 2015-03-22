package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;
import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;

@Component("MaterialRowMapper")
public class MaterialRowMapper implements RowMapper<IMY_MAT_ONLINE> {

	@Override
	public IMY_MAT_ONLINE mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		
		IMY_MAT_ONLINE material = new IMY_MAT_ONLINE();
		IMY_MAT_WERKS materialPlant = new IMY_MAT_WERKS();
		materialPlant.setPLANT(rs.getString("PLANT_CD"));
		material.setIMY_MAT_WERKS(materialPlant);

		material.setBOM(rs.getString("BOM"));
		material.setGROSS_WEIGHT(rs.getString("GROSS_WEIGHT"));
		material.setMAT_DESC(rs.getString("MAT_DESC"));
		material.setMATERIAL(rs.getString("MATERIAL_CD"));
		material.setMAT_TYPE(rs.getString("MAT_TYPE"));
		material.setMATERIAL_GROUP(rs.getString("MATERIAL_GROUP"));
		material.setNET_WEIGHT(rs.getString("NET_WEIGHT"));
		//material.setSEGMENT(rs.getString("SEGMENT"));
		material.setSTOCK(rs.getString("STOCK"));
		material.setUOM(rs.getString("UOM"));
		
		return material;
	}

}
