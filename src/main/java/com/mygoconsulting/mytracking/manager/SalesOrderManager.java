package com.mygoconsulting.mytracking.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.dao.SalesDAO;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_DETAIL_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_HEADER_COMMENT;
import com.mygoconsulting.mytracking.model.IMY_MGOL_SO_ITEM_ATTACHM;

@Component("SalesOrderManager")
public class SalesOrderManager {
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	@Qualifier("SalesDAO")
	SalesDAO salesDao;
		
	public List<IMY_MGOL_SO_DETAIL> getSalesOrderDetail(String soNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_DETAIL> imyMGolSoDetail = salesDao.getSalesOrderDetails(soNum);
		logger.debug("END");
		return imyMGolSoDetail;
	}
	
	public IMY_MGOL_SO_HEADER getSalesOrderHeader(String soNum) {
		logger.debug("BEGIN");
		IMY_MGOL_SO_HEADER imyMGolSoHeader = salesDao.getSalesOrderHeader(soNum);
		logger.debug("END");
		return imyMGolSoHeader;
	}
	
	public List<IMY_MGOL_SO_ITEM_ATTACHM> getSODetailAttachement(String soNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_ITEM_ATTACHM> soDetailItemAttachments = salesDao.getSODetailAttachement(soNum);
		logger.debug("END");
		return soDetailItemAttachments;
	}
	
	public List<IMY_MGOL_SO_HEADER_COMMENT> getSOHeaderCommentDetails(String soNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_HEADER_COMMENT> soHeaderComments = salesDao.getSOHeaderCommentDetails(soNum);
		logger.debug("END");
		return soHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getSODetailComment(String soNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolSoDetailComments = salesDao.getSODetailComment(soNum);
		logger.debug("END");
		return imyMGolSoDetailComments;
	}
	
	
}
