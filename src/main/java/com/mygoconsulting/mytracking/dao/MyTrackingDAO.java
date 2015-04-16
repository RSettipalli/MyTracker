package com.mygoconsulting.mytracking.dao;

import org.springframework.stereotype.Service;

import com.mygoconsulting.mytracking.model.IUser;
import com.mygoconsulting.mytracking.model.User;

@Service
public interface MyTrackingDAO {
	
	public boolean createUser(IUser user);
	
	public User validateUser(String email, String password);

	public User getUserDetails(String email);
	
}
