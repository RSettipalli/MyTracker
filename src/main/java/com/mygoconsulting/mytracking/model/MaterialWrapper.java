package com.mygoconsulting.mytracking.model;

public class MaterialWrapper {
	private IMY_MAT_ONLINE material;
	private IMY_MAT_WERKS materialPlant;
	private IMY_MAT_STORAGE_DETIALS storage;
	public IMY_MAT_ONLINE getMaterial() {
		return material;
	}
	public void setMaterial(IMY_MAT_ONLINE material) {
		this.material = material;
	}
	public IMY_MAT_WERKS getMaterialPlant() {
		return materialPlant;
	}
	public void setMaterialPlant(IMY_MAT_WERKS materialPlant) {
		this.materialPlant = materialPlant;
	}
	public IMY_MAT_STORAGE_DETIALS getStorage() {
		return storage;
	}
	public void setStorage(IMY_MAT_STORAGE_DETIALS storage) {
		this.storage = storage;
	}
	
	
}
