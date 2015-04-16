package com.mygoconsulting.mytracking.model;

import com.mygoconsulting.mytracking.util.UserType;

public class CustomerUser implements IUser {
	private String fname;
	private String lname;
	private String email;
	private String password;
	private UserType userType = UserType.CUSTOMER;
	private String customerId;
	
	public CustomerUser(){
		
	}
	
	public CustomerUser(User user){
		fname = user.getFname();
		lname = user.getLname();
		email = user.getEmail();
		password = user.getPassword();
		customerId = user.getCustomerId();
	}
	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return fname;
	}
	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		fname = firstName;
	}
	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lname;
	}
	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		lname = lastName;
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return customerId;
	}
	@Override
	public void setID(String ID) {
		// TODO Auto-generated method stub
		customerId = ID;
	}
	public String getUserTypeAsString() {
		return userType.toString();
	}
	@Override
	public UserType getUserType() {
		// TODO Auto-generated method stub
		return userType;
	}
}
