package com.mygoconsulting.mytracking.util;

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
	      user.setUserType(UserType.fromString(rs.getString("type")));
	      return user;
	   }	
}
