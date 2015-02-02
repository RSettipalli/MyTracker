package com.mygoconsulting.mytracking.parse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_COMPANY;
import com.mygoconsulting.mytracking.model.IMY_SHIP_POINT;

public class CompanyParser extends BaseParser implements IParser {

	String parent;

	public IDOC parse(String fileName) {

		XMLStreamReader reader = super.getReader(fileName);
		IMY_COMPANY myCompany = null;
		List<IMY_COMPANY> myCompanies = new ArrayList<IMY_COMPANY>();		
		IMY_SHIP_POINT shipPoint = null;
		List<IMY_SHIP_POINT> shipPoints = new ArrayList<IMY_SHIP_POINT>();
		IDOC idoc = null;
		
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
					} else if ("_-IMY_-COMPANY".equals(reader.getLocalName())) {
						myCompany = new IMY_COMPANY();
						myCompany.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-COMPANY";
					} else if ("_-IMY_-SHIP_POINT"
							.equals(reader.getLocalName())) {
						shipPoint = new IMY_SHIP_POINT();
						shipPoint.setSEGMENT(reader.getAttributeValue(0));
						parent = "_-IMY_-SHIP_POINT";
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "BUKRS":
						if (parent.equals("_-IMY_-SHIP_POINT")) {
							shipPoint.setBUKRS(tagContent);
						} else {
							myCompany.setBUKRS(tagContent);
						}
						break;
					case "BUTXT":
						myCompany.setBUTXT(tagContent);
						break;
					case "ORT01":
						myCompany.setORT01(tagContent);
						break;
					case "WAERS":
						myCompany.setWAERS(tagContent);
						break;
					case "SPRAS":
						myCompany.setSPRAS(tagContent);
						break;
					case "ADRNR":
						myCompany.setADRNR(tagContent);
						break;
					case "ADDRESS1":

						if (parent.equals("_-IMY_-SHIP_POINT")) {
							shipPoint.setADDRESS1(tagContent);
						} else {
							myCompany.setADDRESS1(tagContent);
						}
						break;
					case "ADDRESS2":

						if (parent.equals("_-IMY_-SHIP_POINT")) {
							shipPoint.setADDRESS2(tagContent);
						} else {
							myCompany.setADDRESS2(tagContent);
						}
						break;
					case "COUNTRY":
						if (parent.equals("_-IMY_-SHIP_POINT")) {
							shipPoint.setCOUNTRY(tagContent);
						} else {
							myCompany.setCOUNTRY(tagContent);
						}
						break;
					case "ZIP":
						if (parent.equals("_-IMY_-SHIP_POINT")) {
							shipPoint.setZIP(tagContent);
						} else {
							myCompany.setZIP(tagContent);
						}
						break;
					case "PHONE":
						if (parent.equals("_-IMY_-SHIP_POINT")) {
							shipPoint.setPHONE(tagContent);
						} else {
							myCompany.setPHONE(tagContent);
						}
						break;
					case "FAX":
						if (parent.equals("_-IMY_-SHIP_POINT")) {
							shipPoint.setFAX(tagContent);
						} else {
							myCompany.setFAX(tagContent);
						}
						break;
					case "SHIP_POINT":
						shipPoint.setSHIP_POINT(tagContent);
						break;
					case "CITY":
						shipPoint.setCITY(tagContent);
						break;
					case "LANGUAGE":
						shipPoint.setLANGUAGE(tagContent);
						break;
					case "_-IMY_-SHIP_POINT":
						shipPoints.add(shipPoint);						
						parent = null;
						break;
					case "_-IMY_-COMPANY":
						myCompany.setIMY_SHIP_POINT(shipPoints);
						myCompanies.add(myCompany);
						idoc.setIMY_COMPANY(myCompanies);
						parent = null;
						break;
					case "IDOC":
						idoc.setIMY_COMPANY(myCompanies);
						parent = null;
						break;
					}

					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return idoc;
	}
}
