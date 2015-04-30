package com.mygoconsulting.mytracking.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.InvoiceDAO;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER_COMMEN;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_ITEM_ATMT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;
import com.mygoconsulting.mytracking.util.OrderStatusTypes;

@Component("InventoryManager")
public class InventoryManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	@Qualifier("InvoiceDAO")
	InvoiceDAO invoiceDao;
		
	public List<IMY_MGOL_INV_DETAIL> getInventoryDetail(String invNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_INV_DETAIL> imyMGolInvDetailList = invoiceDao.getInvDetails(invNum);
		for(IMY_MGOL_INV_DETAIL imyMGolInvDetail:imyMGolInvDetailList){
			IMY_MGOL_INV_ITEM_ATMT invItemAttachment = getInvAttachement(imyMGolInvDetail.getORDER_NBR());
			imyMGolInvDetail.setIMY_MGOL_INV_ITEM_ATMT(invItemAttachment);
			List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolInvComments = getInvDetailComment(imyMGolInvDetail.getORDER_NBR(),
					imyMGolInvDetail.getORDER_LINE_NBR());
			imyMGolInvDetail.setIMY_MGOL_SO_DETAIL_COMMENT(imyMGolInvComments);
		}
		logger.debug("END");
		return imyMGolInvDetailList;
	}
	
	public List<IMY_MGOL_INV_HEADER> getInventoryHeaders(String customerId) {
		logger.debug("BEGIN");		
		List<IMY_MGOL_INV_HEADER> imyMGolInvHeaderList = invoiceDao.getInvHeaders(customerId);
		if(imyMGolInvHeaderList.size() > 0){
			for(IMY_MGOL_INV_HEADER imyMGolInvHeader:imyMGolInvHeaderList){
				List<IMY_MGOL_INV_DETAIL> imyMGolInvDetailList = getInventoryDetail(imyMGolInvHeader.getINVOI_NBR());
				List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments =getInvHeaderCommentDetails(imyMGolInvHeader.getINVOI_NBR());
				imyMGolInvHeader.setIMY_MGOL_INV_DETAIL(imyMGolInvDetailList);
				imyMGolInvHeader.setIMY_MGOL_INV_HEADER_COMMEN(invHeaderComments);
			}
		}
		logger.debug("END");
		return imyMGolInvHeaderList;
	}
	
	public List<IMY_MGOL_INV_HEADER> getInventoryHeadersByStatus(String customerId, OrderStatusTypes status){
		List<IMY_MGOL_INV_HEADER> toReturn = null;
		if(status!=null){
			toReturn = new ArrayList<IMY_MGOL_INV_HEADER>();
			List<IMY_MGOL_INV_HEADER> completeList = getInventoryHeaders(customerId);
			if((status.toString().equalsIgnoreCase(OrderStatusTypes.OPEN.toString()))||
				(status.toString().equalsIgnoreCase(OrderStatusTypes.COMPLETED.toString()))||
				(status.toString().equalsIgnoreCase(OrderStatusTypes.CANCELLED.toString()))){
				for(IMY_MGOL_INV_HEADER soHeaderItem:completeList){
					if(status.equals(OrderStatusTypes.getType(soHeaderItem.getORDER_STATUS_CD())))
						toReturn.add(soHeaderItem);
				}
			}
		}
		return toReturn;
	}
	
	public IMY_MGOL_INV_HEADER getInventoryHeader(String customerId, String invNum){
		logger.debug("BEGIN");
		IMY_MGOL_INV_HEADER imyMGolInvHeader = invoiceDao.getInvHeader(customerId, invNum);
		if(imyMGolInvHeader!=null){
			List<IMY_MGOL_INV_DETAIL> imyMGolInvDetailList = getInventoryDetail(imyMGolInvHeader.getINVOI_NBR());
			List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments =getInvHeaderCommentDetails(imyMGolInvHeader.getINVOI_NBR());
			imyMGolInvHeader.setIMY_MGOL_INV_DETAIL(imyMGolInvDetailList);
			imyMGolInvHeader.setIMY_MGOL_INV_HEADER_COMMEN(invHeaderComments);
		}
		logger.debug("END");
		return imyMGolInvHeader;
	}
	
	public String getInvNum(String customerId, String ordNum){
		logger.debug("BEGIN");
		String invNum = invoiceDao.getInvNum(customerId, ordNum);
		logger.debug("END");
		return invNum;
	}
	
	public IMY_MGOL_INV_ITEM_ATMT getInvAttachement(String invNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_INV_ITEM_ATMT> invItemAttachments = invoiceDao.getInvoiceItemAttachement(invNum);
		if(invItemAttachments != null && invItemAttachments.size() > 0)
			return invItemAttachments.get(0);
		logger.debug("END");
		return null;
	}
	
	public List<IMY_MGOL_INV_HEADER_COMMEN> getInvHeaderCommentDetails(String invNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments = invoiceDao.getInvoiceHeaderCommentDetails(invNum);
		logger.debug("END");
		return invHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getInvDetailComment(String invNum,String invLineNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolInvComments = invoiceDao.getInvoiceDetailComment(invNum,invLineNum);
		logger.debug("END");
		return imyMGolInvComments;
	}
}