package com.mygoconsulting.mytracking.parse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;

public class BaseParser {
	
	private static final MygoLogger LOG = LogFactory.getMygoLogger();

	private XMLInputFactory factory;

	public BaseParser() {
		factory = XMLInputFactory.newInstance();
	}

	
	public XMLStreamReader getReader(String fileName) {
		LOG.debug("BEGIN");
		XMLStreamReader reader = null;
		try {
			reader = factory.createXMLStreamReader(new FileInputStream(fileName));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("END");
		return reader;
	}

}
