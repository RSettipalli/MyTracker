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
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUSTOMER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_CUST_BANK;

public class CustomerXMLParser extends BaseParser implements IParser {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	String parent;

	@Override
	public IDOC parse(String fileName) {
		logger.debug("BEGIN");
		XMLStreamReader reader = super.getReader(fileName);
		IMY_MGOL_CUSTOMER myCustomer = null;
		IMY_MGOL_CUST_BANK myCustBank = null;
		EDI_DC40 ediDC40 = null;
		String tagContent = null;
		List<IMY_MGOL_CUSTOMER> customerList = null;
		IDOC doc = null;
		try {
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("IDOC".equals(reader.getLocalName())) {
						doc = new IDOC();
						customerList = new ArrayList<IMY_MGOL_CUSTOMER>();
						parent = "IDOC";
					} else if ("EDI_DC40".equals(reader.getLocalName())) {
						ediDC40 = new EDI_DC40();
						ediDC40.setSEGMENT(reader.getAttributeValue(0));
						parent = "EDI_DC40";
					} else if ("_-IMY_-MGOL_CUSTOMER".equals(reader
							.getLocalName())) {
						myCustomer = new IMY_MGOL_CUSTOMER();
						parent = "_-IMY_-MGOL_CUSTOMER";
					} else if ("_-IMY_-MGOL_CUST_BANK".equals(reader
							.getLocalName())) {
						myCustBank = new IMY_MGOL_CUST_BANK();
						myCustBank.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-MGOL_CUST_BANK";
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "KUNNR":
						myCustomer.setKUNNR(tagContent);
						break;
					case "NAME1":
						myCustomer.setNAME1(tagContent);
						break;
					case "STREET":
						myCustomer.setSTREET(tagContent);
						break;
					case "STR_SUPPL3":
						myCustomer.setSTR_SUPPL3(tagContent);
						break;
					case "CITY":
						myCustomer.setCITY(tagContent);
						break;
					case "REGION":
						myCustomer.setREGION(tagContent);
						break;
					case "COUNTRY":
						myCustomer.setCOUNTRY(tagContent);
						break;
					case "TELEPHONE":
						myCustomer.setTELEPHONE(tagContent);
						break;
					case "FAX":
						myCustomer.setFAX(tagContent);
						break;
					case "POSTL_COD1":
						myCustomer.setPOSTL_COD1(tagContent);
						break;
					case "CUST_NUMBER":
						myCustBank.setCUST_NUMBER(tagContent);
						break;
					case "BANK_COUNTRY":
						myCustBank.setBANK_COUNTRY(tagContent);
						break;
					case "BANK_KEY":
						myCustBank.setBANK_KEY(tagContent);
						break;
					case "BANK_ACC":
						myCustBank.setBANK_ACC(tagContent);
						break;
					case "BANK_TYPE":
						myCustBank.setBANK_TYPE(tagContent);
						break;
					case "_-IMY_-MGOL_CUST_BANK":
						myCustomer.setIMY_MGOL_CUST_BANK(myCustBank);
						parent = null;
						break;
					case "_-IMY_-MGOL_CUSTOMER":
						customerList.add(myCustomer);
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
						parent = null;
						break;
					case "IDOC":
						doc.setEDI_DC40(ediDC40);
						doc.setIMY_MGOL_CUSTOMER(customerList);
						parent = null;
						break;
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		logger.debug("END");
		return doc;
	}
}
