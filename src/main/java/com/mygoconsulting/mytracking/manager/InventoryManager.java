package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.dao.InvoiceDAO;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER_COMMEN;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_ITEM_ATMT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

@Component("InventoryManager")
public class InventoryManager {
	
	@Autowired
	@Qualifier("InvoiceDAO")
	InvoiceDAO invoiceDao;
		
	public List<IMY_MGOL_INV_DETAIL> getInventoryDetail(String invNum) {
		List<IMY_MGOL_INV_DETAIL> imyMGolInvDetail = invoiceDao.getInvDetails(invNum);
		return imyMGolInvDetail;
	}
	
	public IMY_MGOL_INV_HEADER getInventoryHeader(String invNum) {
		IMY_MGOL_INV_HEADER imyMGolInvHeader = invoiceDao.getInvHeader(invNum);
		return imyMGolInvHeader;
	}
	
	public List<IMY_MGOL_INV_ITEM_ATMT> getInvAttachement(String invNum) {
		List<IMY_MGOL_INV_ITEM_ATMT> invItemAttachments = invoiceDao.getInvoiceItemAttachement(invNum);
		return invItemAttachments;
	}
	
	public List<IMY_MGOL_INV_HEADER_COMMEN> getInvHeaderCommentDetails(String invNum) {
		List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments = invoiceDao.getInvoiceHeaderCommentDetails(invNum);
		return invHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getInvDetailComment(String invNum) {
		List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolInvComments = invoiceDao.getInvoiceDetailComment(invNum);
		return imyMGolInvComments;
	}
}