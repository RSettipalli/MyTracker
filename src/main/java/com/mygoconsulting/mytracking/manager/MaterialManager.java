package com.mygoconsulting.mytracking.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.dao.MaterialDAO;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;
import com.mygoconsulting.mytracking.model.IMY_MAT_STORAGE_DETIALS;
import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;


@Component("MaterialManager")
public class MaterialManager {
	@Autowired
	@Qualifier("Material")
	MaterialDAO materialDao;
	
	public IMY_MAT_ONLINE getMaterialInfo() {
		IMY_MAT_ONLINE imyMatOnline = materialDao.getMaterialDetails();
		return imyMatOnline;
	}
	
	public IMY_MAT_WERKS getMaterialPlantDetails() {
		IMY_MAT_WERKS imyMatPlant = materialDao.getMaterialPlantDetails();
		return imyMatPlant;
	}
	
	public IMY_MAT_STORAGE_DETIALS getMaterialStorageDetails() {
		IMY_MAT_STORAGE_DETIALS imyMatStorageDetails = materialDao.getMaterialStorageDetails();
		return imyMatStorageDetails;
	}
}