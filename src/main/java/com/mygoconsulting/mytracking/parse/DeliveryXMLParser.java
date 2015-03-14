package com.mygoconsulting.mytracking.parse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_OD_ITEM_ATTACHM;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

public class DeliveryXMLParser extends BaseParser implements IParser {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	String parent;

	public IDOC parse(String fileName) {
		logger.debug("BEGIN");
		XMLStreamReader reader = super.getReader(fileName);
		IDOC idoc = null;
		IMY_MGOL_OD_HEADER myODHeader = null;
		List<IMY_MGOL_OD_HEADER_COMMENT> myODHeaderComments = new ArrayList<IMY_MGOL_OD_HEADER_COMMENT>();
		IMY_MGOL_OD_HEADER_COMMENT myODHeaderComment = null;
		List<IMY_MGOL_OD_DETAIL> myODDetails = new ArrayList<IMY_MGOL_OD_DETAIL>();
		IMY_MGOL_OD_DETAIL myODDetail = null;
		IMY_MGOL_SO_DETAIL_COMMENT mySODetailComment = null;
		List<IMY_MGOL_SO_DETAIL_COMMENT> mySODetailComments = null;
		IMY_MGOL_OD_ITEM_ATTACHM myODItemAttachm = null;
		
		String tagContent = null;
		try {
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if("IDOC".equals(reader.getLocalName())){
						idoc = new IDOC();
						idoc.setBEGIN(reader.getAttributeValue(0));
						parent = "IDOC";
					} else if ("_-IMY_-MGOL_OD_HEADER".equals(reader.getLocalName())) {
						myODHeader = new IMY_MGOL_OD_HEADER();
						myODHeader.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_OD_HEADER";
					} else if ("_-IMY_-MGOL_OD_HEADER_COMMENT".equals(reader.getLocalName())) {
						System.out.println("Delivery Header Comment");
						myODHeaderComment = new IMY_MGOL_OD_HEADER_COMMENT();
						myODHeaderComment.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_OD_HEADER_COMMENT";
					} else if("_-IMY_-MGOL_OD_DETAIL".equals(reader.getLocalName())){
						mySODetailComments = new ArrayList<IMY_MGOL_SO_DETAIL_COMMENT>();
						myODDetail = new IMY_MGOL_OD_DETAIL();
						myODDetail.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_OD_DETAIL";
					} else if("_-IMY_-MGOL_SO_DETAIL_COMMENT".equals(reader.getLocalName())){
						mySODetailComment = new IMY_MGOL_SO_DETAIL_COMMENT();
						mySODetailComment.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_SO_DETAIL_COMMENT";
					} else if("_-IMY_-MGOL_OD_ITEM_ATTACHM".equals(reader.getLocalName())){
						myODItemAttachm = new IMY_MGOL_OD_ITEM_ATTACHM();
						myODItemAttachm.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_OD_ITEM_ATTACHM";
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
				case XMLStreamConstants.END_ELEMENT:
				  switch (reader.getLocalName()) {
				  
				  case "IDOC":
					  idoc.setIMY_MGOL_OD_HEADER(myODHeader);
					  idoc.setIMY_MGOL_OD_DETAIL(myODDetails);
					  break;
					case "SOLD_TO_COMPANY_CD":
						myODHeader.setSOLD_TO_COMPANY_CD(tagContent);
						break;
					case "SHIP_TO_COMPANY_CD":
						myODHeader.setSHIP_TO_COMPANY_CD(tagContent);
						break;
					case "DELVI_NBR":
						myODHeader.setDELVI_NBR(tagContent);
						break;
					case "ORDER_PLANT_CD":
						myODHeader.setORDER_PLANT_CD(tagContent);
						break;
					case "ORDER_STATUS_CD":
						myODHeader.setORDER_STATUS_CD(tagContent);
						break;
					case "CUSTOMER_PO":
						myODHeader.setCUSTOMER_PO(tagContent);
						break;
					case "END_USER":
						myODHeader.setEND_USER(tagContent);
						break;
					case "END_USER_COMPANY_CD":
						myODHeader.setEND_USER_COMPANY_CD(tagContent);
						break;
					case "OVERRIDE_COMPANY_NAME":
						myODHeader.setOVERRIDE_COMPANY_NAME(tagContent);
						break;
					case "OVERRIDE_ADDRESS1":
						myODHeader.setOVERRIDE_ADDRESS1(tagContent);
						break;
					case "OVERRIDE_ADDRESS2":
						myODHeader.setOVERRIDE_ADDRESS2(tagContent);
						break;
					case "OVERRIDE_CITY":
						myODHeader.setOVERRIDE_CITY(tagContent);
						break;
					case "OVERRIDE_STATE":
						myODHeader.setOVERRIDE_STATE(tagContent);
						break;
					case "OVERRIDE_ZIP":
						myODHeader.setOVERRIDE_ZIP(tagContent);
						break;
					case "ORDER_REF_NUM":
						myODHeader.setORDER_REF_NUM(tagContent);
						break;
					case "ORDER_NBR":
						if (parent.equals("_-IMY_-MGOL_OD_HEADER_COMMENT")) {
							myODHeaderComment.setORDER_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_OD_DETAIL")) {
							myODDetail.setORDER_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setORDER_NBR(tagContent);
						}
						break;
					case "TYPE":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setTYPE(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_OD_HEADER_COMMENT")){
							myODHeaderComment.setTYPE(tagContent);
						}
						break;
					case "LINE":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setLINE(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_OD_HEADER_COMMENT")){
							myODHeaderComment.setLINE(tagContent);
						}
						break;
					case "ORDER_LINE_NBR":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setORDER_LINE_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_OD_DETAIL")) {
							myODDetail.setORDER_LINE_NBR(tagContent);
						}
						break;
					case "PRODUCT_NBR":
						myODDetail.setPRODUCT_NBR(tagContent);
						break;
					case "OVERRIDE_PRODUCT":
						myODDetail.setOVERRIDE_PRODUCT(tagContent);
						break;
					case "ITEM_CAT":
						myODDetail.setITEM_CAT(tagContent);
						break;
					case "BASE_UOM":
						myODDetail.setBASE_UOM(tagContent);
						break;
					case "NET_VAL":
						myODDetail.setNET_VAL(tagContent);
						break;
					case "ORD_QTY":
						myODDetail.setORD_QTY(tagContent);
						break;
					case "DOKAR":
						myODItemAttachm.setDOKAR(tagContent);
						break;
					case "DOKNR":
						myODItemAttachm.setDOKNR(tagContent);
						break;
					case "DOKVR":
						myODItemAttachm.setDOKVR(tagContent);
						break;
					case "DOKTL":
						myODItemAttachm.setDOKTL(tagContent);
						break;
					case "OBJKY":
						myODItemAttachm.setOBJKY(tagContent);
						break;
					case "_-IMY_-MGOL_OD_HEADER_COMMENT":
						System.out.println("Header comment added");
						myODHeaderComments.add(myODHeaderComment);
						parent = null;
					    break;
					case "_-IMY_-MGOL_OD_HEADER":
						myODHeader.setIMY_MGOL_OD_HEADER_COMMENT(myODHeaderComments);
						idoc.setIMY_MGOL_OD_HEADER(myODHeader);
						parent = null;
						break;
					case "_-IMY_-MGOL_SO_DETAIL_COMMENT":
						mySODetailComments.add(mySODetailComment);
						parent = null;
					    break;
					case "_-IMY_-MGOL_OD_DETAIL":
						myODDetail.setIMY_MGOL_OD_ITEM_ATTACHM(myODItemAttachm);
						myODDetail.setIMY_MGOL_SO_DETAIL_COMMENT(mySODetailComments);
						myODDetails.add(myODDetail);
						idoc.setIMY_MGOL_OD_DETAIL(myODDetails);
						parent = null;
						break;
					}
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		logger.debug("END");
		return idoc;
	}
}
