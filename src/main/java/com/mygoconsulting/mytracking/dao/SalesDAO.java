package com.mygoconsulting.mytracking.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_ITEM_ATTACHM;

@Component("SalesDAO")
public class SalesDAO extends BaseDAO implements IDAO {

	@Autowired
	@Qualifier("SalesHeaderCommentRowMapper")
	RowMapper<IMY_MGOL_SO_HEADER_COMMENT> headerCommentRowMapper;

	@Autowired
	@Qualifier("SalesDetailRowMapper")
	RowMapper<IMY_MGOL_SO_DETAIL> soDetailRowMapper;
	
	@Autowired
	@Qualifier("DeliveryDetailCommentRowMapper")
	RowMapper<IMY_MGOL_SO_DETAIL_COMMENT> soDetailCommentRowMapper;
	
	@Autowired
	@Qualifier("SalesDetailItemAttachementRowMapper")
	RowMapper<IMY_MGOL_SO_ITEM_ATTACHM> soDetailItemAttachementRowMapper;

	@Autowired
	@Qualifier("SalesHeaderRowMapper")
	RowMapper<IMY_MGOL_SO_HEADER> headerRowMapper;

	public void persist(IDOC doc) {

		IMY_MGOL_SO_HEADER header = doc.getIMY_MGOL_SO_HEADER();

		// create header
		createHeader(header);
		
		// create sales details
		createSalesDetail(doc.getIMY_MGOL_SO_DETAIL());
		
		// create sales details
		createSalesDetailItemAttachement(doc.getIMY_MGOL_SO_DETAIL());
	}

	private void createSalesDetailItemAttachement(List<IMY_MGOL_SO_DETAIL> imy_MGOL_SO_DETAIL) {
		for (IMY_MGOL_SO_DETAIL soDetail : imy_MGOL_SO_DETAIL) {
			if(soDetail.getIMY_MGOL_SO_ITEM_ATTACHM() != null){
				String selectQuery = new String("select * from SO_ITEM_ATTACHMENT where DOKAR= ? and ORDER_NBR_SO_DETAIL = ? and ORDER_LINE_NBR_SO_DETAIL = ?");
				Object[] selectParams = { soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKAR(), soDetail.getORDER_NBR(), soDetail.getORDER_LINE_NBR()  };
				if (!isExists(selectQuery, selectParams, soDetailItemAttachementRowMapper)) {
					System.out.println("Sales Detail Item Attachement inserting");
					String sqlQuery = new String(
						"insert into SO_ITEM_ATTACHMENT (DOKAR, DOKNR, DOKTL, DOKVR, OBJKY, "
						+ "ORDER_NBR_SO_DETAIL,ORDER_LINE_NBR_SO_DETAIL) Values(?,?,?,?,?,?,?)");
					Object[] params = { soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKAR(), soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKNR(),
							soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKTL(),soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getDOKVR(),
							soDetail.getIMY_MGOL_SO_ITEM_ATTACHM().getOBJKY(),soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, params);
				} else {
					System.out.println("Sales Detail Item Attachement updating");
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
	}

	private void createSalesDetailComment(List<IMY_MGOL_SO_DETAIL_COMMENT> soDetailComments,String orderNum,String orderLineNum) {
		if(soDetailComments != null){
			for(IMY_MGOL_SO_DETAIL_COMMENT soDetailComment : soDetailComments){
				String selectQuery = new String("select * from SO_DETAIL_COMMENT where ORDER_NBR = ? and ORDER_LINE_NBR =?");
				Object[] selectParams = { soDetailComment.getORDER_NBR(), soDetailComment.getORDER_LINE_NBR() };
				if (!isExists(selectQuery, selectParams, soDetailCommentRowMapper)) {
					System.out.println("Sales Detail Comment inserting");
					String sqlQuery = new String(
							"insert into SO_DETAIL_COMMENT (ORDER_NBR, ORDER_LINE_NBR,TYPE, LINE, ORDER_NBR_SO_DETAIL,"
							+ "ORDER_LINE_NBR_SO_DETAIL) Values(?,?,?,?,?,?)");
					Object[] params = { soDetailComment.getORDER_NBR(), soDetailComment.getORDER_LINE_NBR(), soDetailComment.getTYPE(), 
							soDetailComment.getLINE(), orderNum, orderLineNum};
					insertOrUpdate(sqlQuery, params);
				} else {
					System.out.println("Sales Detail Comment updating");
					String sqlQuery = new String(
							"update SO_DETAIL_COMMENT SET  TYPE=?, LINE=? where ORDER_NBR=? and ORDER_LINE_NBR=?");
					Object[] updateParams = { soDetailComment.getTYPE(), soDetailComment.getLINE(),
							soDetailComment.getORDER_NBR(), soDetailComment.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
	}

	private void createSalesDetail(List<IMY_MGOL_SO_DETAIL> soDetailsList) {
		for (IMY_MGOL_SO_DETAIL soDetail : soDetailsList) {
			String selectQuery = new String("select * from SO_DETAIL where ORDER_NBR= ? and ORDER_LINE_NBR=?");
			Object[] selectParams = { soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR() };
			if (!isExists(selectQuery, selectParams, soDetailRowMapper)) {
				System.out.println("Sales Details inserting");
				String sqlQuery = new String("insert into SO_DETAIL (ORDER_NBR, ORDER_LINE_NBR, PRODUCT_NBR, OVERRIDE_PRODUCT, "
						+ "ITEM_CAT, BASE_UOM, NET_VAL, ORD_QTY) Values(?,?,?,?,?,?,?,?)");
				Object[] params = { soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR(), soDetail.getPRODUCT_NBR(),
						soDetail.getOVERRIDE_PRODUCT(), soDetail.getITEM_CAT(),	soDetail.getBASE_UOM(), soDetail.getNET_VAL(),
						soDetail.getORD_QTY() };
				insertOrUpdate(sqlQuery, params);
			} else {
				System.out.println("Sales Details updating");
				String sqlQuery = new String(
						"update SO_DETAIL SET  PRODUCT_NBR=?, OVERRIDE_PRODUCT=?, ITEM_CAT=?, BASE_UOM=?, NET_VAL=?, "
						+ "ORD_QTY=? where ORDER_NBR=? and ORDER_LINE_NBR=?");
				Object[] updateParams = { soDetail.getPRODUCT_NBR(), soDetail.getOVERRIDE_PRODUCT(),
						soDetail.getITEM_CAT(), soDetail.getBASE_UOM(),	soDetail.getNET_VAL(), soDetail.getORD_QTY(),
						soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR() };
				insertOrUpdate(sqlQuery, updateParams);
			}
			
			// create sales details
			createSalesDetailComment(soDetail.getIMY_MGOL_SO_DETAIL_COMMENT(),soDetail.getORDER_NBR(),soDetail.getORDER_LINE_NBR());
		}
	}

	private void createHeader(IMY_MGOL_SO_HEADER header) {
		String selectQuery = new String("select * from SO_HEADER where ORDER_NBR= ?");
		Object[] selectParams = { header.getORDER_NBR() };
		if (!isExists(selectQuery, selectParams, headerRowMapper)) {
			System.out.println("Header inserting");
			String sqlQuery = new String(
					"insert into SO_HEADER (ORDER_TYPE, SOLD_FROM_COMPANY_CD, SOLD_TO_COMPANY_CD, SHIP_TO_COMPANY_CD, ORDER_NBR, "
					+ "ORDER_NBR_VER, ORDER_PLANT_CD, ORDER_STATUS_CD, CUSTOMER_PO, END_USER, END_USER_COMPANY_CD, OVERRIDE_COMPANY_NAME, "
					+ "OVERRIDE_ADDRESS1, OVERRIDE_ADDRESS2, OVERRIDE_CITY, OVERRIDE_STATE, OVERRIDE_ZIP) Values(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,?, ?)");
			Object[] params = { header.getORDER_TYPE(),header.getSOLD_FROM_COMPANY_CD(),header.getSOLD_TO_COMPANY_CD(),
					header.getSHIP_TO_COMPANY_CD(), header.getORDER_NBR(), header.getORDER_NBR_VER(),header.getORDER_PLANT_CD(), 
					header.getORDER_STATUS_CD(), header.getCUSTOMER_PO(), header.getEND_USER(),header.getEND_USER_COMPANY_CD(),
					header.getOVERRIDE_COMPANY_NAME(),header.getOVERRIDE_ADDRESS1(),header.getOVERRIDE_ADDRESS2(), header.getOVERRIDE_CITY(),
					header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP() };
			insertOrUpdate(sqlQuery, params);
		} else {
			System.out.println("Header Udating");
			String sqlQuery = new String(
					"update SO_HEADER SET ORDER_TYPE = ?, SOLD_FROM_COMPANY_CD = ?, SOLD_TO_COMPANY_CD=?, SHIP_TO_COMPANY_CD=?, ORDER_PLANT_CD=?, "
					+ "ORDER_STATUS_CD=?, CUSTOMER_PO=?, END_USER=?, END_USER_COMPANY_CD=?, OVERRIDE_COMPANY_NAME=?, OVERRIDE_ADDRESS1=?, "
					+ "OVERRIDE_ADDRESS2=?, OVERRIDE_CITY=?, OVERRIDE_STATE=?, OVERRIDE_ZIP=? where ORDER_NBR=?");
			Object[] updateParams = { header.getORDER_TYPE(),header.getSOLD_FROM_COMPANY_CD(), header.getSOLD_TO_COMPANY_CD(),
					header.getSHIP_TO_COMPANY_CD(), header.getORDER_PLANT_CD(),	header.getORDER_STATUS_CD(), header.getCUSTOMER_PO(),
					header.getEND_USER(), header.getEND_USER_COMPANY_CD(), header.getOVERRIDE_COMPANY_NAME(), header.getOVERRIDE_ADDRESS1(),
					header.getOVERRIDE_ADDRESS2(), header.getOVERRIDE_CITY(), header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP(),
					header.getORDER_NBR() };
			insertOrUpdate(sqlQuery, updateParams);
		}
		// create header comment
		createHeaderComment(header.getIMY_MGOL_SO_HEADER_COMMENT(),header.getORDER_NBR());

	}

	private void createHeaderComment(List<IMY_MGOL_SO_HEADER_COMMENT> iMySOHeaderComments,String orderNum) {
		if(iMySOHeaderComments != null){
			for(IMY_MGOL_SO_HEADER_COMMENT iMySOHeaderComment : iMySOHeaderComments) {
				String selectQuery = new String("select * from SO_HEADER_COMMENT where ORDER_NBR= ? and LINE = ?");
				Object[] selectParams = { iMySOHeaderComment.getORDER_NBR(),iMySOHeaderComment.getLINE() };
				if (!isExists(selectQuery, selectParams, headerCommentRowMapper)) {
					System.out.println("Sales Header Comment inserting");
					String sqlQuery = new String("insert into SO_HEADER_COMMENT (ORDER_NBR, TYPE, LINE, ORDER_NBR_SO_HEADER) Values(?,?,?,?)");
					Object[] params = { iMySOHeaderComment.getORDER_NBR(), iMySOHeaderComment.getTYPE(), iMySOHeaderComment.getLINE(),
							orderNum };
					insertOrUpdate(sqlQuery, params);
				} else {
					System.out.println("Sales Header Comment updating");
					String sqlQuery = new String(
							"update SO_HEADER_COMMENT SET TYPE=?  where ORDER_NBR=? and LINE=? and ORDER_NBR_SO_HEADER = ?");
					Object[] updateParams = { iMySOHeaderComment.getTYPE(), iMySOHeaderComment.getORDER_NBR(), iMySOHeaderComment.getLINE(),
							orderNum };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
	}
	
	public IMY_MGOL_SO_HEADER getSalesOrderHeader(String soNum){
		String selectQuery = new String("select * from SO_HEADER");
		//Object[] selectParams = { header.getINVOI_NBR() };
		IMY_MGOL_SO_HEADER soHeader = (IMY_MGOL_SO_HEADER) get(selectQuery,headerRowMapper);
		return soHeader;
	}
	
	public List<IMY_MGOL_SO_DETAIL> getSalesOrderDetails(String soNum){
		String selectQuery = new String("select * from SO_DETAIL");
		List<Object> soDetailObjects = (List<Object>) getObjects(selectQuery,soDetailRowMapper);
		List<IMY_MGOL_SO_DETAIL> soDetails = new ArrayList<IMY_MGOL_SO_DETAIL>();
		for(Iterator<Object> iterator = soDetailObjects.iterator();iterator.hasNext();){
			IMY_MGOL_SO_DETAIL invDetail = (IMY_MGOL_SO_DETAIL) iterator.next();
			soDetails.add(invDetail);
		}
		return soDetails;
	}
}
