package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;

@Service
public class OrderManager {
	
	List<IMY_MGOL_OD_DETAIL> imyMGolODDetail;
	IMY_MGOL_OD_HEADER imyMGolODHeader;
		
	public List<IMY_MGOL_OD_DETAIL> getOrderDetail() {
		IDOC idoc = new IDOC();
		imyMGolODDetail = idoc.getIMY_MGOL_OD_DETAIL();
		return imyMGolODDetail;
	}
	
	public IMY_MGOL_OD_HEADER getOrderHeaderDetail() {
		IDOC idoc = new IDOC();
		imyMGolODHeader = idoc.getIMY_MGOL_OD_HEADER();
		return imyMGolODHeader;
	}
}
