package com.mygoconsulting.mytracking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyTrackerProperty {
	
private static String fileName = ApplicationConstants.PROPS_FILE; //"mytracker.properties";
	
	private static Properties prop; 
	
	private static void loadProperties(){
		try {
			prop = new Properties();
			InputStream inputStream = MyTrackerProperty.class.getClassLoader().getResourceAsStream(fileName);
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String property) {
		if(prop == null){
			loadProperties();
		}
		return prop.getProperty(property);
	}
	
}
