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
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;
import com.mygoconsulting.mytracking.model.IMY_MAT_STORAGE_DETIALS;
import com.mygoconsulting.mytracking.model.IMY_MAT_WERKS;

public class MaterialXMLParser extends BaseParser implements IParser {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	String parent;

	@Override
	public IDOC parse(String fileName) {
		logger.debug("BEGIN");
		XMLStreamReader reader = super.getReader(fileName);
		IDOC idoc = new IDOC();
		EDI_DC40 ediDC40 = null;
		List<IMY_MAT_ONLINE> materialOnlineList = new ArrayList<IMY_MAT_ONLINE>();
		// List<IMY_MAT_WERKS> materialPlantList = new
		// ArrayList<IMY_MAT_WERKS>();
		// List<IMY_MAT_STORAGE_DETIALS> materialOnlineList = new
		// ArrayList<IMY_MAT_STORAGE_DETIALS>();
		IMY_MAT_ONLINE materialOnline = null;
		IMY_MAT_WERKS materialPlant = null;
		IMY_MAT_STORAGE_DETIALS materialStorage = null;
		String tagContent = null;
		try {
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("_-IMY_-MAT_ONLINE".equals(reader.getLocalName())) {
						materialOnline = new IMY_MAT_ONLINE();
						parent = "_-IMY_-MAT_ONLINE";
					} else if ("_-IMY_-MAT_WERKS".equals(reader.getLocalName())) {
						materialPlant = new IMY_MAT_WERKS();
						parent = "_-IMY_-MAT_WERKS";
					} else if ("_-IMY_-MAT_STORAGE_DETIALS".equals(reader
							.getLocalName())) {
						materialStorage = new IMY_MAT_STORAGE_DETIALS();
						parent = "_-IMY_-MAT_STORAGE_DETIALS";
					} else if ("EDI_DC40".equals(reader.getLocalName())) {
						ediDC40 = new EDI_DC40();
						ediDC40.setSEGMENT(reader.getAttributeValue(0));
						parent = "EDI_DC40";
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "MATERIAL":
						if (parent.equals("_-IMY_-MAT_STORAGE_DETIALS")) {
							materialStorage.setMATERIAL(tagContent);
						} else {
							materialOnline.setMATERIAL(tagContent);
						}

						break;
					case "MAT_DESC":
						materialOnline.setMAT_DESC(tagContent);
						break;
					case "UOM":
						materialOnline.setUOM(tagContent);
						break;
					case "STOCK":
						materialOnline.setSTOCK(tagContent);
						break;
					case "BOM":
						materialOnline.setBOM(tagContent);
						break;
					case "MAT_TYPE":
						materialOnline.setMAT_TYPE(tagContent);
						break;
					case "GROSS_WEIGHT":
						materialOnline.setGROSS_WEIGHT(tagContent);
						break;
					case "NET_WEIGHT":
						materialOnline.setNET_WEIGHT(tagContent);
						break;
					case "MATERIAL_GROUP":
						materialOnline.setMATERIAL_GROUP(tagContent);
						break;
					case "_-IMY_-MAT_WERKS":
						materialOnline.setIMY_MAT_WERKS(materialPlant);
						break;
					case "PLANT":
						if (parent.equals("_-IMY_-MAT_STORAGE_DETIALS")) {
							materialStorage.setPLANT(tagContent);
						} else {
							materialPlant.setPLANT(tagContent);
						}
						break;
					case "MRP_TYPE":
						materialPlant.setMRP_TYPE(tagContent);
						break;
					case "MRP_CONT":
						materialPlant.setMRP_CONT(tagContent);
						break;
					case "_-IMY_-MAT_STORAGE_DETIALS":
						materialPlant
								.setIMY_MAT_STORAGE_DETIALS(materialStorage);
						parent = null;
						break;
					case "STO_LOCATION":
						materialStorage.setSTO_LOCATION(tagContent);
						break;
					case "MAINT_STATUS":
						if (parent.equals("_-IMY_-MAT_STORAGE_DETIALS")) {
							materialStorage.setMAINT_STATUS(tagContent);
						} else {
							materialPlant.setMAINT_STATUS(tagContent);
						}
						break;
					case "STOC_IN_QLTY_INS":
						materialStorage.setSTOC_IN_QLTY_INS(tagContent);
						break;
					case "_-IMY_-MAT_ONLINE":
						materialOnlineList.add(materialOnline);
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
			idoc.setIMY_MAT_ONLINE_List(materialOnlineList);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		logger.debug("END");
		return idoc;
	}
}