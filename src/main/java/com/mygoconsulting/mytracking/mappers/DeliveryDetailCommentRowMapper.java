package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;

@Component("DeliveryDetailCommentRowMapper")
public class DeliveryDetailCommentRowMapper implements RowMapper<IMY_MGOL_SO_DETAIL_COMMENT> {

	@Override
	public IMY_MGOL_SO_DETAIL_COMMENT mapRow(ResultSet rs, int rowNum) throws SQLException {
		IMY_MGOL_SO_DETAIL_COMMENT detailComment = new IMY_MGOL_SO_DETAIL_COMMENT();
		detailComment.setORDER_NBR(rs.getString("ORDER_NBR"));
		detailComment.setLINE(rs.getString("LINE"));
		detailComment.setTYPE(rs.getString("TYPE"));		
		return detailComment;
	}
}
