package com.mygoconsulting.mytracking.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyTrackerProperty {
	
	private static Properties prop; 
	
	private static void loadProperties(){
		try {
			prop = new Properties();
			//InputStream inputStream = MyTrackerProperty.class.getClassLoader().getResourceAsStream(fileName);
			InputStream inputStream = new FileInputStream(ApplicationConstants.PROPS_FILE);
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
