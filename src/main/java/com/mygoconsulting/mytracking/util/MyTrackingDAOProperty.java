package com.mygoconsulting.mytracking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyTrackingDAOProperty  {
	
	private static String fileName = ApplicationConstants.DB_PROPS_FILE;//"sqlqueries.properties";
	
	private static Properties prop; 
	
	private static void loadProperties(){
		try {
			prop = new Properties();
			InputStream inputStream = MyTrackingDAOProperty.class.getClassLoader().getResourceAsStream(fileName);
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
