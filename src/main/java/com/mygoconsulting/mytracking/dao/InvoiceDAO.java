package com.mygoconsulting.mytracking.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER_COMMEN;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_ITEM_ATMT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

@Component("InvoiceDAO")
public class InvoiceDAO extends BaseDAO implements IDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	@Autowired
	@Qualifier("InvoiceHeaderCommentRowMapper")
	RowMapper<IMY_MGOL_INV_HEADER_COMMEN> headerCommentRowMapper;

	@Autowired
	@Qualifier("InvoiceDetailRowMapper")
	RowMapper<IMY_MGOL_INV_DETAIL> invoiceRowMapper;
	
	@Autowired
	@Qualifier("DeliveryDetailCommentRowMapper")
	RowMapper<IMY_MGOL_SO_DETAIL_COMMENT> invoiceCommentRowMapper;
	
	@Autowired
	@Qualifier("InvoiceItemAttachementRowMapper")
	RowMapper<IMY_MGOL_INV_ITEM_ATMT> invoiceItemAttachementRowMapper;

	@Autowired
	@Qualifier("InvoiceHeaderRowMapper")
	RowMapper<IMY_MGOL_INV_HEADER> headerInvoiceRowMapper;

	public void persist(IDOC doc) {
		logger.debug("BEGIN");
		IMY_MGOL_INV_HEADER header = doc.getIMY_MGOL_INV_HEADER();

		// create header
		createHeader(header);
				
		// create invoice
		createInvoice(doc.getIMY_MGOL_INV_DETAIL());
		
		//create invoice item atmt
		createInvoiceItemAtmt(doc.getIMY_MGOL_INV_DETAIL());
		logger.debug("END");
	}

	private void createInvoiceItemAtmt(List<IMY_MGOL_INV_DETAIL> imy_MGOL_INV_DETAIL) {
		logger.debug("BEGIN");
		for (IMY_MGOL_INV_DETAIL invoiceDetail : imy_MGOL_INV_DETAIL) {
			if(invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT() != null){
				String selectQuery = new String(
						"select * from INV_ITEM_ATTACHMENT where DOKAR= ? and INVOI_NUM_SO = ? and INVOI_ORD_NUM = ?");
				Object[] selectParams = { invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKAR(), invoiceDetail.getORDER_NBR(), invoiceDetail.getORDER_LINE_NBR()};
				if (!isExists(selectQuery, selectParams, invoiceItemAttachementRowMapper)) {
					logger.debug("Invoice Item Attachement inserting");
					String sqlQuery = new String(
						"insert into INV_ITEM_ATTACHMENT (DOKAR, DOKNR, DOKTL, DOKVR, OBJKY, "
						+ "INVOI_NUM_SO, INVOI_ORD_NUM) Values(?,?,?,?,?,?,?)");
					Object[] params = { invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKAR(), invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKNR(),
							invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKTL(),invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKVR(),
							invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getOBJKY(),invoiceDetail.getORDER_NBR(),invoiceDetail.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Invoice Item Attachement updating");
					String sqlQuery = new String(
						"update INV_ITEM_ATTACHMENT SET DOKNR=?, DOKTL=?, DOKVR=?, "
						+ "OBJKY=? where ORDER_NBR=? and INVOI_NUM_SO=? and INVOI_ORD_NUM = ?");
					Object[] updateParams = { invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKNR(),
							invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKTL(),invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKVR(),
							invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getOBJKY(),invoiceDetail.getIMY_MGOL_INV_ITEM_ATMT().getDOKAR(),
							invoiceDetail.getORDER_NBR(), invoiceDetail.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	private void createInvoiceComment(List<IMY_MGOL_SO_DETAIL_COMMENT> imy_MGOL_INV_DETAIL_COMMENT,
			String orderNumber,String orderLineNumber) {
		logger.debug("BEGIN");
		if(imy_MGOL_INV_DETAIL_COMMENT != null){
			for(IMY_MGOL_SO_DETAIL_COMMENT invoiceComment : imy_MGOL_INV_DETAIL_COMMENT ) {
				String selectQuery = new String(
						"select * from INV_DETAIL_COMMENT where ORDER_NBR= ? and ORDER_LINE_NBR =?");
				Object[] selectParams = { invoiceComment.getORDER_NBR(),invoiceComment.getORDER_LINE_NBR() };
				if (!isExists(selectQuery, selectParams, invoiceCommentRowMapper)) {
					logger.debug("Invoice Comment inserting");
					String sqlQuery = new String(
							"insert into INV_DETAIL_COMMENT (ORDER_NBR, ORDER_LINE_NBR, TYPE, LINE, ORDER_NBR_SO_DETAIL, ORDER_LINE_NBR_SO_DETAIL) "
							+ "Values(?,?,?,?,?,?)");
					Object[] params = { invoiceComment.getORDER_NBR(), invoiceComment.getORDER_LINE_NBR(), invoiceComment.getTYPE(), 
							invoiceComment.getLINE(), orderNumber,orderLineNumber };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Invoice Comment updating");
					String sqlQuery = new String(
							"update INV_DETAIL_COMMENT SET TYPE=?, LINE=?  where ORDER_NBR=? and ORDER_LINE_NBR = ?"); 
					Object[] updateParams = { invoiceComment.getTYPE(), invoiceComment.getLINE(), 
							invoiceComment.getORDER_NBR(), invoiceComment.getORDER_LINE_NBR()};
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	private void createInvoice(List<IMY_MGOL_INV_DETAIL> detailsList) {
		logger.debug("BEGIN");
		for (IMY_MGOL_INV_DETAIL detail : detailsList) {
			String selectQuery = new String(
					"select * from INV_DETAIL where ORDER_NBR= ? and ORDER_LINE_NBR=?");
			Object[] selectParams = { detail.getORDER_NBR(), detail.getORDER_LINE_NBR() };
			if (!isExists(selectQuery, selectParams, invoiceRowMapper)) {
				logger.debug("Invoice inserting");
				String sqlQuery = new String(
						"insert into INV_DETAIL (ORDER_NBR, ORDER_LINE_NBR, PRODUCT_NBR, OVERRIDE_PRODUCT, "
						+ "ITEM_CAT, BASE_UOM, NET_VAL) Values(?,?,?,?,?,?,?)");
				Object[] params = { detail.getORDER_NBR(),
						detail.getORDER_LINE_NBR(), detail.getPRODUCT_NBR(),
						detail.getOVERRIDE_PRODUCT(), detail.getITEM_CAT(),
						detail.getBASE_UOM(), detail.getNET_VAL() };
				insertOrUpdate(sqlQuery, params);
			} else {
				logger.debug("Invoice updating");
				String sqlQuery = new String(
						"update INV_DETAIL SET  PRODUCT_NBR=?, OVERRIDE_PRODUCT=?, "
						+ "ITEM_CAT=?, BASE_UOM=?, NET_VAL=? where ORDER_NBR=? and ORDER_LINE_NBR=?");
				Object[] updateParams = { detail.getPRODUCT_NBR(), detail.getOVERRIDE_PRODUCT(),
						detail.getITEM_CAT(), detail.getBASE_UOM(),
						detail.getNET_VAL(), detail.getORDER_NBR(),detail.getORDER_LINE_NBR() };
				insertOrUpdate(sqlQuery, updateParams);
			}
			//create invoice comment
			createInvoiceComment(detail.getIMY_MGOL_SO_DETAIL_COMMENT(),detail.getORDER_NBR(),detail.getORDER_LINE_NBR());

		}
		logger.debug("END");
	}

	private void createHeader(IMY_MGOL_INV_HEADER header) {
		logger.debug("BEGIN");
		String selectQuery = new String(
				"select * from INV_HEADER where INVOI_NBR= ?");
		Object[] selectParams = { header.getINVOI_NBR() };
		if (!isExists(selectQuery, selectParams, headerInvoiceRowMapper)) {
			logger.debug("Header inserting");

			String sqlQuery = new String(
					"insert into INV_HEADER (SOLD_FROM_COMPANY_CD, SOLD_TO_COMPANY_CD, SHIP_TO_COMPANY_CD, INVOI_NBR, "
							+ "ORDER_PLANT_CD, ORDER_STATUS_CD, CUSTOMER_PO, END_USER_COMPANY_CD, OVERRIDE_COMPANY_NAME,"
							+ "OVERRIDE_ADDRESS1, OVERRIDE_ADDRESS2, OVERRIDE_CITY, OVERRIDE_STATE, OVERRIDE_ZIP "
							+ ") Values(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?)");
			Object[] params = { header.getSOLD_FROM_COMPANY_CD(), header.getSOLD_TO_COMPANY_CD(), header.getSHIP_TO_COMPANY_CD(), 
					header.getINVOI_NBR(), header.getORDER_PLANT_CD(), header.getORDER_STATUS_CD(),	header.getCUSTOMER_PO(),
					header.getEND_USER_COMPANY_CD(),header.getOVERRIDE_COMPANY_NAME(),header.getOVERRIDE_ADDRESS1(),
					header.getOVERRIDE_ADDRESS2(), header.getOVERRIDE_CITY(), header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP() };			
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Header updating");
			String sqlQuery = new String(
					"update INV_HEADER SET SOLD_FROM_COMPANY_CD = ?, SOLD_TO_COMPANY_CD=?, SHIP_TO_COMPANY_CD=?, ORDER_PLANT_CD=?,"
					+ " ORDER_STATUS_CD=?, CUSTOMER_PO=?, END_USER_COMPANY_CD=?, OVERRIDE_COMPANY_NAME=?, OVERRIDE_ADDRESS1=?, "
					+ "OVERRIDE_ADDRESS2=?, OVERRIDE_CITY=?, OVERRIDE_STATE=?, OVERRIDE_ZIP=? where INVOI_NBR=?");
			Object[] updateParams = { header.getSOLD_FROM_COMPANY_CD(), header.getSOLD_TO_COMPANY_CD(),	header.getSHIP_TO_COMPANY_CD(),
					header.getORDER_PLANT_CD(),	header.getORDER_STATUS_CD(), header.getCUSTOMER_PO(),header.getEND_USER_COMPANY_CD(),
					header.getOVERRIDE_COMPANY_NAME(),header.getOVERRIDE_ADDRESS1(),header.getOVERRIDE_ADDRESS2(), header.getOVERRIDE_CITY(),
					header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP(), header.getINVOI_NBR() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		// create header comment
		createHeaderComment(header);
		logger.debug("END");
	}

	private void createHeaderComment(IMY_MGOL_INV_HEADER header) {
		logger.debug("BEGIN");
		List<IMY_MGOL_INV_HEADER_COMMEN> headerCommentList = header.getIMY_MGOL_INV_HEADER_COMMEN();
		if(headerCommentList != null){
			for(IMY_MGOL_INV_HEADER_COMMEN headerComment : headerCommentList ) {
				String selectQuery = new String(
					"select * from INV_HEADER_COMMENT where ORDER_NBR= ? and LINE=?");
				Object[] selectParams = { headerComment.getORDER_NBR(), headerComment.getLINE() };
				if (!isExists(selectQuery, selectParams, headerCommentRowMapper)) {
					System.out.println("Header Comment inserting");
					String sqlQuery = new String(
						"insert into INV_HEADER_COMMENT (ORDER_NBR, TYPE, LINE, INVOI_NBR_SO_HEADER) Values(?,?,?,?)");
					Object[] params = { headerComment.getORDER_NBR(), headerComment.getTYPE(), headerComment.getLINE(),
							header.getINVOI_NBR()};
					insertOrUpdate(sqlQuery, params);
				} else {
					System.out.println("Header Comment updating");
					String sqlQuery = new String(
						"update INV_HEADER_COMMENT SET TYPE=?  where ORDER_NBR=? and INVOI_NBR_SO_HEADER = ? and LINE=?");
					Object[] updateParams = { headerComment.getTYPE(), headerComment.getORDER_NBR(),
							header.getINVOI_NBR(),headerComment.getLINE(),};
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}
	
	public IMY_MGOL_INV_HEADER getInvHeader(String invNum){
		logger.debug("BEGIN");
		String selectQuery = new String("select * from INV_HEADER");
		//Object[] selectParams = { header.getINVOI_NBR() };
		IMY_MGOL_INV_HEADER invHeader = (IMY_MGOL_INV_HEADER) get(selectQuery,headerInvoiceRowMapper);
		logger.debug("END");
		return invHeader;
	}
	
	public List<IMY_MGOL_INV_DETAIL> getInvDetails(String invNum){
		logger.debug("BEGIN");
		String selectQuery = new String("select * from INV_DETAIL");
		List<Object> invDetailObjects = (List<Object>) getObjects(selectQuery,invoiceRowMapper);
		List<IMY_MGOL_INV_DETAIL> invDetails = new ArrayList<IMY_MGOL_INV_DETAIL>();
		for(Iterator<Object> iterator = invDetailObjects.iterator();iterator.hasNext();){
			IMY_MGOL_INV_DETAIL invDetail = (IMY_MGOL_INV_DETAIL) iterator.next();
			invDetails.add(invDetail);
		}
		logger.debug("END");
		return invDetails;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getInvoiceDetailComment(String invNum){
		logger.debug("BEGIN");
		String selectQuery = new String("select * from INV_DETAIL_COMMENT");
		List<Object> invCommentObjects = (List<Object>) getObjects(selectQuery,invoiceCommentRowMapper);
		List<IMY_MGOL_SO_DETAIL_COMMENT> invComments = new ArrayList<IMY_MGOL_SO_DETAIL_COMMENT>();
		for(Iterator<Object> iterator = invCommentObjects.iterator();iterator.hasNext();){
			IMY_MGOL_SO_DETAIL_COMMENT invComment = (IMY_MGOL_SO_DETAIL_COMMENT) iterator.next();
			invComments.add(invComment);
		}
		logger.debug("END");
		return invComments;
	}
	
	public List<IMY_MGOL_INV_ITEM_ATMT> getInvoiceItemAttachement(String invNum){
		logger.debug("BEGIN");
		String selectQuery = new String("select * from INV_ITEM_ATTACHMENT");
		List<Object> invItemAttachementObjects = (List<Object>) getObjects(selectQuery,invoiceItemAttachementRowMapper);
		List<IMY_MGOL_INV_ITEM_ATMT> invItemAttachements = new ArrayList<IMY_MGOL_INV_ITEM_ATMT>();
		for(Iterator<Object> iterator = invItemAttachementObjects.iterator();iterator.hasNext();){
			IMY_MGOL_INV_ITEM_ATMT invItemAttachement = (IMY_MGOL_INV_ITEM_ATMT) iterator.next();
			invItemAttachements.add(invItemAttachement);
		}
		logger.debug("END");
		return invItemAttachements;
	}
	
	public List<IMY_MGOL_INV_HEADER_COMMEN> getInvoiceHeaderCommentDetails(String invNum){
		logger.debug("BEGIN");
		String selectQuery = new String("select * from INV_HEADER_COMMENT");
		List<Object> invHeaderCommentObjects = (List<Object>) getObjects(selectQuery,headerCommentRowMapper);
		List<IMY_MGOL_INV_HEADER_COMMEN> invHeaderComments = new ArrayList<IMY_MGOL_INV_HEADER_COMMEN>();
		for(Iterator<Object> iterator = invHeaderCommentObjects.iterator();iterator.hasNext();){
			IMY_MGOL_INV_HEADER_COMMEN invHeaderComment = (IMY_MGOL_INV_HEADER_COMMEN) iterator.next();
			invHeaderComments.add(invHeaderComment);
		}
		logger.debug("END");
		return invHeaderComments;
	}
}
