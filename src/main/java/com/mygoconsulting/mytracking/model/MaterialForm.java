package com.mygoconsulting.mytracking.model;

import java.util.List;

public class MaterialForm {
	private String materialId;
	private List<String> materialIdList;
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public List<String> getMaterialIdList() {
		return materialIdList;
	}
	public void setMaterialIdList(List<String> materialIdList) {
		this.materialIdList = materialIdList;
	}
	
}