package com.mygoconsulting.mytracking.manager;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;


public class MaterialManager {
	IMY_MAT_ONLINE imyMatOnline;
	public IMY_MAT_ONLINE getMaterialInfo() {
		IDOC idoc = new IDOC();
		imyMatOnline = idoc.getIMY_MAT_ONLINE();
		return imyMatOnline;
	}
}