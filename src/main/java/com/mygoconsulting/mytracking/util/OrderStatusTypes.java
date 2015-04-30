package com.mygoconsulting.mytracking.util;

import java.util.ArrayList;
import java.util.List;

public enum OrderStatusTypes {
	OPEN("1"), COMPLETED("2"), CANCELLED("3");
	private String key;

	OrderStatusTypes(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}
	
	public static OrderStatusTypes fromString(String key){
		if(key!=null){
			for(OrderStatusTypes b : OrderStatusTypes.values()){
				if(key.equalsIgnoreCase(b.key)){
					return b;
				}
			}
		}
		return null;
	}
	
	public static OrderStatusTypes getType(String name){
		if(name!=null){
			for(OrderStatusTypes b : OrderStatusTypes.values()){
				if(name.equalsIgnoreCase(b.name())) {
					return b;
				}
			}
		}
		return null;
	}
	
	public static List<String> getAllUserTypes(){
		List<String> orderStatusTypesList = new ArrayList<String>();
		
		for (OrderStatusTypes b : OrderStatusTypes.values()) {
			orderStatusTypesList.add(b.name());
		}
		return orderStatusTypesList;
	}
}
