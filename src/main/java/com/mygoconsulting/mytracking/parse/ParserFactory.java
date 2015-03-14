package com.mygoconsulting.mytracking.parse;

import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;
import com.mygoconsulting.mytracking.model.IMY_MAT_ONLINE;

@Component
public class ParserFactory {
	
	private static final MygoLogger LOG = LogFactory.getMygoLogger();

	public IDOC parseCompanyCodeXML(String file) {
		LOG.debug("BEGIN");
		IParser parser = new CompanyParser();
		IDOC doc = (IDOC) parser.parse(file);
		LOG.debug("END");
		return doc;
	}

	public IDOC parseCustomerXML(String file) {
		LOG.debug("BEGIN");
		IParser parser = new CustomerXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		LOG.debug("END");
		return doc;
	}

	
	public IDOC parseMaterialXML(String file) {
		LOG.debug("BEGIN");
		IParser parser = new MaterialXMLParser();
		IMY_MAT_ONLINE material = (IMY_MAT_ONLINE) parser.parse(file);
		IDOC doc = new IDOC();
		doc.setIMY_MAT_ONLINE(material);
		LOG.debug("END");
		return doc;
	}

	
	public IDOC parseDeliveryXML(String file) {
		LOG.debug("BEGIN");
		IParser parser = new DeliveryXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		LOG.debug("END");
		return doc;
	}
	
	public IDOC parseSalesXML(String file) {
		LOG.debug("BEGIN");
		IParser parser = new SalesXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		LOG.debug("END");
		return doc;
	}
	
	public IDOC parseInvoiceXML(String file) {
		LOG.debug("BEGIN");
		IParser parser = new InvoiceXMLParser();
		IDOC doc = (IDOC) parser.parse(file);
		LOG.debug("END");
		return doc;
	}
}