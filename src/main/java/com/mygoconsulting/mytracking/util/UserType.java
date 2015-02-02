package com.mygoconsulting.mytracking.util;

import java.util.ArrayList;
import java.util.List;

public enum UserType {
	CONSUMER("1"), SUPPLIER("2"), GUEST("3");
	
	private String key;
	
	UserType(String key) {
		this.key = key;
		
	}
	
	public String getKey() {
		return this.key;
	}
	
	public static UserType fromString(String key) {
		if (key != null) {
			for (UserType b : UserType.values()) {
				if (key.equalsIgnoreCase(b.key)) {
					return b;
				}
			}
		}
		return null;
	}
	
	public static UserType getType(String name) {
		if (name != null) {
			for (UserType b : UserType.values()) {
				if (name.equalsIgnoreCase(b.name())) {
					return b;
				}
			}
		}
		return null;
	}
	
	public static List<String> getAllUserTypes(){
		List<String> userTypeList = new ArrayList<String>();
		
		for (UserType b : UserType.values()) {
			userTypeList.add(b.name());
		}
		return userTypeList;
	}
	
/*	public static void main(String args[]){
		List<String> userTypeList = getAllUserTypes();
		for(String str: userTypeList){
			System.out.println(str);
		}
	}*/
}
