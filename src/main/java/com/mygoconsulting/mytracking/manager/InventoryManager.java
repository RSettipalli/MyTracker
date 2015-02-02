package com.mygoconsulting.mytracking.manager;

import java.util.List;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;

public class InventoryManager {
	
	List<IMY_MGOL_INV_DETAIL> imyMGolInvDetail;
	IMY_MGOL_INV_HEADER imyMGolInvHeader;	
		
	public List<IMY_MGOL_INV_DETAIL> getInventoryDetail() {
		IDOC idoc = new IDOC();
		imyMGolInvDetail = idoc.getIMY_MGOL_INV_DETAIL();
		return imyMGolInvDetail;
	}
	
	public IMY_MGOL_INV_HEADER getInventoryHeader() {
		IDOC idoc = new IDOC();
		imyMGolInvHeader = idoc.getIMY_MGOL_INV_HEADER();
		return imyMGolInvHeader;
	}
}