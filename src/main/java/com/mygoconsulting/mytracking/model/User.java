package com.mygoconsulting.mytracking.model;

import java.util.List;

import com.mygoconsulting.mytracking.util.UserType;

public class User {
	
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String userId;
	private UserType userType;
	private String companyId;	
	private List<String> BUKRSList;
	private List<String> KUNNRList;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public List<String> getBUKRSList() {
		return BUKRSList;
	}
	public void setBUKRSList(List<String> bUKRSList) {
		BUKRSList = bUKRSList;
	}
	public List<String> getKUNNRList() {
		return KUNNRList;
	}
	public void setKUNNRList(List<String> kUNNRList) {
		KUNNRList = kUNNRList;
	}
}
