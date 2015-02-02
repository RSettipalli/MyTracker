package com.mygoconsulting.mytracking.parse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class BaseParser {

	private XMLInputFactory factory;

	public BaseParser() {
		factory = XMLInputFactory.newInstance();
	}

	
	public XMLStreamReader getReader(String fileName) {
		XMLStreamReader reader = null;
		try {
			reader = factory.createXMLStreamReader(new FileInputStream(fileName));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}

}
