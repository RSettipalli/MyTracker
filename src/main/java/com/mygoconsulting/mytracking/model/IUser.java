package com.mygoconsulting.mytracking.model;

import com.mygoconsulting.mytracking.util.UserType;

public interface IUser {
	
	public String getFirstName();
	public void setFirstName(String firstName);
	
	public String getLastName();
	public void setLastName(String lastName);
	
	public String getEmail();
	public void setEmail(String email);
	
	public String getPassword();
	public void setPassword(String password);
	
	public String getID();
	public void setID(String ID);
	
	public UserType getUserType();
	
}
