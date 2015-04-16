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
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_HEADER_COMMEN;
import com.mygoconsulting.mytracking.model.IMY_MGOL_INV_ITEM_ATMT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

public class InvoiceXMLParser extends BaseParser implements IParser {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	String parent;

	public IDOC parse(String fileName) {
		logger.debug("BEGIN");
		XMLStreamReader reader = super.getReader(fileName);
		IDOC idoc = null;
		EDI_DC40 ediDC40 = null;
		IMY_MGOL_INV_HEADER myINVHeader = null;
		List<IMY_MGOL_INV_HEADER_COMMEN> myINVHeaderComments = new ArrayList<IMY_MGOL_INV_HEADER_COMMEN>();
		IMY_MGOL_INV_HEADER_COMMEN myINVHeaderComment = null;
		List<IMY_MGOL_INV_DETAIL> myINVDetails = new ArrayList<IMY_MGOL_INV_DETAIL>();
		IMY_MGOL_INV_DETAIL myINVDetail = null;
		List<IMY_MGOL_SO_DETAIL_COMMENT> mySODetailComments = null;
		IMY_MGOL_SO_DETAIL_COMMENT mySODetailComment = null;
		IMY_MGOL_INV_ITEM_ATMT myInvItemAtmt = null;
		
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
					} else if ("_-IMY_-MGOL_INV_HEADER".equals(reader.getLocalName())) {
						myINVHeader = new IMY_MGOL_INV_HEADER();
						myINVHeader.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_INV_HEADER";
					} else if ("_-IMY_-MGOL_INV_HEADER_COMMEN"
							.equals(reader.getLocalName())) {
						myINVHeaderComment = new IMY_MGOL_INV_HEADER_COMMEN();
						myINVHeaderComment.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_INV_HEADER_COMMEN";
					} else if("_-IMY_-MGOL_INV_DETAIL".equals(reader.getLocalName())){
						mySODetailComments = new ArrayList<IMY_MGOL_SO_DETAIL_COMMENT>();
						myINVDetail = new IMY_MGOL_INV_DETAIL();
						myINVDetail.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_INV_DETAIL";
					} else if("_-IMY_-MGOL_SO_DETAIL_COMMENT".equals(reader.getLocalName())){
						mySODetailComment = new IMY_MGOL_SO_DETAIL_COMMENT();
						mySODetailComment.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_SO_DETAIL_COMMENT";
					} else if ("_-IMY_-MGOL_INV_ITEM_ATMT".equals(reader.getLocalName())){
						myInvItemAtmt = new IMY_MGOL_INV_ITEM_ATMT();
						myInvItemAtmt.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_INV_ITEM_ATMT";
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
				case XMLStreamConstants.END_ELEMENT:
				  switch (reader.getLocalName()) {
				  case "SOLD_FROM_COMPANY_CD":
						myINVHeader.setSOLD_FROM_COMPANY_CD(tagContent);
						break;
					case "SOLD_TO_COMPANY_CD":
						myINVHeader.setSOLD_TO_COMPANY_CD(tagContent);
						break;
					case "SHIP_TO_COMPANY_CD":
						myINVHeader.setBILL_TO_COMPANY_CD(tagContent);
						break;
					case "BILL_TO_COMPANY_CD":
						myINVHeader.setBILL_TO_COMPANY_CD(tagContent);
						break;
					case "INVOI_NBR":
						myINVHeader.setINVOI_NBR(tagContent);
						break;
					case "ORDER_PLANT_CD":
						myINVHeader.setORDER_PLANT_CD(tagContent);
						break;
					case "ORDER_STATUS_CD":
						myINVHeader.setORDER_STATUS_CD(tagContent);
						break;
					case "CUSTOMER_PO":
						myINVHeader.setCUSTOMER_PO(tagContent);
						break;
					case "END_USER_COMPANY_CD":
						myINVHeader.setEND_USER_COMPANY_CD(tagContent);
						break;
					case "OVERRIDE_COMPANY_NAME":
						myINVHeader.setOVERRIDE_COMPANY_NAME(tagContent);
						break;
					case "OVERRIDE_ADDRESS1":
						myINVHeader.setOVERRIDE_ADDRESS1(tagContent);
						break;
					case "OVERRIDE_ADDRESS2":
						myINVHeader.setOVERRIDE_ADDRESS2(tagContent);
						break;
					case "OVERRIDE_CITY":
						myINVHeader.setOVERRIDE_CITY(tagContent);
						break;
					case "OVERRIDE_STATE":
						myINVHeader.setOVERRIDE_STATE(tagContent);
						break;
					case "OVERRIDE_ZIP":
						myINVHeader.setOVERRIDE_ZIP(tagContent);
						break;
					case "ORDER_REF_NUM":
						myINVHeader.setORDER_REF_NUM(tagContent);
						break;
					case "BILLED_PRICE":
						myINVHeader.setBILLED_PRICE(tagContent);
						break;
					case "CURRENCY":
						myINVHeader.setCURRENCY(tagContent);
						break;
					case "CREATE_DATE":
						myINVHeader.setCREATE_DATE(tagContent);
						break;
					case "ORDER_NBR":
						if (parent.equals("_-IMY_-MGOL_INV_HEADER_COMMEN")) {
							myINVHeaderComment.setORDER_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_INV_DETAIL")) {
							myINVDetail.setORDER_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setORDER_NBR(tagContent);
						}
						break;
					case "TYPE":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setTYPE(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_INV_HEADER_COMMEN")){
							myINVHeaderComment.setTYPE(tagContent);
						} 
						break;
					case "LINE":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setLINE(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_INV_HEADER_COMMEN")){
							myINVHeaderComment.setLINE(tagContent);
						}
						break;
					case "ORDER_LINE_NBR":
						if(parent.equals("_-IMY_-MGOL_SO_DETAIL_COMMENT")) {
							mySODetailComment.setORDER_LINE_NBR(tagContent);
						} else if(parent.equals("_-IMY_-MGOL_INV_DETAIL")) {
							myINVDetail.setORDER_LINE_NBR(tagContent);
						}
						break;
					case "PRODUCT_NBR":
						myINVDetail.setPRODUCT_NBR(tagContent);
						break;
					case "OVERRIDE_PRODUCT":
						myINVDetail.setOVERRIDE_PRODUCT(tagContent);
						break;
					case "ITEM_CAT":
						myINVDetail.setITEM_CAT(tagContent);
						break;
					case "BASE_UOM":
						myINVDetail.setBASE_UOM(tagContent);
						break;
					case "NET_VAL":
						myINVDetail.setNET_VAL(tagContent);
						break;
					case "DOKAR":
						myInvItemAtmt.setDOKAR(tagContent);
						break;
					case "DOKNR":
						myInvItemAtmt.setDOKNR(tagContent);
						break;
					case "DOKVR":
						myInvItemAtmt.setDOKVR(tagContent);
						break;
					case "DOKTL":
						myInvItemAtmt.setDOKTL(tagContent);
						break;
					case "OBJKY":
						myInvItemAtmt.setOBJKY(tagContent);
						break;
					case "_-IMY_-MGOL_INV_HEADER_COMMEN":
						myINVHeaderComments.add(myINVHeaderComment);
						parent = null;
					    break;
					case "_-IMY_-MGOL_INV_HEADER":
						myINVHeader.setIMY_MGOL_INV_HEADER_COMMEN(myINVHeaderComments);
						idoc.setIMY_MGOL_INV_HEADER(myINVHeader);
						parent = null;
						break;
					case "_-IMY_-MGOL_SO_DETAIL_COMMENT":
						mySODetailComments.add(mySODetailComment);
						parent = null;
					    break;
					case "_-IMY_-MGOL_INV_DETAIL":
						myINVDetail.setIMY_MGOL_INV_ITEM_ATMT(myInvItemAtmt);
						myINVDetail.setIMY_MGOL_SO_DETAIL_COMMENT(mySODetailComments);
						myINVDetails.add(myINVDetail);
						idoc.setIMY_MGOL_INV_DETAIL(myINVDetails);
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
