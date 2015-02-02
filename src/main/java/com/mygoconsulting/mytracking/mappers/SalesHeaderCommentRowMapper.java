package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER_COMMENT;

@Component("SalesHeaderCommentRowMapper")
public class SalesHeaderCommentRowMapper implements RowMapper<IMY_MGOL_SO_HEADER_COMMENT> {

	@Override
	public IMY_MGOL_SO_HEADER_COMMENT mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_SO_HEADER_COMMENT headerComment = new IMY_MGOL_SO_HEADER_COMMENT();
		headerComment.setORDER_NBR(rs.getString("ORDER_NBR"));
		headerComment.setLINE(rs.getString("LINE"));
		headerComment.setTYPE(rs.getString("TYPE"));
		return headerComment;
	}

}
