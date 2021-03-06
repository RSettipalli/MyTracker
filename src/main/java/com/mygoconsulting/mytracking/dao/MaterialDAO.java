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
			createStorageDetails(material.getIMY_MAT_WERKS()
					.getIMY_MAT_STORAGE_DETIALS());

			// create material Plant details
			createPlant(material.getIMY_MAT_WERKS());

			// create material Storage details
			createMaterial(material);
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
					"insert into MATERIAL (MATERIAL_CD, MAT_DESC, UOM, STOCK, BOM, MAT_TYPE, GROSS_WEIGHT, NET_WEIGHT, MATERIAL_GROUP, PLANT_CD) Values(?,?,?, ?,?, ?, ?,?,?,?)");
			Object[] params = { material.getMATERIAL(), material.getMAT_DESC(),
					material.getUOM(), material.getSTOCK(), material.getBOM(),
					material.getMAT_TYPE(), material.getGROSS_WEIGHT(),
					material.getNET_WEIGHT(), material.getMATERIAL_GROUP(),
					material.getIMY_MAT_WERKS().getPLANT() };
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Material updating");
			String sqlQuery = new String(
					"update MATERIAL SET MAT_DESC=?, UOM=?, STOCK=?, BOM=?, MAT_TYPE=?, GROSS_WEIGHT=?, NET_WEIGHT=?, MATERIAL_GROUP=?, PLANT_CD=? where MATERIAL_CD=?");
			Object[] updateParams = { material.getMATERIAL(),
					material.getMAT_DESC(), material.getUOM(),
					material.getSTOCK(), material.getBOM(),
					material.getMAT_TYPE(), material.getGROSS_WEIGHT(),
					material.getNET_WEIGHT(), material.getMATERIAL_GROUP(),
					material.getIMY_MAT_WERKS().getPLANT() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		logger.debug("END");
	}

	private void createPlant(IMY_MAT_WERKS materialPlant) {
		logger.debug("BEGIN");
		String selectQuery = new String(
				"select * from MATERIAL_PLANT where PLANT_CD= ?");
		Object[] selectParams = { materialPlant.getPLANT() };
		if (!isExists(selectQuery, selectParams, materialPlantMapper)) {
			logger.debug("Material Plant inserting");

			String sqlQuery = new String(
					"insert into MATERIAL_PLANT (PLANT_CD, MAINT_STATUS, MRP_TYPE,MRP_CONT, STO_LOCATION) Values(?,?,?, ?, ?)");
			Object[] params = {
					materialPlant.getPLANT(),
					materialPlant.getMAINT_STATUS(),
					materialPlant.getMRP_TYPE(),
					materialPlant.getMRP_CONT(),
					materialPlant.getIMY_MAT_STORAGE_DETIALS()
							.getSTO_LOCATION() };
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Material Plant updating");
			String sqlQuery = new String(
					"update MATERIAL_PLANT SET MAINT_STATUS=?, MRP_TYPE=?, MRP_CONT=?, STO_LOCATION=?  where PLANT_CD=? ");
			Object[] updateParams = {
					materialPlant.getMAINT_STATUS(),
					materialPlant.getMRP_TYPE(),
					materialPlant.getMRP_CONT(),
					materialPlant.getIMY_MAT_STORAGE_DETIALS()
							.getSTO_LOCATION(), materialPlant.getPLANT() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		logger.debug("END");
	}

	private void createStorageDetails(IMY_MAT_STORAGE_DETIALS storageDetails) {
		logger.debug("BEGIN");
		String selectQuery = new String(
				"select * from MATERIAL_STORAGE where STO_LOCATION= ?");
		Object[] selectParams = { storageDetails.getSTO_LOCATION() };
		if (!isExists(selectQuery, selectParams, storageMapper)) {
			logger.debug("Material Storage details inserting");

			String sqlQuery = new String(
					"insert into MATERIAL_STORAGE (STO_LOCATION, MAINT_STATUS, STOC_IN_QLTY_INS ) Values(?,?,?)");
			Object[] params = { storageDetails.getSTO_LOCATION(),
					storageDetails.getMAINT_STATUS(),
					storageDetails.getSTOC_IN_QLTY_INS() };
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Material Storage details updating");
			String sqlQuery = new String(
					"update MATERIAL_STORAGE set  MAINT_STATUS=?, STOC_IN_QLTY_INS=? where STO_LOCATION=? ");
			Object[] updateParams = { storageDetails.getMAINT_STATUS(),
					storageDetails.getSTOC_IN_QLTY_INS(),
					storageDetails.getSTO_LOCATION() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		logger.debug("END");

	}

	public List<IMY_MAT_ONLINE> getMaterialDetails() {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL");
		List<Object> objList = super.getObjects(selectQuery, materialMapper);
		// IMY_MAT_ONLINE iMyMaterials = (IMY_MAT_ONLINE) get(selectQuery,
		// materialMapper);
		List<IMY_MAT_ONLINE> iMyMaterials = new ArrayList(objList.size());
		for (Object obj : objList) {
			IMY_MAT_ONLINE matOnline = (IMY_MAT_ONLINE) obj;
			iMyMaterials.add(matOnline);
		}
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

	public List<IMY_MAT_STORAGE_DETIALS> getMaterialStorageDetails() {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from MATERIAL_storage");
		List<Object> objList = getObjects(selectQuery, storageMapper);
		// IMY_MAT_STORAGE_DETIALS iMyMaterialStorage =
		// (IMY_MAT_STORAGE_DETIALS) get(selectQuery, storageMapper);
		List<IMY_MAT_STORAGE_DETIALS> iMyMaterialStorageList = new ArrayList<IMY_MAT_STORAGE_DETIALS>();
		if (objList != null) {
			iMyMaterialStorageList = new ArrayList(objList.size());
			for (Object obj : objList) {
				IMY_MAT_STORAGE_DETIALS storage = (IMY_MAT_STORAGE_DETIALS) obj;
				iMyMaterialStorageList.add(storage);
			}
		}
		logger.debug("END");
		return iMyMaterialStorageList;
	}

}