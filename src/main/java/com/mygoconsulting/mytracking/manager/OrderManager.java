package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.DeliveryDAO;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_ITEM_ATTACHM;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

@Component("OrderManager")
public class OrderManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	@Autowired
	@Qualifier("DeliveryDAO")
	DeliveryDAO deliveryDao;
		
	public List<IMY_MGOL_OD_DETAIL> getOrderDetail(String orderNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_OD_DETAIL> imyMGolODDetail = deliveryDao.getOrderDetails(orderNum);
		logger.debug("END");
		return imyMGolODDetail;
	}
	
	public IMY_MGOL_OD_HEADER getOrderHeaderDetail(String orderNum) {
		logger.debug("BEGIN");
		IMY_MGOL_OD_HEADER imyMGolODHeader = deliveryDao.getOrderHeader(orderNum);
		logger.debug("END");
		return imyMGolODHeader;
	}
	
	public List<IMY_MGOL_OD_ITEM_ATTACHM> getOrderDetailAttachement(String orderNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_OD_ITEM_ATTACHM> odDetailItemAttachments = deliveryDao.getODItemAttachement(orderNum);
		logger.debug("END");
		return odDetailItemAttachments;
	}
	
	public List<IMY_MGOL_OD_HEADER_COMMENT> getOrderHeaderCommentDetails(String orderNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_OD_HEADER_COMMENT> odHeaderComments = deliveryDao.getODHeaderCommentDetails(orderNum);
		logger.debug("END");
		return odHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getOrderDetailComment(String orderNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolODDetailComments = deliveryDao.getODDetailComment(orderNum);
		logger.debug("END");
		return imyMGolODDetailComments;
	}
}
