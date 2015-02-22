package com.mygoconsulting.mytracking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
@Component
public class BaseDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
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
		
		System.out.println("Select Query is "+sqlQuery);
		Object obj = null;
		try{
			obj = jdbcTemplateObject.queryForObject(sqlQuery, params,rowMapper);
		}catch(EmptyResultDataAccessException ex){
			System.out.println("jdbcTemplateObject is null");
			obj = null;
		} catch (Exception ex){
			System.out.println("jdbcTemplateObject is null");
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
	
	
	@SuppressWarnings("unchecked")
	protected Object get(String sqlQuery, RowMapper rowMapper) {
		
		System.out.println("Select Query is "+sqlQuery);
		Object obj = null;
		try{
			obj = jdbcTemplateObject.queryForObject(sqlQuery, rowMapper);
		}catch(EmptyResultDataAccessException ex){
			System.out.println("jdbcTemplateObject is null");
			obj = null;
		} catch (Exception ex){
			System.out.println("jdbcTemplateObject is null");
			obj = null;
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object> getObjects(String sqlQuery,RowMapper rowMapper) {
		
		System.out.println("Select Query is "+sqlQuery);
		List<Object> obj = null;
		try{
			obj = jdbcTemplateObject.query(sqlQuery, rowMapper);
		}catch(EmptyResultDataAccessException ex){
			System.out.println("jdbcTemplateObject is null");
			obj = null;
		} catch (Exception ex){
			System.out.println("jdbcTemplateObject is null");
			obj = null;
		}
		return obj;
	}

}
