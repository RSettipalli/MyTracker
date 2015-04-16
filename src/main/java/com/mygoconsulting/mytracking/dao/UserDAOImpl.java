package com.mygoconsulting.mytracking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.mappers.UserMapper;
import com.mygoconsulting.mytracking.model.IUser;
import com.mygoconsulting.mytracking.model.User;
import com.mygoconsulting.mytracking.util.MyTrackingDAOProperty;
import com.mygoconsulting.mytracking.util.UserType;

@Service
public class UserDAOImpl implements MyTrackingDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();		
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public boolean createUser(IUser user) {
		logger.debug("BEGIN");
		//User user1 = getUserDetails(user.getEmail());
		String 
		createQuery = "insert into customer_info (email,fname,lname,password,companyId,customerId) values (?,?,?,?,?,?)";
		int recordsCreated;
		if (user != null) {
			if(user.getUserType().equals(UserType.COMPANY)){
				recordsCreated = jdbcTemplateObject.update(createQuery, user.getEmail(),user.getFirstName(),
					user.getLastName(),user.getPassword(),user.getID(), null);
			} else{
				createQuery = "insert into customer_info (email,fname,lname,password,companyId,customerId) values (?,?,?,?,?,?)";
				recordsCreated = jdbcTemplateObject.update(createQuery, user.getEmail(),user.getFirstName(),
					user.getLastName(),user.getPassword(),null,user.getID());
			}
			if(recordsCreated >= 1)
				return true;
			}
		logger.debug("END");
		return false;
	}
	
	public User validateUser(String email, String password) {
		logger.debug("BEGIN");
		logger.debug(email);
		String validUserQuery = "select * from customer_info where email = ? and password = ?";//MyTrackingDAOProperty.getProperty("getUser");
		logger.debug(validUserQuery);
		try{
			User user = jdbcTemplateObject.queryForObject(validUserQuery, new Object[]{email,password},new UserMapper());
			logger.debug("END");
			if(user != null){
				return user;
			}
		} catch(EmptyResultDataAccessException ex){
			logger.debug("ValidateUser error: "+ex.toString());
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
