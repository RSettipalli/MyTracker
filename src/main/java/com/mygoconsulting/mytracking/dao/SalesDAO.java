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
import com.mygoconsulting.mytracking.model.EDI_DC40;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_ITEM_ATTACHM;

@Component("SalesDAO")
public class SalesDAO extends BaseDAO implements IDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	@Autowired
	@Qualifier("SalesHeaderCommentRowMapper")
	RowMapper<IMY_MGOL_SO_HEADER_COMMENT> headerCommentRowMapper;

	@Autowired
	@Qualifier("SalesDetailRowMapper")
	RowMapper<IMY_MGOL_SO_DETAIL> soDetailRowMapper;
	
	@Autowired
	@Qualifier("SalesOrderDetailCommentRowMapper")
	RowMapper<IMY_MGOL_SO_DETAIL_COMMENT> soDetailCommentRowMapper;
	
	@Autowired
	@Qualifier("SalesDetailItemAttachementRowMapper")
	RowMapper<IMY_MGOL_SO_ITEM_ATTACHM> soDetailItemAttachementRowMapper;

	@Autowired
	@Qualifier("SalesHeaderRowMapper")
	RowMapper<IMY_MGOL_SO_HEADER> headerRowMapper;
	
	@Autowired
	@Qualifier("EDIDC40Mapper")
	RowMapper<EDI_DC40> ediDC40Mapper;

	public void persist(IDOC doc) {
		logger.debug("BEGIN");
		IMY_MGOL_SO_HEADER header = doc.getIMY_MGOL_SO_HEADER();
		
		createEDI_DC40(doc.getEDI_DC40());

		// create header
		createHeader(header,doc.getEDI_DC40().getDOCNUM());
		
		// create sales details
		createSalesDetail(doc.getIMY_MGOL_SO_DETAIL());
		
		// create sales details
		createSalesDetailItemAttachement(doc.getIMY_MGOL_SO_DETAIL());
		logger.debug("END");
	}
	
	private void createEDI_DC40(EDI_DC40 ediDC40){
		if(ediDC40 != null){
			String selectQuery = new String("select * from EDI_DC40 where DOCNUM = ?");
			Object[] selectParams = { ediDC40.getDOCNUM() };
			if(!isExists(selectQuery,selectParams,ediDC40Mapper)){
				String sqlQuery = new String(
						"insert into EDI_DC40 (TABNAM, MANDT, DOCNUM, DOCREL, STATUS, DIRECT, OUTMOD, IDOCTYP, MESTYP,"
						+ " SNDPOR, SNDPRT, SNDPRN, RCVPOR, RCVPRT, RCVPRN, CREDAT, CRETIM, SERIAL) values ( "
						+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				Object[] params = { ediDC40.getTABNAM(), ediDC40.getMANDT(), ediDC40.getDOCNUM(), ediDC40.getDOCREL(),
						ediDC40.getSTATUS(), ediDC40.getDIRECT(), ediDC40.getOUTMOD(), ediDC40.getIDOCTYP(), 
						ediDC40.getMESTYP(), ediDC40.getSNDPOR(), ediDC40.getSNDPRT(), ediDC40.getSNDPRN(), 
						ediDC40.getRCVPOR(), ediDC40.getRCVPRT(), ediDC40.getRCVPRN(), ediDC40.getCREDAT(),
						ediDC40.getCRETIM(),ediDC40.getSERIAL()};
				insertOrUpdate(sqlQuery, params);
			}
		}
	}

	private void createSalesDetailItemAttachement(List<IMY_MGOL_SO_DETAIL> imy_MGOL_SO_DETAIL) {
		logger.debug("BEGIN");
		for (IMY_MGOL_SO_DETAIL soDetail : imy_MGOL_SO_DETAIL) {
			if(soDetail.getIMY_MGOL_SO_ITEM_ATTACHM() != null){
				String selectQuery = new String("select * from SO_ITEM_ATTACHMENT where DOKAR= ? "
						+ "and ORDER_NBR_SO_DETAIL = ? and ORDER_LINE_NBR_SO_DETAIL = ?");
				Object[] selectParams = { soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKAR(), 
						soDetail.getORDER_NBR(), soDetail.getORDER_LINE_NBR()  };
				if (!isExists(selectQuery, selectParams, soDetailItemAttachementRowMapper)) {
					logger.debug("Sales Detail Item Attachement inserting");
					String sqlQuery = new String(
						"insert into SO_ITEM_ATTACHMENT (DOKAR, DOKNR, DOKTL, DOKVR, OBJKY, "
						+ "ORDER_NBR_SO_DETAIL,ORDER_LINE_NBR_SO_DETAIL) Values(?,?,?,?,?,?,?)");
					Object[] params = { soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKAR(), 
							soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKNR(),
							soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKTL(),soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKVR(),
							soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getOBJKY(),soDetail.getORDER_NBR(),
							soDetail.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Sales Detail Item Attachement updating");
					String sqlQuery = new String(
						"update SO_ITEM_ATTACHMENT SET DOKNR=?, DOKTL=?, DOKVR=?, "
						+ "OBJKY=? where DOKAR=? and ORDER_NBR_SO_DETAIL=? and ORDER_LINE_NBR_SO_DETAIL = ?");
					Object[] updateParams = { soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKNR(),
							soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKTL(),soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKVR(),
							soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getOBJKY(),soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKAR(),
							soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	private void createSalesDetailComment(List<IMY_MGOL_SO_DETAIL_COMMENT> soDetailComments,
			String orderNum,String orderLineNum) {
		logger.debug("BEGIN");
		if(soDetailComments != null){
			for(IMY_MGOL_SO_DETAIL_COMMENT soDetailComment : soDetailComments){
				String selectQuery = new String("select * from SO_DETAIL_COMMENT where ORDER_NBR = ? and ORDER_LINE_NBR =?");
				Object[] selectParams = { soDetailComment.getORDER_NBR(), soDetailComment.getORDER_LINE_NBR() };
				if (!isExists(selectQuery, selectParams, soDetailCommentRowMapper)) {
					logger.debug("Sales Detail Comment inserting");
					String sqlQuery = new String(
							"insert into SO_DETAIL_COMMENT (ORDER_NBR, ORDER_LINE_NBR,TYPE, LINE, ORDER_NBR_SO_DETAIL,"
							+ "ORDER_LINE_NBR_SO_DETAIL) Values(?,?,?,?,?,?)");
					Object[] params = { soDetailComment.getORDER_NBR(), soDetailComment.getORDER_LINE_NBR(), 
							soDetailComment.getTYPE(), 
							soDetailComment.getLINE(), orderNum, orderLineNum};
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Sales Detail Comment updating");
					String sqlQuery = new String(
							"update SO_DETAIL_COMMENT SET  TYPE=?, LINE=? where ORDER_NBR=? and ORDER_LINE_NBR=?");
					Object[] updateParams = { soDetailComment.getTYPE(), soDetailComment.getLINE(),
							soDetailComment.getORDER_NBR(), soDetailComment.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	private void createSalesDetail(List<IMY_MGOL_SO_DETAIL> soDetailsList) {
		logger.debug("BEGIN");
		for (IMY_MGOL_SO_DETAIL soDetail : soDetailsList) {
			String selectQuery = new String("select * from SO_DETAIL where ORDER_NBR= ? and ORDER_LINE_NBR=?");
			Object[] selectParams = { soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR() };
			if (!isExists(selectQuery, selectParams, soDetailRowMapper)) {
				logger.debug("Sales Details inserting");
				String sqlQuery = new String("insert into SO_DETAIL (ORDER_NBR, ORDER_LINE_NBR, PRODUCT_NBR, OVERRIDE_PRODUCT, "
						+ "ITEM_CAT, BASE_UOM, NET_VAL, ORD_QTY, ORD_UOM_DESC, BASE_UOM_DESC, BASE_PRICE ) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?)");
				Object[] params = { soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR(), soDetail.getPRODUCT_NBR(),
						soDetail.getOVERRIDE_PRODUCT(), soDetail.getITEM_CAT(),	soDetail.getBASE_UOM(), soDetail.getNET_VAL(),
						soDetail.getORD_QTY(),soDetail.getORD_UOM_DESC(), soDetail.getBASE_UOM_DESC(),soDetail.getBASE_PRICE() };
				insertOrUpdate(sqlQuery, params);
			} else {
				logger.debug("Sales Details updating");
				String sqlQuery = new String(
						"update SO_DETAIL SET  PRODUCT_NBR=?, OVERRIDE_PRODUCT=?, ITEM_CAT=?, BASE_UOM=?, NET_VAL=?, "
						+ "ORD_QTY=?, ORD_UOM_DESC=?, BASE_UOM_DESC=?, BASE_PRICE=? where ORDER_NBR=? and ORDER_LINE_NBR=?");
				Object[] updateParams = { soDetail.getPRODUCT_NBR(), soDetail.getOVERRIDE_PRODUCT(),
						soDetail.getITEM_CAT(), soDetail.getBASE_UOM(),	soDetail.getNET_VAL(), soDetail.getORD_QTY(),
						soDetail.getORD_UOM_DESC(), soDetail.getBASE_UOM_DESC(),soDetail.getBASE_PRICE(),
						soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR() };
				insertOrUpdate(sqlQuery, updateParams);
			}
			
			// create sales details
			createSalesDetailComment(soDetail.getIMY_MGOL_SO_DETAIL_COMMENT(),soDetail.getORDER_NBR(),
					soDetail.getORDER_LINE_NBR());
		}
		logger.debug("END");
	}

	private void createHeader(IMY_MGOL_SO_HEADER header, String docNum) {
		logger.debug("BEGIN");
		String selectQuery = new String("select * from SO_HEADER where ORDER_NBR= ?");
		Object[] selectParams = { header.getORDER_NBR() };
		if (!isExists(selectQuery, selectParams, headerRowMapper)) {
			logger.debug("Header inserting");
			String sqlQuery = new String(
					"insert into SO_HEADER (ORDER_TYPE, SOLD_FROM_COMPANY_CD, SOLD_TO_COMPANY_CD, "
					+ "SHIP_TO_COMPANY_CD, BILL_TO_COMPANY_CD, ORDER_NBR, ORDER_NBR_VER, ORDER_PLANT_CD, ORDER_STATUS_CD, "
					+ "CUSTOMER_PO, END_USER, END_USER_COMPANY_CD, OVERRIDE_COMPANY_NAME, OVERRIDE_ADDRESS1, "
					+ "OVERRIDE_ADDRESS2, OVERRIDE_CITY, OVERRIDE_STATE, OVERRIDE_ZIP,TOTAL_PRICE, CURRENCY, "
					+ "CREATE_DATE, DOCNUM) Values(?,?,?,?, ?,?,?,?, "
					+ "?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?)");
			Object[] params = { header.getORDER_TYPE(),header.getSOLD_FROM_COMPANY_CD(),header.getSOLD_TO_COMPANY_CD(),
					header.getSHIP_TO_COMPANY_CD(), header.getBILL_TO_COMPANY_CD(), header.getORDER_NBR(), 
					header.getORDER_NBR_VER(),header.getORDER_PLANT_CD(), header.getORDER_STATUS_CD(), 
					header.getCUSTOMER_PO(), header.getEND_USER(),header.getEND_USER_COMPANY_CD(),
					header.getOVERRIDE_COMPANY_NAME(),header.getOVERRIDE_ADDRESS1(),header.getOVERRIDE_ADDRESS2(), 
					header.getOVERRIDE_CITY(), header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP(),
					header.getTOTAL_PRICE(),header.getCURRENCY(),header.getCREATE_DATE(), docNum };
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Header Udating");
			String sqlQuery = new String(
					"update SO_HEADER SET ORDER_TYPE = ?, SOLD_FROM_COMPANY_CD = ?, SOLD_TO_COMPANY_CD=?, "
					+ "SHIP_TO_COMPANY_CD=?, BILL_TO_COMPANY_CD=?, ORDER_PLANT_CD=?, ORDER_STATUS_CD=?, CUSTOMER_PO=?, "
					+ "END_USER=?, END_USER_COMPANY_CD=?, OVERRIDE_COMPANY_NAME=?, OVERRIDE_ADDRESS1=?, OVERRIDE_ADDRESS2=?, "
					+ "OVERRIDE_CITY=?, OVERRIDE_STATE=?, OVERRIDE_ZIP=?, TOTAL_PRICE=?, CURRENCY=?, CREATE_DATE=? "
					+ "where ORDER_NBR=?");
			Object[] updateParams = { header.getORDER_TYPE(),header.getSOLD_FROM_COMPANY_CD(), header.getSOLD_TO_COMPANY_CD(),
					header.getSHIP_TO_COMPANY_CD(), header.getBILL_TO_COMPANY_CD(), header.getORDER_PLANT_CD(),	
					header.getORDER_STATUS_CD(), header.getCUSTOMER_PO(), header.getEND_USER(), header.getEND_USER_COMPANY_CD(), 
					header.getOVERRIDE_COMPANY_NAME(), header.getOVERRIDE_ADDRESS1(), header.getOVERRIDE_ADDRESS2(), 
					header.getOVERRIDE_CITY(), header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP(), header.getTOTAL_PRICE(),
					header.getCURRENCY(),header.getCREATE_DATE(), header.getORDER_NBR() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		// create header comment
		createHeaderComment(header.getIMY_MGOL_SO_HEADER_COMMENT(),header.getORDER_NBR());
		logger.debug("END");
	}

	private void createHeaderComment(List<IMY_MGOL_SO_HEADER_COMMENT> iMySOHeaderComments,String orderNum) {
		logger.debug("BEGIN");
		if(iMySOHeaderComments != null){
			for(IMY_MGOL_SO_HEADER_COMMENT iMySOHeaderComment : iMySOHeaderComments) {
				String selectQuery = new String("select * from SO_HEADER_COMMENT where ORDER_NBR= ? and LINE = ?");
				Object[] selectParams = { iMySOHeaderComment.getORDER_NBR(),iMySOHeaderComment.getLINE() };
				if (!isExists(selectQuery, selectParams, headerCommentRowMapper)) {
					logger.debug("Sales Header Comment inserting");
					String sqlQuery = new String("insert into SO_HEADER_COMMENT (ORDER_NBR, TYPE, LINE, ORDER_NBR_SO_HEADER)"
							+ " Values(?,?,?,?)");
					Object[] params = { iMySOHeaderComment.getORDER_NBR(), iMySOHeaderComment.getTYPE(), 
							iMySOHeaderComment.getLINE(), orderNum };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Sales Header Comment updating");
					String sqlQuery = new String(
							"update SO_HEADER_COMMENT SET TYPE=?  where ORDER_NBR=? and LINE=? and ORDER_NBR_SO_HEADER = ?");
					Object[] updateParams = { iMySOHeaderComment.getTYPE(), iMySOHeaderComment.getORDER_NBR(), 
							iMySOHeaderComment.getLINE(), orderNum };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}
	
	public List<IMY_MGOL_SO_HEADER> getSalesOrderHeader(String customerId){
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_HEADER> soHeaderList = new ArrayList<IMY_MGOL_SO_HEADER>();		
		StringBuilder selectQuery = new StringBuilder("select * from SO_HEADER");
		if(customerId != null)
			selectQuery.append(" where SOLD_TO_COMPANY_CD = "+customerId);
		List<Object> objectsList = (List<Object>) getObjects(selectQuery.toString(),headerRowMapper);
		if(objectsList != null){
			for(Object obj: objectsList){
				IMY_MGOL_SO_HEADER imySoHeader = (IMY_MGOL_SO_HEADER) obj;
				soHeaderList.add(imySoHeader);
			}
		}
		logger.debug("END");
		return soHeaderList;
	}
	
	public IMY_MGOL_SO_ITEM_ATTACHM getSODetailAttachement(String soNum){
		logger.debug("BEGIN");
		String selectQuery = new String("select * from SO_ITEM_ATTACHMENT");
		IMY_MGOL_SO_ITEM_ATTACHM soDetailItemAttachementObjects = (IMY_MGOL_SO_ITEM_ATTACHM) get(selectQuery,
				soDetailItemAttachementRowMapper);
		logger.debug("END");
		return soDetailItemAttachementObjects;
	}
	
	public List<IMY_MGOL_SO_HEADER_COMMENT> getSOHeaderCommentDetails(String soNum){
		logger.debug("BEGIN");
		StringBuilder selectQuery = new StringBuilder("select * from SO_HEADER_COMMENT");
		if(soNum != null)
			selectQuery.append(" where ORDER_NBR_SO_HEADER = "+soNum);
		List<Object> soHeaderCommentObjects = (List<Object>) getObjects(selectQuery.toString(),headerCommentRowMapper);
		List<IMY_MGOL_SO_HEADER_COMMENT> soHeaderComments = new ArrayList<IMY_MGOL_SO_HEADER_COMMENT>();
		for(Iterator<Object> iterator = soHeaderCommentObjects.iterator();iterator.hasNext();){
			IMY_MGOL_SO_HEADER_COMMENT soHeaderComment = (IMY_MGOL_SO_HEADER_COMMENT) iterator.next();
			soHeaderComments.add(soHeaderComment);
		}
		logger.debug("END");
		return soHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL> getSalesOrderDetails(String soNum){
		logger.debug("BEGIN");
		StringBuilder selectQuery = new StringBuilder("select * from SO_DETAIL");
		if(soNum != null)
			selectQuery.append(" where order_nbr = "+soNum);
		List<Object> soDetailObjects = (List<Object>) getObjects(selectQuery.toString(),soDetailRowMapper);
		List<IMY_MGOL_SO_DETAIL> soDetails = new ArrayList<IMY_MGOL_SO_DETAIL>();
		for(Iterator<Object> iterator = soDetailObjects.iterator();iterator.hasNext();){
			IMY_MGOL_SO_DETAIL invDetail = (IMY_MGOL_SO_DETAIL) iterator.next();
			soDetails.add(invDetail);
		}
		logger.debug("END");
		return soDetails;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getSODetailComment(String soNum,String soLineNum){
		logger.debug("BEGIN");
		StringBuilder sb = new StringBuilder();
		sb.append("select * from SO_DETAIL_COMMENT");		
		if(soNum != null && soLineNum != null)
			sb.append(" where ORDER_NBR_SO_DETAIL = "+soNum+" and ORDER_LINE_NBR_SO_DETAIL = "+soLineNum);
		List<Object> soDetailCommentObjects = (List<Object>) getObjects(sb.toString(),soDetailCommentRowMapper);
		List<IMY_MGOL_SO_DETAIL_COMMENT> soDetailComments = new ArrayList<IMY_MGOL_SO_DETAIL_COMMENT>();
		for(Iterator<Object> iterator = soDetailCommentObjects.iterator();iterator.hasNext();){
			IMY_MGOL_SO_DETAIL_COMMENT soDetailComment = (IMY_MGOL_SO_DETAIL_COMMENT) iterator.next();
			soDetailComments.add(soDetailComment);
		}
		logger.debug("END");
		return soDetailComments;
	}
}
