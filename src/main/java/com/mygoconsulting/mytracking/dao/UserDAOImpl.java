package com.mygoconsulting.mytracking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mygoconsulting.mytracking.model.User;
import com.mygoconsulting.mytracking.util.MyTrackingDAOProperty;
import com.mygoconsulting.mytracking.util.UserMapper;

@Service
public class UserDAOImpl implements MyTrackingDAO {
		
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public boolean createUser(User user) {
		String createQuery = MyTrackingDAOProperty.getProperty("createQuery");
		int recordsCreated = jdbcTemplateObject.update(createQuery, user.getEmail(),user.getFname(),
				user.getLname(),user.getPassword(),user.getUserType().getKey());
		if(recordsCreated >= 1)
			return true;
		
		return false;
	}
	
	public User validateUser(String email, String password) {
		System.out.println(email);
		System.out.println(password);
		String validUserQuery = MyTrackingDAOProperty.getProperty("getUser");
		System.out.println(validUserQuery);
		User user = jdbcTemplateObject.queryForObject(validUserQuery, new Object[]{email,password},new UserMapper());
		if(user != null){
			return user;
		}
		return null;
	}

	public User getUserDetails(String email) {
		String validUserEmailQuery = MyTrackingDAOProperty.getProperty("getUserDetailsByEmail");
		System.out.println(validUserEmailQuery);
		List<User> usersList = jdbcTemplateObject.query(validUserEmailQuery, new Object[]{email},new UserMapper());
		if(usersList.size() > 0){
			return usersList.get(0);
		}
		return null;
	}	
}
