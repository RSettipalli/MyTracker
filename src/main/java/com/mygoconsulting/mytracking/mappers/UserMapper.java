package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mygoconsulting.mytracking.model.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	      User user = new User();
	      user.setEmail(rs.getString("email"));
	      user.setFname(rs.getString("fname"));
	      user.setLname(rs.getString("lname"));
	      user.setCompanyId(rs.getString("companyId"));
	      user.setCustomerId(rs.getString("customerId"));
	      return user;
	   }	
}
