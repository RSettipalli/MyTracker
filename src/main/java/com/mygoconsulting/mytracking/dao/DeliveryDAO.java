package com.mygoconsulting.mytracking.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.EDI_DC40;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_ITEM_ATTACHM;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

@Component("DeliveryDAO")
public class DeliveryDAO extends BaseDAO implements IDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	@Autowired
	@Qualifier("DeliveryHeaderCommentRowMapper")
	RowMapper<IMY_MGOL_OD_HEADER_COMMENT> headerCommentRowMapper;

	@Autowired
	@Qualifier("DeliveryDetailRowMapper")
	RowMapper<IMY_MGOL_OD_DETAIL> detailRowMapper;

	@Autowired
	@Qualifier("DeliveryHeaderRowMapper")
	RowMapper<IMY_MGOL_OD_HEADER> headerRowMapper;

	@Autowired
	@Qualifier("DeliveryDetailCommentRowMapper")
	RowMapper<IMY_MGOL_SO_DETAIL_COMMENT> detailCommentRowMapper;

	@Autowired
	@Qualifier("DetailItemAttachementRowMapper")
	RowMapper<IMY_MGOL_OD_ITEM_ATTACHM> detailItemAttachementRowMapper;

	@Autowired
	@Qualifier("EDIDC40Mapper")
	RowMapper<EDI_DC40> ediDC40Mapper;

	public void persist(IDOC doc) {
		logger.debug("BEGIN");
		IMY_MGOL_OD_HEADER header = doc.getIMY_MGOL_OD_HEADER();

		createEDI_DC40(doc.getEDI_DC40());

		// create header
		createHeader(header, doc.getEDI_DC40().getDOCNUM());

		// create details
		createDetail(doc.getIMY_MGOL_OD_DETAIL());

		// create detail item attachment
		createDetailItemAttachment(doc.getIMY_MGOL_OD_DETAIL());
		logger.debug("END");
	}

	private void createEDI_DC40(EDI_DC40 ediDC40) {
		if (ediDC40 != null) {
			String selectQuery = new String(
					"select * from EDI_DC40 where DOCNUM = ?");
			Object[] selectParams = { ediDC40.getDOCNUM() };
			if (!isExists(selectQuery, selectParams, ediDC40Mapper)) {
				String sqlQuery = new String(
						"insert into EDI_DC40 (TABNAM, MANDT, DOCNUM, DOCREL, STATUS, DIRECT, OUTMOD, IDOCTYP, MESTYP,"
								+ " SNDPOR, SNDPRT, SNDPRN, RCVPOR, RCVPRT, RCVPRN, CREDAT, CRETIM, SERIAL) values ( "
								+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				Object[] params = { ediDC40.getTABNAM(), ediDC40.getMANDT(),
						ediDC40.getDOCNUM(), ediDC40.getDOCREL(),
						ediDC40.getSTATUS(), ediDC40.getDIRECT(),
						ediDC40.getOUTMOD(), ediDC40.getIDOCTYP(),
						ediDC40.getMESTYP(), ediDC40.getSNDPOR(),
						ediDC40.getSNDPRT(), ediDC40.getSNDPRN(),
						ediDC40.getRCVPOR(), ediDC40.getRCVPRT(),
						ediDC40.getRCVPRN(), ediDC40.getCREDAT(),
						ediDC40.getCRETIM(), ediDC40.getSERIAL() };
				insertOrUpdate(sqlQuery, params);
			}
		}
	}

	private void createDetailItemAttachment(
			List<IMY_MGOL_OD_DETAIL> imy_MGOL_OD_DETAIL) {
		logger.debug("BEGIN");
		for (IMY_MGOL_OD_DETAIL detail : imy_MGOL_OD_DETAIL) {
			if (detail.getIMY_MGOL_OD_ITEM_ATTACHM() != null) {
				String selectQuery = new String(
						"select * from DELIVERY_ITEM_ATTACHMENT where DOKAR= ? and ORDER_NBR_OD_DETAIL = ? "
								+ "and ORDER_LINE_NBR_OD_DETAIL =?");
				Object[] selectParams = {
						detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKAR(),
						detail.getORDER_NBR(), detail.getORDER_LINE_NBR() };
				if (!isExists(selectQuery, selectParams,
						detailItemAttachementRowMapper)) {
					logger.debug("Detail Item Attachement inserting");
					String sqlQuery = new String(
							"insert into DELIVERY_ITEM_ATTACHMENT (DOKAR, DOKNR, DOKTL, DOKVR, OBJKY, "
									+ "ORDER_NBR_OD_DETAIL,ORDER_LINE_NBR_OD_DETAIL) Values(?,?,?,?,?,?,?)");
					Object[] params = {
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKAR(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKNR(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKTL(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKVR(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getOBJKY(),
							detail.getORDER_NBR(), detail.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Detail Item Attachement updating");
					String sqlQuery = new String(
							"update DELIVERY_ITEM_ATTACHMENT SET DOKNR=?, DOKTL=?, DOKVR=?, "
									+ "OBJKY=? where DOKAR=? and ORDER_NBR_OD_DETAIL=? and ORDER_LINE_NBR_OD_DETAIL =?");
					Object[] updateParams = {
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKNR(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKTL(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKVR(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getOBJKY(),
							detail.getIMY_MGOL_OD_ITEM_ATTACHM().getDOKAR(),
							detail.getORDER_NBR(), detail.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	private void createDetail(List<IMY_MGOL_OD_DETAIL> detailsList) {
		logger.debug("BEGIN");
		for (IMY_MGOL_OD_DETAIL detail : detailsList) {
			String selectQuery = new String(
					"select * from DELIVERY_DETAIL where ORDER_NBR= ? and ORDER_LINE_NBR=?");
			Object[] selectParams = { detail.getORDER_NBR(),
					detail.getORDER_LINE_NBR() };
			if (!isExists(selectQuery, selectParams, detailRowMapper)) {
				logger.debug("Delivery Detail inserting");
				String sqlQuery = new String(
						"insert into DELIVERY_DETAIL (ORDER_NBR, ORDER_LINE_NBR, PRODUCT_NBR, OVERRIDE_PRODUCT, ITEM_CAT, "
								+ "BASE_UOM, NET_VAL, ORD_QTY, ORD_UOM_DESC, BASE_UOM_DESC, BASE_PRICE ) values(?,?,?,?, ?,?,?,?, ?,?,?)");
				Object[] params = { detail.getORDER_NBR(),
						detail.getORDER_LINE_NBR(), detail.getPRODUCT_NBR(),
						detail.getOVERRIDE_PRODUCT(), detail.getITEM_CAT(),
						detail.getBASE_UOM(), detail.getNET_VAL(),
						detail.getORD_QTY(), detail.getORD_UOM_DESC(),
						detail.getBASE_UOM_DESC(), detail.getBASE_PRICE() };
				insertOrUpdate(sqlQuery, params);
			} else {
				logger.debug("Delivery Detail updating");
				String sqlQuery = new String(
						"update DELIVERY_DETAIL SET PRODUCT_NBR=?, OVERRIDE_PRODUCT=?, "
								+ "ITEM_CAT=?, BASE_UOM=?, NET_VAL=?, ORD_QTY=?, "
								+ "ORD_UOM_DESC=?, BASE_UOM_DESC=?, BASE_PRICE=? "
								+ "where ORDER_NBR=? and ORDER_LINE_NBR=?");
				Object[] updateParams = { detail.getPRODUCT_NBR(),
						detail.getOVERRIDE_PRODUCT(), detail.getITEM_CAT(),
						detail.getBASE_UOM(), detail.getNET_VAL(),
						detail.getORD_QTY(), detail.getORD_UOM_DESC(),
						detail.getBASE_UOM_DESC(), detail.getBASE_PRICE(),
						detail.getORDER_NBR(), detail.getORDER_LINE_NBR() };
				insertOrUpdate(sqlQuery, updateParams);
			}
			// create detail comment
			createDetailComment(detail.get_IMY_MGOL_SO_DETAIL_COMMENT(),
					detail.getORDER_NBR(), detail.getORDER_LINE_NBR());
		}
		logger.debug("END");
	}

	private void createHeader(IMY_MGOL_OD_HEADER header, String docNum) {
		logger.debug("BEGIN");
		String selectQuery = new String(
				"select * from DELIVERY_HEADER where DELVI_NBR= ?");
		Object[] selectParams = { header.getDELVI_NBR() };
		if (!isExists(selectQuery, selectParams, headerRowMapper)) {
			logger.debug("Header inserting");
			String sqlQuery = new String(
					"insert into DELIVERY_HEADER (SOLD_TO_COMPANY_CD, SHIP_TO_COMPANY_CD, DELVI_NBR, ORDER_PLANT_CD, "
							+ "ORDER_STATUS_CD, CUSTOMER_PO, END_USER, END_USER_COMPANY_CD, OVERRIDE_COMPANY_NAME, "
							+ "OVERRIDE_ADDRESS1, OVERRIDE_ADDRESS2, OVERRIDE_CITY, OVERRIDE_STATE, OVERRIDE_ZIP, "
							+ "ORDER_REF_NUM, DOCNUM, DELIV_PRICE, CURRENCY, CREATE_DATE ) "
							+ "Values(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?)");
			Object[] params = { header.getSOLD_TO_COMPANY_CD(),
					header.getSHIP_TO_COMPANY_CD(), header.getDELVI_NBR(),
					header.getORDER_PLANT_CD(), header.getORDER_STATUS_CD(),
					header.getCUSTOMER_PO(), header.getEND_USER(),
					header.getEND_USER_COMPANY_CD(),
					header.getOVERRIDE_COMPANY_NAME(),
					header.getOVERRIDE_ADDRESS1(),
					header.getOVERRIDE_ADDRESS2(), header.getOVERRIDE_CITY(),
					header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP(),
					header.getORDER_REF_NUM(), docNum, header.getDELIV_PRICE(),
					header.getCURRENCY(), header.getCREATE_DATE() };
			insertOrUpdate(sqlQuery, params);
		} else {
			logger.debug("Header Comment updating");
			String sqlQuery = new String(
					"update DELIVERY_HEADER SET SOLD_TO_COMPANY_CD=?, SHIP_TO_COMPANY_CD=?,  ORDER_PLANT_CD=?, "
							+ "ORDER_STATUS_CD=?, CUSTOMER_PO=?, END_USER=?, END_USER_COMPANY_CD=?, OVERRIDE_COMPANY_NAME=?, "
							+ "OVERRIDE_ADDRESS1=?, OVERRIDE_ADDRESS2=?, OVERRIDE_CITY=?, OVERRIDE_STATE=?, OVERRIDE_ZIP=?, "
							+ "ORDER_REF_NUM=?, DELIV_PRICE=?, CURRENCY=?, CREATE_DATE=? where DELVI_NBR=?");
			Object[] updateParams = { header.getSOLD_TO_COMPANY_CD(),
					header.getSHIP_TO_COMPANY_CD(), header.getORDER_PLANT_CD(),
					header.getORDER_STATUS_CD(), header.getCUSTOMER_PO(),
					header.getEND_USER(), header.getEND_USER_COMPANY_CD(),
					header.getOVERRIDE_COMPANY_NAME(),
					header.getOVERRIDE_ADDRESS1(),
					header.getOVERRIDE_ADDRESS2(), header.getOVERRIDE_CITY(),
					header.getOVERRIDE_STATE(), header.getOVERRIDE_ZIP(),
					header.getORDER_REF_NUM(), header.getDELIV_PRICE(),
					header.getCURRENCY(), header.getCREATE_DATE(),
					header.getDELVI_NBR() };
			insertOrUpdate(sqlQuery, updateParams);
		}

		// create header comment
		createHeaderComment(header.getIMY_MGOL_OD_HEADER_COMMENT(),
				header.getDELVI_NBR());
		logger.debug("END");
	}

	private void createHeaderComment(
			List<IMY_MGOL_OD_HEADER_COMMENT> headerComments, String delviNum) {
		logger.debug("BEGIN");
		if (headerComments != null) {
			logger.debug("Header Comments are not null");
			for (IMY_MGOL_OD_HEADER_COMMENT headerComment : headerComments) {
				String selectQuery = new String(
						"select * from DELIVERY_OD_HEADER_COMMENT where ORDER_NBR= ? and LINE = ?");
				Object[] selectParams = { headerComment.getORDER_NBR(),
						headerComment.getLINE() };
				if (!isExists(selectQuery, selectParams, headerCommentRowMapper)) {
					logger.debug("Header Comment inserting");
					String sqlQuery = new String(
							"insert into DELIVERY_OD_HEADER_COMMENT (ORDER_NBR, TYPE, "
									+ "LINE, DELVI_NBR_OD_HEADER) Values(?,?,?,?)");
					Object[] params = { headerComment.getORDER_NBR(),
							headerComment.getTYPE(), headerComment.getLINE(),
							delviNum };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Header Comment updating");
					String sqlQuery = new String(
							"update DELIVERY_OD_HEADER_COMMENT SET TYPE=? where ORDER_NBR=? "
									+ "and LINE=? and DELVI_NBR_OD_HEADER = ?");
					Object[] updateParams = { headerComment.getTYPE(),
							headerComment.getORDER_NBR(),
							headerComment.getLINE(), delviNum };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	private void createDetailComment(
			List<IMY_MGOL_SO_DETAIL_COMMENT> detailComments, String orderNum,
			String orderLineNum) {
		logger.debug("BEGIN");
		if (detailComments != null) {
			for (IMY_MGOL_SO_DETAIL_COMMENT detailComment : detailComments) {
				String selectQuery = new String(
						"select * from DELIVERY_OD_DETAIL_COMMENT where ORDER_NBR= ? and ORDER_LINE_NBR = ?");
				Object[] selectParams = { detailComment.getORDER_NBR(),
						detailComment.getORDER_LINE_NBR() };
				if (!isExists(selectQuery, selectParams, detailCommentRowMapper)) {
					logger.debug("Detail Comment inserting");
					String sqlQuery = new String(
							"insert into DELIVERY_OD_DETAIL_COMMENT (ORDER_NBR, ORDER_LINE_NBR, "
									+ "TYPE, LINE, ORDER_NBR_OD_DETAIL, "
									+ "ORDER_LINE_NBR_OD_DETAIL) Values(?,?,?,?,?,?)");
					Object[] params = { detailComment.getORDER_NBR(),
							detailComment.getORDER_LINE_NBR(),
							detailComment.getTYPE(), detailComment.getLINE(),
							orderNum, orderLineNum };
					insertOrUpdate(sqlQuery, params);
				} else {
					logger.debug("Detail Comment updating");
					String sqlQuery = new String(
							"update DELIVERY_OD_DETAIL_COMMENT SET TYPE=?, LINE=? where ORDER_NBR=? and ORDER_LINE_NBR=?");
					Object[] updateParams = { detailComment.getTYPE(),
							detailComment.getLINE(),
							detailComment.getORDER_NBR(),
							detailComment.getORDER_LINE_NBR() };
					insertOrUpdate(sqlQuery, updateParams);
				}
			}
		}
		logger.debug("END");
	}

	public List<IMY_MGOL_OD_DETAIL> getOrderDetails(String orderNum) {
		logger.debug("BEGIN");
		StringBuilder selectQuery = new StringBuilder(
				"select * from DELIVERY_DETAIL");
		if (orderNum != null)
			selectQuery.append(" where ORDER_NBR = " + orderNum);
		List<Object> orderDetailObjects = (List<Object>) getObjects(
				selectQuery.toString(), detailRowMapper);
		List<IMY_MGOL_OD_DETAIL> orderDetails = new ArrayList<IMY_MGOL_OD_DETAIL>();
		for (Iterator<Object> iterator = orderDetailObjects.iterator(); iterator
				.hasNext();) {
			IMY_MGOL_OD_DETAIL orderDetail = (IMY_MGOL_OD_DETAIL) iterator
					.next();
			logger.debug("OrderDetails are " + orderDetail.getBASE_UOM());
			orderDetails.add(orderDetail);
		}
		logger.debug("END");
		return orderDetails;
	}

	public List<IMY_MGOL_OD_HEADER> getOrderHeaders(String customerId) {
		logger.debug("BEGIN");
		List<IMY_MGOL_OD_HEADER> orderHeaderList = new ArrayList<IMY_MGOL_OD_HEADER>();
		StringBuilder selectQuery = new StringBuilder(
				"select * from DELIVERY_HEADER");
		if (customerId != null)
			selectQuery.append(" where SOLD_TO_COMPANY_CD = " + customerId);
		List<Object> objectsList = (List<Object>) getObjects(
				selectQuery.toString(), headerRowMapper);
		if (objectsList != null) {
			for (Object obj : objectsList) {
				IMY_MGOL_OD_HEADER iMyOdHeader = (IMY_MGOL_OD_HEADER) obj;
				orderHeaderList.add(iMyOdHeader);
			}
		}
		logger.debug("END");
		return orderHeaderList;
	}

	public IMY_MGOL_OD_HEADER getOrderHeader(String customerId, String orderNum) {
		logger.debug("BEGIN");
		IMY_MGOL_OD_HEADER orderHeader = null;
		StringBuilder selectQuery = new StringBuilder(
				"select * from DELIVERY_HEADER where DELVI_NBR=" + orderNum);
		if (customerId != null)
			selectQuery.append(" where SOLD_TO_COMPANY_CD = " + customerId);
		Object object = get(selectQuery.toString(), headerRowMapper);
		if (object != null) {
			orderHeader = (IMY_MGOL_OD_HEADER) object;
		}
		logger.debug("END");
		return orderHeader;
	}
	
	@SuppressWarnings("unchecked")
	public String getDelivNum(String customerId, String ordNum) {
		logger.debug("BEGIN");
		StringBuilder selectQuery = new StringBuilder(
				"select DELVI_NBR from DELIVERY_HEADER where ORDER_REF_NUM="
						+ ordNum);
		Object object = get(selectQuery.toString());
		logger.debug("END");
		if ((object != null)
				&& (((ArrayList<LinkedCaseInsensitiveMap<String>>) object).size() > 0) 
				&& (object.getClass() == ArrayList.class)) {
			List<LinkedCaseInsensitiveMap<String>> invNum = (ArrayList<LinkedCaseInsensitiveMap<String>>) object;
			LinkedCaseInsensitiveMap<String> obj = (LinkedCaseInsensitiveMap<String>) invNum
					.get(0);
			String DELVI_NBR = obj.get("DELVI_NBR");
			return DELVI_NBR;
		}
		return null;
	}

	public List<IMY_MGOL_SO_DETAIL_COMMENT> getODDetailComment(String orderNum,
			String orderLineNum) {
		logger.debug("BEGIN");
		StringBuilder selectQuery = new StringBuilder(
				"select * from DELIVERY_OD_DETAIL_COMMENT");
		if (orderNum != null && orderLineNum != null)
			selectQuery.append(" where ORDER_NBR_OD_DETAIL = " + orderNum
					+ " and ORDER_LINE_NBR_OD_DETAIL = " + orderLineNum);
		List<Object> odDetailCommentObjects = (List<Object>) getObjects(
				selectQuery.toString(), detailCommentRowMapper);
		List<IMY_MGOL_SO_DETAIL_COMMENT> odDetailComments = new ArrayList<IMY_MGOL_SO_DETAIL_COMMENT>();
		for (Iterator<Object> iterator = odDetailCommentObjects.iterator(); iterator
				.hasNext();) {
			IMY_MGOL_SO_DETAIL_COMMENT odDetailComment = (IMY_MGOL_SO_DETAIL_COMMENT) iterator
					.next();
			odDetailComments.add(odDetailComment);
		}
		logger.debug("END");
		return odDetailComments;
	}

	public List<IMY_MGOL_OD_ITEM_ATTACHM> getODItemAttachement(String orderNum) {
		logger.debug("BEGIN");
		String selectQuery = new String(
				"select * from DELIVERY_ITEM_ATTACHMENT");
		List<Object> odDetailItemAttachementObjects = (List<Object>) getObjects(
				selectQuery, detailItemAttachementRowMapper);
		List<IMY_MGOL_OD_ITEM_ATTACHM> odDetailItemAttachements = new ArrayList<IMY_MGOL_OD_ITEM_ATTACHM>();
		for (Iterator<Object> iterator = odDetailItemAttachementObjects
				.iterator(); iterator.hasNext();) {
			IMY_MGOL_OD_ITEM_ATTACHM odDetailItemAttachement = (IMY_MGOL_OD_ITEM_ATTACHM) iterator
					.next();
			odDetailItemAttachements.add(odDetailItemAttachement);
		}
		logger.debug("END");
		return odDetailItemAttachements;
	}

	public List<IMY_MGOL_OD_HEADER_COMMENT> getODHeaderCommentDetails(
			String orderNum) {
		logger.debug("BEGIN");
		StringBuilder selectQuery = new StringBuilder(
				"select * from DELIVERY_OD_HEADER_COMMENT");
		if (orderNum != null)
			selectQuery.append(" where DELVI_NBR_OD_HEADER = " + orderNum);
		List<Object> odHeaderCommentObjects = (List<Object>) getObjects(
				selectQuery.toString(), headerCommentRowMapper);
		List<IMY_MGOL_OD_HEADER_COMMENT> odHeaderComments = new ArrayList<IMY_MGOL_OD_HEADER_COMMENT>();
		for (Iterator<Object> iterator = odHeaderCommentObjects.iterator(); iterator
				.hasNext();) {
			IMY_MGOL_OD_HEADER_COMMENT odHeaderComment = (IMY_MGOL_OD_HEADER_COMMENT) iterator
					.next();
			odHeaderComments.add(odHeaderComment);
		}
		logger.debug("END");
		return odHeaderComments;
	}
}
