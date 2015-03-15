package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.MaterialDAO;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;
import com.mygoconsulting.mytracking.model.IMY_MAT_STORAGE_DETIALS;
import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;


@Component("MaterialManager")
public class MaterialManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	@Autowired
	@Qualifier("Material")
	MaterialDAO materialDao;
	
	public List<IMY_MAT_ONLINE> getMaterialInfo() {
		logger.debug("BEGIN");
		List<IMY_MAT_ONLINE> imyMatOnlineList = materialDao.getMaterialDetails();
		logger.debug("END");
		return imyMatOnlineList;
	}
	
	public List<IMY_MAT_WERKS> getMaterialPlantDetails() {
		logger.debug("BEGIN");
		List<IMY_MAT_WERKS> imyMatPlant = materialDao.getMaterialPlantDetails();
		logger.debug("END");
		return imyMatPlant;
	}
	
	public List<IMY_MAT_STORAGE_DETIALS> getMaterialStorageDetails() {
		logger.debug("BEGIN");
		List<IMY_MAT_STORAGE_DETIALS> imyMatStorageDetails = materialDao.getMaterialStorageDetails();
		logger.debug("END");
		return imyMatStorageDetails;
	}
}