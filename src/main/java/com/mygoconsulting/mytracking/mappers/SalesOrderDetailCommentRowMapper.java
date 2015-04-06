package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

@Component("SalesOrderDetailCommentRowMapper")
public class SalesOrderDetailCommentRowMapper implements RowMapper<IMY_MGOL_SO_DETAIL_COMMENT> {

	@Override
	public IMY_MGOL_SO_DETAIL_COMMENT mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_SO_DETAIL_COMMENT detailComment = new IMY_MGOL_SO_DETAIL_COMMENT();
		detailComment.setORDER_NBR(rs.getString("ORDER_NBR"));
		detailComment.setLINE(rs.getString("LINE"));
		detailComment.setTYPE(rs.getString("TYPE"));
		detailComment.setORDER_LINE_NBR(rs.getString("ORDER_LINE_NBR"));
		detailComment.setORDER_LINE_NBR_SO_DETAIL(rs.getString("ORDER_LINE_NBR_SO_DETAIL"));
		detailComment.setORDER_NBR_SO_DETAIL(rs.getString("ORDER_NBR_SO_DETAIL"));
		return detailComment;
	}
}
