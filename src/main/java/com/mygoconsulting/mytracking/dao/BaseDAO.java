package com.mygoconsulting.mytracking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
@Component
public class BaseDAO {
	private final static MygoLogger logger = LogFactory.getMygoLogger();

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplateObject;

	protected boolean insertOrUpdate(String sqlQuery, Object[] params) {
		logger.debug("BEGIN");
		int recordsCreated = jdbcTemplateObject.update(sqlQuery, params);
		logger.debug("END");
		if (recordsCreated >= 1)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	protected Object get(String sqlQuery, Object[] params, RowMapper rowMapper) {
		logger.debug("BEGIN");
		logger.debug("Select Query is "+sqlQuery);
		Object obj = null;
		try{
			obj = jdbcTemplateObject.queryForObject(sqlQuery, params,rowMapper);
		}catch(EmptyResultDataAccessException ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		} catch (Exception ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		}
		logger.debug("END");
		return obj;
	}

	protected boolean isExists(String sqlQuery, Object[] params,
			RowMapper rowMapper) {
		logger.debug("BEGIN");
		Object obj = get(sqlQuery, params, rowMapper);
		logger.debug("END");
		if (obj == null)
			return false;
		else
			return true;
	}
	
	protected void batchUpdate(String sql, List<Object[]> batchArgs){
		logger.debug("BEGIN");
		jdbcTemplateObject.batchUpdate(sql, batchArgs);
		logger.debug("END");
	}
	
	
	@SuppressWarnings("unchecked")
	protected Object get(String sqlQuery, RowMapper rowMapper) {
		logger.debug("BEGIN");
		logger.debug("Select Query is "+sqlQuery);
		Object obj = null;
		try{
			obj = jdbcTemplateObject.queryForObject(sqlQuery, rowMapper);
		}catch(EmptyResultDataAccessException ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		} catch (Exception ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		}
		logger.debug("END");
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	protected Object get(String sqlQuery) {
		logger.debug("BEGIN");
		logger.debug("Select Query is "+sqlQuery);
		Object obj = null;
		try{
			obj = jdbcTemplateObject.queryForList(sqlQuery);
		}catch(EmptyResultDataAccessException ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		} catch (Exception ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		}
		logger.debug("END");
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object> getObjects(String sqlQuery,RowMapper rowMapper) {
		logger.debug("BEGIN");
		logger.debug("Select Query is "+sqlQuery);
		List<Object> obj = null;
		try{
			obj = jdbcTemplateObject.query(sqlQuery, rowMapper);
		}catch(EmptyResultDataAccessException ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		} catch (Exception ex){
			logger.error("jdbcTemplateObject is null",ex);
			obj = null;
		}
		logger.debug("END");
		return obj;
	}

}
