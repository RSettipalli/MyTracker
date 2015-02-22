package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.dao.SalesDAO;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;

@Component("SalesOrderManager")
public class SalesOrderManager {
	
	@Autowired
	@Qualifier("SalesDAO")
	SalesDAO salesDao;
		
	public List<IMY_MGOL_SO_DETAIL> getSalesOrderDetail(String soNum) {
		List<IMY_MGOL_SO_DETAIL> imyMGolSoDetail = salesDao.getSalesOrderDetails(soNum);
		return imyMGolSoDetail;
	}
	
	public IMY_MGOL_SO_HEADER getSalesOrderHeader(String soNum) {
		IMY_MGOL_SO_HEADER imyMGolSoHeader = salesDao.getSalesOrderHeader(soNum);
		return imyMGolSoHeader;
	}
	
}
