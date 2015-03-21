package com.mygoconsulting.mytracking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.mappers.UserMapper;
import com.mygoconsulting.mytracking.model.User;
import com.mygoconsulting.mytracking.util.MyTrackingDAOProperty;

@Service
public class UserDAOImpl implements MyTrackingDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();		
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public boolean createUser(User user) {
		logger.debug("BEGIN");
		String companyId = null;
		String createQuery = "insert into customer_info (email,fname,lname,password,companyId) values (?,?,?,?,?)";
		// MyTrackingDAOProperty.getProperty("createQuery");
		if(user.getCompanyId() != null && user.getCompanyId().trim().length() != 0 && !user.getCompanyId().equals("-1")){
			companyId = user.getCompanyId();
		} else {
			companyId = user.getCustomerId();
		}
		int recordsCreated = jdbcTemplateObject.update(createQuery, user.getEmail(),user.getFname(),
				user.getLname(),user.getPassword(),companyId); //user.getUserType().getKey()
		logger.debug("END");
		if(recordsCreated >= 1)
			return true;
		
		return false;
	}
	
	public User validateUser(String email, String password) {
		logger.debug("BEGIN");
		logger.debug(email);
		String validUserQuery = "select * from customer_info where email = ? and password = ?";//MyTrackingDAOProperty.getProperty("getUser");
		logger.debug(validUserQuery);
		User user = jdbcTemplateObject.queryForObject(validUserQuery, new Object[]{email,password},new UserMapper());
		logger.debug("END");
		if(user != null){
			return user;
		}
		return null;
	}

	public User getUserDetails(String email) {
		logger.debug("BEGIN");
		String validUserEmailQuery = "select * from customer_info where email = ?";//MyTrackingDAOProperty.getProperty("getUserDetailsByEmail");
		logger.debug(validUserEmailQuery);
		List<User> usersList = jdbcTemplateObject.query(validUserEmailQuery, new Object[]{email},new UserMapper());
		logger.debug("END");
		if(usersList.size() > 0){
			return usersList.get(0);
		}
		return null;
	}	
}
