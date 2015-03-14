package com.mygoconsulting.mytracking.manager;

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

@Component("InventoryManager")
public class InventoryManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	@Qualifier("InvoiceDAO")
	InvoiceDAO invoiceDao;
		
	public List<IMY_MGOL_INV_DETAIL> getInventoryDetail(String invNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_INV_DETAIL> imyMGolInvDetail = invoiceDao.getInvDetails(invNum);
		logger.debug("END");
		return imyMGolInvDetail;
	}
	
	public IMY_MGOL_INV_HEADER getInventoryHeader(String invNum) {
		logger.debug("BEGIN");
		IMY_MGOL_INV_HEADER imyMGolInvHeader = invoiceDao.getInvHeader(invNum);
		logger.debug("END");
		return imyMGolInvHeader;
	}
	
	public List<IMY_MGOL_INV_ITEM_ATMT> getInvAttachement(String invNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_INV_ITEM_ATMT> invItemAttachments = invoiceDao.getInvoiceItemAttachement(invNum);
		logger.debug("END");
		return invItemAttachments;
	}
	
	public List<IMY_MGOL_INV_HEADER_COMMEN> getInvHeaderCommentDetails(String invNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments = invoiceDao.getInvoiceHeaderCommentDetails(invNum);
		logger.debug("END");
		return invHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getInvDetailComment(String invNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolInvComments = invoiceDao.getInvoiceDetailComment(invNum);
		logger.debug("END");
		return imyMGolInvComments;
	}
}