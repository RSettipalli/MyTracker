package com.mygoconsulting.mytracking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
@Component
public class BaseDAO {

	@Autowired
	//@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplateObject;

	protected boolean insertOrUpdate(String sqlQuery, Object[] params) {
		int recordsCreated = jdbcTemplateObject.update(sqlQuery, params);
		if (recordsCreated >= 1)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	protected Object get(String sqlQuery, Object[] params, RowMapper rowMapper) {
		Object obj = null;
		try{
		obj = jdbcTemplateObject.queryForObject(sqlQuery, params,
				rowMapper);
		}catch(EmptyResultDataAccessException ex){
			obj = null;
		}
		return obj;

	}

	protected boolean isExists(String sqlQuery, Object[] params,
			RowMapper rowMapper) {
		Object obj = get(sqlQuery, params, rowMapper);
		if (obj == null)
			return false;
		else
			return true;
	}
	
	protected void batchUpdate(String sql, List<Object[]> batchArgs){
		jdbcTemplateObject.batchUpdate(sql, batchArgs);
	}

}
