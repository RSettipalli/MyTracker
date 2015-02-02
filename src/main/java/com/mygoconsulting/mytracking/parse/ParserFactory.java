package com.mygoconsulting.mytracking.parse;

import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;

@Component
public class ParserFactory {

	public IDOC parseCompanyCodeXML(String file) {
		
		//parse XML file
		IParser parser = new CompanyParser();
		IDOC doc = (IDOC) parser.parse(file);
		return doc;
	}

	public IDOC parseCustomerXML(String file) {
		//parse XML file
		IParser parser = new CustomerXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		return doc;
	}

	
	public IDOC parseMaterialXML(String file) {
		//parse XML file
		IParser parser = new MaterialXMLParser();
		IMY_MAT_ONLINE material = (IMY_MAT_ONLINE) parser.parse(file);
		IDOC doc = new IDOC();
		doc.setIMY_MAT_ONLINE(material);
		return doc;
	}

	
	public IDOC parseDeliveryXML(String file) {
		//parse XML file
		IParser parser = new DeliveryXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		return doc;
	}
	
	public IDOC parseSalesXML(String file) {
		//parse XML file
		IParser parser = new SalesXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		return doc;
	}
	
	public IDOC parseInvoiceXML(String file) {
		//parse XML file
		IParser parser = new InvoiceXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		return doc;
	}
	
}