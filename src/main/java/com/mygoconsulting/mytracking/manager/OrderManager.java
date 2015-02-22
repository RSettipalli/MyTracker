package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.dao.DeliveryDAO;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;

@Component("OrderManager")
public class OrderManager {
	
	@Autowired
	@Qualifier("DeliveryDAO")
	DeliveryDAO deliveryDao;
		
	public List<IMY_MGOL_OD_DETAIL> getOrderDetail(String orderNum) {
		List<IMY_MGOL_OD_DETAIL> imyMGolODDetail = deliveryDao.getOrderDetails(orderNum);
		return imyMGolODDetail;
	}
	
	public IMY_MGOL_OD_HEADER getOrderHeaderDetail(String orderNum) {
		IMY_MGOL_OD_HEADER imyMGolODHeader = deliveryDao.getOrderHeader(orderNum);
		return imyMGolODHeader;
	}
}
