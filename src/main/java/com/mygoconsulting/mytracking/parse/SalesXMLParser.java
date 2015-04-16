package com.mygoconsulting.mytracking.parse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.EDI_DC40;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_ITEM_ATTACHM;

public class SalesXMLParser extends BaseParser implements IParser {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	String parent;

	public IDOC parse(String fileName) {
		logger.debug("BEGIN");
		XMLStreamReader reader = super.getReader(fileName);
		IDOC idoc = null;
		EDI_DC40 ediDC40 = null;
		IMY_MGOL_SO_HEADER mySOHeader = null;
		IMY_MGOL_SO_HEADER_COMMENT mySOHeaderComment = null;
		List<IMY_MGOL_SO_HEADER_COMMENT> mySOHeaderComments = new ArrayList<IMY_MGOL_SO_HEADER_COMMENT>();
		List<IMY_MGOL_SO_DETAIL> mySODetails = new ArrayList<IMY_MGOL_SO_DETAIL>();
		IMY_MGOL_SO_DETAIL mySODetail = null;
		IMY_MGOL_SO_DETAIL_COMMENT mySODetailComment = null;
		List<IMY_MGOL_SO_DETAIL_COMMENT> mySODetailComments = null;
		IMY_MGOL_SO_ITEM_ATTACHM mySOItemAttachm = null;
		
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
					} else if ("EDI_DC40".equals(reader.getLocalName())) {
						ediDC40 = new EDI_DC40();
						ediDC40.setSEGMENT(reader.getAttributeValue(0));
						parent = "EDI_DC40";
					} else if ("_-IMY_-MGOL_SO_HEADER".equals(reader.getLocalName())) {
						mySOHeader = new IMY_MGOL_SO_HEADER();
						mySOHeader.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_SO_HEADER";
					} else if ("_-IMY_-MGOL_SO_HEADER_COMMENT".equals(reader.getLocalName())) {
						mySOHeaderComment = new IMY_MGOL_SO_HEADER_COMMENT();
						mySOHeaderComment.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_SO_HEADER_COMMENT";
					} else if("_-IMY_-MGOL_SO_DETAIL".equals(reader.getLocalName())){
						mySODetailComments = new ArrayList<IMY_MGOL_SO_DETAIL_COMMENT>();
						mySODetail = new IMY_MGOL_SO_DETAIL();
						mySODetail.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_SO_DETAIL";
					} else if("_-IMY_-MGOL_SO_DETAIL_COMMENT".equals(reader.getLocalName())){
						mySODetailComment = new IMY_MGOL_SO_DETAIL_COMMENT();
						mySODetailComment.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_SO_DETAIL_COMMENT";
					} else if("_-IMY_-MGOL_SO_ITEM_ATTACHM".equals(reader.getLocalName())){
						mySOItemAttachm = new IMY_MGOL_SO_ITEM_ATTACHM();
						mySOItemAttachm.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_SO_ITEM_ATTACHM";
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
				case XMLStreamConstants.END_ELEMENT:
				  switch (reader.getLocalName()) {
					case "ORDER_TYPE":
						mySOHeader.setORDER_TYPE(tagContent);						
						break;
					case "SOLD_FROM_COMPANY_CD":
						mySOHeader.setSOLD_FROM_COMPANY_CD(tagContent);
						break;
					case "SOLD_TO_COMPANY_CD":
						mySOHeader.setSOLD_TO_COMPANY_CD(tagContent);
						break;
					case "SHIP_TO_COMPANY_CD":
						mySOHeader.setSHIP_TO_COMPANY_CD(tagContent);
						break;
					case "BILL_TO_COMPANY_CD":
						mySOHeader.setBILL_TO_COMPANY_CD(tagContent);
						break;
					case "ORDER_NBR":
						if (parent.equals("_-IMY_-MGOL_SO_HEADER_COMMENT")) {
							mySOHeaderComment.setORDER_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_DETAIL")) {
							mySODetail.setORDER_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setORDER_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_HEADER")){
							mySOHeader.setORDER_NBR(tagContent);
						}
						break;
					case "ORDER_NBR_VER":
						mySOHeader.setORDER_NBR_VER(tagContent);
						break;
					case "ORDER_PLANT_CD":
						mySOHeader.setORDER_PLANT_CD(tagContent);
						break;
					case "ORDER_STATUS_CD":
						mySOHeader.setORDER_STATUS_CD(tagContent);
						break;
					case "CUSTOMER_PO":
						mySOHeader.setCUSTOMER_PO(tagContent);
						break;
					case "END_USER":
						mySOHeader.setEND_USER(tagContent);
						break;
					case "END_USER_COMPANY_CD":
						mySOHeader.setEND_USER_COMPANY_CD(tagContent);
						break;
					case "OVERRIDE_COMPANY_NAME":
						mySOHeader.setOVERRIDE_COMPANY_NAME(tagContent);
						break;
					case "OVERRIDE_ADDRESS1":
						mySOHeader.setOVERRIDE_ADDRESS1(tagContent);
						break;
					case "OVERRIDE_ADDRESS2":
						mySOHeader.setOVERRIDE_ADDRESS2(tagContent);
						break;
					case "OVERRIDE_CITY":
						mySOHeader.setOVERRIDE_CITY(tagContent);
						break;
					case "OVERRIDE_STATE":
						mySOHeader.setOVERRIDE_STATE(tagContent);
						break;
					case "OVERRIDE_ZIP":
						mySOHeader.setOVERRIDE_ZIP(tagContent);
						break;
					case "TOTAL_PRICE":
						mySOHeader.setTOTAL_PRICE(tagContent);
						break;
					case "CURRENCY":
						mySOHeader.setCURRENCY(tagContent);
						break;
					case "CREATE_DATE":
						mySOHeader.setCREATE_DATE(tagContent);
						break;
					case "TYPE":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setTYPE(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_HEADER_COMMENT")){
							mySOHeaderComment.setTYPE(tagContent);
						}
						break;
					case "LINE":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setLINE(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_HEADER_COMMENT")){
							mySOHeaderComment.setLINE(tagContent);
						}
						break;
					case "ORDER_LINE_NBR":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setORDER_LINE_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_DETAIL")) {
							mySODetail.setORDER_LINE_NBR(tagContent);
						}
						break;
					case "PRODUCT_NBR":
						mySODetail.setPRODUCT_NBR(tagContent);
						break;
					case "OVERRIDE_PRODUCT":
						mySODetail.setOVERRIDE_PRODUCT(tagContent);
						break;
					case "ORDER_LINE_STATUS":
						mySODetail.setORDER_LINE_STATUS(tagContent);
						break;
					case "ITEM_CAT":
						mySODetail.setITEM_CAT(tagContent);
						break;
					case "BASE_UOM":
						mySODetail.setBASE_UOM(tagContent);
						break;
					case "NET_VAL":
						mySODetail.setNET_VAL(tagContent);
						break;
					case "ORD_QTY":
						mySODetail.setORD_QTY(tagContent);
						break;
					case "ORD_UOM_DESC":
						mySODetail.setORD_UOM_DESC(tagContent);
						break;
					case "BASE_UOM_DESC":
						mySODetail.setBASE_UOM_DESC(tagContent);
						break;
					case "BASE_PRICE":
						mySODetail.setBASE_PRICE(tagContent);
						break;
					case "DOKAR":
						mySOItemAttachm.setDOKAR(tagContent);
						break;
					case "DOKNR":
						mySOItemAttachm.setDOKNR(tagContent);
						break;
					case "DOKVR":
						mySOItemAttachm.setDOKVR(tagContent);
						break;
					case "DOKTL":
						mySOItemAttachm.setDOKTL(tagContent);
						break;
					case "OBJKY":
						mySOItemAttachm.setOBJKY(tagContent);
						break;
					case "_-IMY_-MGOL_SO_HEADER_COMMENT":
						mySOHeaderComments.add(mySOHeaderComment);
						parent = null;
					    break;
					case "_-IMY_-MGOL_SO_HEADER":
						mySOHeader.setIMY_MGOL_SO_HEADER_COMMENT(mySOHeaderComments);
						idoc.setIMY_MGOL_SO_HEADER(mySOHeader);
						parent = null;
						break;
					case "_-IMY_-MGOL_SO_DETAIL_COMMENT":
						mySODetailComments.add(mySODetailComment);
						parent = null;
					    break;
					case "_-IMY_-MGOL_SO_DETAIL":
						mySODetail.setIMY_MGOL_SO_DETAIL_COMMENT(mySODetailComments);
						mySODetail.setIMY_MGOL_SO_ITEM_ATTACHM(mySOItemAttachm);
						mySODetails.add(mySODetail);
						idoc.setIMY_MGOL_SO_DETAIL(mySODetails);
						parent = null;
						break;
					case "TABNAM":
						ediDC40.setTABNAM(tagContent);
						break;
					case "MANDT":
						ediDC40.setMANDT(tagContent);
						break;
					case "DOCNUM":
						ediDC40.setDOCNUM(tagContent);
						break;
					case "DOCREL":
						ediDC40.setDOCREL(tagContent);
						break;
					case "STATUS":
						ediDC40.setSTATUS(tagContent);
						break;
					case "DIRECT":
						ediDC40.setDIRECT(tagContent);
						break;
					case "OUTMOD":
						ediDC40.setOUTMOD(tagContent);
						break;
					case "IDOCTYP":
						ediDC40.setIDOCTYP(tagContent);
						break;
					case "MESTYP":
						ediDC40.setMESTYP(tagContent);
						break;
					case "SNDPOR":
						ediDC40.setSNDPOR(tagContent);
						break;
					case "SNDPRT":
						ediDC40.setSNDPRT(tagContent);
						break;
					case "SNDPRN":
						ediDC40.setSNDPRN(tagContent);
						break;
					case "RCVPOR":
						ediDC40.setRCVPOR(tagContent);
						break;
					case "RCVPRT":
						ediDC40.setRCVPRT(tagContent);
						break;
					case "RCVPRN":
						ediDC40.setRCVPRN(tagContent);
						break;
					case "CREDAT":
						ediDC40.setCREDAT(tagContent);
						break;
					case "CRETIM":
						ediDC40.setCRETIM(tagContent);
						break;
					case "SERIAL":
						ediDC40.setSERIAL(tagContent);
						break;
					case "EDI_DC40":
						idoc.setEDI_DC40(ediDC40);
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
