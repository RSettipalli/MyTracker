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
		List<IMY_MGOL_OD_DETAIL> imyMGolODDetailList = deliveryDao.getOrderDetails(orderNum);
		for(IMY_MGOL_OD_DETAIL imyMGolODDetail:imyMGolODDetailList){
			IMY_MGOL_OD_ITEM_ATTACHM odDetailItemAttachment = getOrderDetailAttachement(imyMGolODDetail.getORDER_NBR());
			imyMGolODDetail.setIMY_MGOL_OD_ITEM_ATTACHM(odDetailItemAttachment);
			List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolODDetailComments = getOrderDetailComment(imyMGolODDetail.getORDER_NBR()
					,imyMGolODDetail.getORDER_LINE_NBR());
			imyMGolODDetail.setIMY_MGOL_SO_DETAIL_COMMENT(imyMGolODDetailComments);
		}
		logger.debug("END");
		return imyMGolODDetailList;
	}
	
	public List<IMY_MGOL_OD_HEADER> getOrderHeaderDetail(String customerId) {
		logger.debug("BEGIN");
		List<IMY_MGOL_OD_HEADER> imyMGolODHeaderList = deliveryDao.getOrderHeader(customerId);
		if(imyMGolODHeaderList.size() > 0){
			for(IMY_MGOL_OD_HEADER imyMGolODHeader:imyMGolODHeaderList){
				List<IMY_MGOL_OD_DETAIL> imyMGolODDetailList = getOrderDetail(imyMGolODHeader.getDELVI_NBR());
				List<IMY_MGOL_OD_HEADER_COMMENT> oDHeaderComments = getOrderHeaderCommentDetails(imyMGolODHeader.getDELVI_NBR());
				imyMGolODHeader.setIMY_MGOL_OD_DETAIL(imyMGolODDetailList);
				imyMGolODHeader.setIMY_MGOL_OD_HEADER_COMMENT(oDHeaderComments);
			}
		}
		logger.debug("END");
		return imyMGolODHeaderList;
	}
	
	public IMY_MGOL_OD_ITEM_ATTACHM getOrderDetailAttachement(String orderNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_OD_ITEM_ATTACHM> odDetailItemAttachments = deliveryDao.getODItemAttachement(orderNum);
		if(odDetailItemAttachments != null && odDetailItemAttachments.size() > 0)
			return odDetailItemAttachments.get(0);
		logger.debug("END");
		return null;
	}
	
	public List<IMY_MGOL_OD_HEADER_COMMENT> getOrderHeaderCommentDetails(String orderNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_OD_HEADER_COMMENT> odHeaderComments = deliveryDao.getODHeaderCommentDetails(orderNum);
		logger.debug("END");
		return odHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getOrderDetailComment(String orderNum,String orderLineNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolODDetailComments = deliveryDao.getODDetailComment(orderNum,orderLineNum);
		logger.debug("END");
		return imyMGolODDetailComments;
	}
}
