package com.mygoconsulting.mytracking.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;
import com.mygoconsulting.mytracking.model.IMY_MAT_STORAGE_DETIALS;
import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;
import com.mygoconsulting.mytracking.model.MaterialWrapper;

@Component("Material")
public class MaterialDAO extends BaseDAO implements IDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	@Autowired
	@Qualifier("StorageDetailsRowMapper")
	RowMapper<IMY_MAT_STORAGE_DETIALS> storageMapper;

	@Autowired
	@Qualifier("MaterialPlantRowMapper")
	RowMapper<IMY_MAT_WERKS> materialPlantMapper;

	@Autowired
	@Qualifier("MaterialRowMapper")
	RowMapper<IMY_MAT_ONLINE> materialMapper;

	public void persist(IDOC doc) {
		logger.debug("BEGIN");
		List<IMY_MAT_ONLINE> materialList = doc.getIMY_MAT_ONLINE_List();

		for (IMY_MAT_ONLINE material : materialList) {

			// create material Storage details
			createMaterial(material);

			// create material Plant details
			createPlant(material);

			// create material Storage details
			createStorageDetails(material.getIMY_MAT_WERKS());

		}
		logger.debug("END");
	}

	private void createMaterial(IMY_MAT_ONLINE material) {
		logger.debug("BEGIN");
		String selectQuery = new String(
				"select * from MATERIAL where MATERIAL_CD= ?");
		Object[] selectParams = { material.getMATERIAL() };
		if (!isExists(selectQuery, selectParams, materialMapper)) {
			logger.debug("Material inserting");
			String sqlQuery = new String(
					"insert into MATERIAL (MATERIAL_CD, MAT_DESC, UOM, STOCK, BOM, MAT_TYPE, GROSS_WEIGHT, NET_WEIGHT, MATERIAL_GROUP) Values(?,?, ?,?, ?, ?,?,?,?)");
			Object[] params = { material.getMATERIAL(), material.getMAT_DESC(),
					material.getUOM(), material.getSTOCK(), material.getBOM(),
					material.getMAT_TYPE(), material.getGROSS_WEIGHT(),
					material.getNET_WEIGHT(), material.getMATERIAL_GROUP() };
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Material updating");
			String sqlQuery = new String(
					"update MATERIAL SET MAT_DESC=?, UOM=?, STOCK=?, BOM=?, MAT_TYPE=?, GROSS_WEIGHT=?, NET_WEIGHT=?, MATERIAL_GROUP=? where MATERIAL_CD=?");
			Object[] updateParams = { material.getMATERIAL(),
					material.getMAT_DESC(), material.getUOM(),
					material.getSTOCK(), material.getBOM(),
					material.getMAT_TYPE(), material.getGROSS_WEIGHT(),
					material.getNET_WEIGHT(), material.getMATERIAL_GROUP() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		logger.debug("END");
	}

	private void createPlant(IMY_MAT_ONLINE material) {
		logger.debug("BEGIN");
		IMY_MAT_WERKS materialPlant = material.getIMY_MAT_WERKS();
		if(materialPlant != null){
		String selectQuery = new String(
				"select * from MATERIAL_PLANT where PLANT_CD= ?");
		Object[] selectParams = { materialPlant.getPLANT() };
		if (!isExists(selectQuery, selectParams, materialPlantMapper)) {
			logger.debug("Material Plant inserting");

			String sqlQuery = new String(
					"insert into MATERIAL_PLANT (PLANT_CD, MAINT_STATUS, MRP_TYPE,MRP_CONT, MATERIAL_CD_REF) Values(?,?,?, ?, ?)");
			Object[] params = { materialPlant.getPLANT(),
					materialPlant.getMAINT_STATUS(),
					materialPlant.getMRP_TYPE(), materialPlant.getMRP_CONT(),
					material.getMATERIAL() };
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Material Plant updating");
			String sqlQuery = new String(
					"update MATERIAL_PLANT SET MAINT_STATUS=?, MRP_TYPE=?, MRP_CONT=?, MATERIAL_CD_REF=?  where PLANT_CD=? ");
			Object[] updateParams = { materialPlant.getMAINT_STATUS(),
					materialPlant.getMRP_TYPE(), materialPlant.getMRP_CONT(),
					material.getMATERIAL(), materialPlant.getPLANT() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		}
		logger.debug("END");
	}

	private void createStorageDetails(IMY_MAT_WERKS materialPlant) {
		logger.debug("BEGIN");
		if(materialPlant != null){
		IMY_MAT_STORAGE_DETIALS storageDetails = materialPlant
				.getIMY_MAT_STORAGE_DETIALS();
		if (storageDetails != null) {
			String selectQuery = new String(
					"select * from MATERIAL_STORAGE where STO_LOCATION= ?");
			Object[] selectParams = { storageDetails.getSTO_LOCATION() };
			if (!isExists(selectQuery, selectParams, storageMapper)) {
				logger.debug("Material Storage details inserting");

				String sqlQuery = new String(
						"insert into MATERIAL_STORAGE (STO_LOCATION, MAINT_STATUS, STOC_IN_QLTY_INS, PLANT_CD_REF ) Values(?,?,?, ?)");
				Object[] params = { storageDetails.getSTO_LOCATION(),
						storageDetails.getMAINT_STATUS(),
						storageDetails.getSTOC_IN_QLTY_INS(),
						materialPlant.getPLANT() };
				insertOrUpdate(sqlQuery, params);
			} else {
				logger.debug("Material Storage details updating");
				String sqlQuery = new String(
						"update MATERIAL_STORAGE set  MAINT_STATUS=?, STOC_IN_QLTY_INS=?, PLANT_CD_REF=? where STO_LOCATION=? ");
				Object[] updateParams = { storageDetails.getMAINT_STATUS(),
						storageDetails.getSTOC_IN_QLTY_INS(),
						storageDetails.getSTO_LOCATION(),
						materialPlant.getPLANT() };
				insertOrUpdate(sqlQuery, updateParams);
			}
		}
		}
		logger.debug("END");

	}

	public MaterialWrapper getAllMaterialDetails(String material_cd) {

		String selectQuery = new String(
				" select * from material m,material_plant mp, material_storage ms where m.plant_cd = mp.plant_cd and mp.sto_location = ms.sto_location and m.material_cd="
						+ material_cd);
		// List<Object> objList = jdbcTemplateObject.queryForObject(selectQuery,
		// new Object[]{material_cd},new UserMapper());

		MaterialWrapper matWrap = new MaterialWrapper();

		return matWrap;
	}

	public List<IMY_MAT_ONLINE> getMaterialDetails() {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL");
		List<Object> objList = super.getObjects(selectQuery, materialMapper);
		// IMY_MAT_ONLINE iMyMaterials = (IMY_MAT_ONLINE) get(selectQuery,
		// materialMapper);
		List<IMY_MAT_ONLINE> iMyMaterials = new ArrayList<IMY_MAT_ONLINE>(objList.size());
		for (Object obj : objList) {
			IMY_MAT_ONLINE matOnline = (IMY_MAT_ONLINE) obj;
			iMyMaterials.add(matOnline);
		}
		logger.debug("END");
		return iMyMaterials;
	}

	
	public IMY_MAT_ONLINE getMaterialDetails(String material_cd) {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL  where MATERIAL_CD = '"+material_cd+"'");
		 IMY_MAT_ONLINE iMyMaterials = (IMY_MAT_ONLINE) get(selectQuery, materialMapper);
		logger.debug("END");
		return iMyMaterials;
	}
	
	
	public List<IMY_MAT_WERKS> getMaterialPlantDetails() {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL_PLANT");
		List<Object> objList = super.getObjects(selectQuery,
				materialPlantMapper);
		// IMY_MAT_WERKS iMyMaterialPlant = (IMY_MAT_WERKS) get(selectQuery,
		// materialPlantMapper);
		List<IMY_MAT_WERKS> iMyMaterialPlant = new ArrayList(objList.size());
		for (Object obj : objList) {
			IMY_MAT_WERKS matOnline = (IMY_MAT_WERKS) obj;
			iMyMaterialPlant.add(matOnline);
		}
		logger.debug("END");
		return iMyMaterialPlant;
	}
	
	public List<IMY_MAT_WERKS> getMaterialPlantDetails(String material_cd_ref) {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL_PLANT where MATERIAL_CD_REF='"+material_cd_ref+"'");
		List<Object> objList = super.getObjects(selectQuery,
				materialPlantMapper);
		// IMY_MAT_WERKS iMyMaterialPlant = (IMY_MAT_WERKS) get(selectQuery,
		// materialPlantMapper);
		List<IMY_MAT_WERKS> iMyMaterialPlant = new ArrayList(objList.size());
		for (Object obj : objList) {
			IMY_MAT_WERKS matOnline = (IMY_MAT_WERKS) obj;
			iMyMaterialPlant.add(matOnline);
		}
		logger.debug("END");
		return iMyMaterialPlant;
	}

	public List<IMY_MAT_STORAGE_DETIALS> getMaterialStorageDetails() {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL_storage");
		List<Object> objList = getObjects(selectQuery, storageMapper);
		// IMY_MAT_STORAGE_DETIALS iMyMaterialStorage =
		// (IMY_MAT_STORAGE_DETIALS) get(selectQuery, storageMapper);
		List<IMY_MAT_STORAGE_DETIALS> iMyMaterialStorageList = new ArrayList<IMY_MAT_STORAGE_DETIALS>();
		if (objList != null) {
			iMyMaterialStorageList = new ArrayList<IMY_MAT_STORAGE_DETIALS>(objList.size());
			for (Object obj : objList) {
				IMY_MAT_STORAGE_DETIALS storage = (IMY_MAT_STORAGE_DETIALS) obj;
				iMyMaterialStorageList.add(storage);
			}
		}
		logger.debug("END");
		return iMyMaterialStorageList;
	}
	
	public List<IMY_MAT_STORAGE_DETIALS> getMaterialStorageDetails(String plant_cd_ref) {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL_storage where PLANT_CD_REF='"+plant_cd_ref+"'");
		List<Object> objList = getObjects(selectQuery, storageMapper);
		// IMY_MAT_STORAGE_DETIALS iMyMaterialStorage =
		// (IMY_MAT_STORAGE_DETIALS) get(selectQuery, storageMapper);
		List<IMY_MAT_STORAGE_DETIALS> iMyMaterialStorageList = new ArrayList<IMY_MAT_STORAGE_DETIALS>();
		if (objList != null) {
			iMyMaterialStorageList = new ArrayList<IMY_MAT_STORAGE_DETIALS>(objList.size());
			for (Object obj : objList) {
				IMY_MAT_STORAGE_DETIALS storage = (IMY_MAT_STORAGE_DETIALS) obj;
				iMyMaterialStorageList.add(storage);
			}
		}
		logger.debug("END");
		return iMyMaterialStorageList;
	}

}