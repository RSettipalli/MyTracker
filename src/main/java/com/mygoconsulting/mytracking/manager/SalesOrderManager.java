package com.mygoconsulting.mytracking.manager;

import java.util.List;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;

public class SalesOrderManager {
	
	List<IMY_MGOL_SO_DETAIL> imyMGolSODetail;
	IMY_MGOL_SO_HEADER imyMGolSOHeader;	
		
	public List<IMY_MGOL_SO_DETAIL> getSoDetail() {
		IDOC idoc = new IDOC();
		imyMGolSODetail = idoc.getIMY_MGOL_SO_DETAIL();
		return imyMGolSODetail;
	}
	
	public IMY_MGOL_SO_HEADER getSoHeader() {
		IDOC idoc = new IDOC();
		imyMGolSOHeader = idoc.getIMY_MGOL_SO_HEADER();
		return imyMGolSOHeader;
	}
	
}
