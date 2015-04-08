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
		List<IMY_MGOL_SO_DETAIL> imyMGolSoDetailList = salesDao.getSalesOrderDetails(soNum);
		for(IMY_MGOL_SO_DETAIL imyMGolSoDetail:imyMGolSoDetailList){
			IMY_MGOL_SO_ITEM_ATTACHM soDetailItemAttachments = getSODetailAttachement(imyMGolSoDetail.getORDER_NBR());
			imyMGolSoDetail.setIMY_MGOL_SO_ITEM_ATTACHM(soDetailItemAttachments);
			List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolSoDetailComments = 
					getSODetailComment(imyMGolSoDetail.getORDER_NBR(),imyMGolSoDetail.getORDER_LINE_NBR());
			imyMGolSoDetail.setIMY_MGOL_SO_DETAIL_COMMENT(imyMGolSoDetailComments);
		}
		logger.debug("END");
		return imyMGolSoDetailList;
	}
	
	public List<IMY_MGOL_SO_HEADER> getSalesOrderHeader(String customerId) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_HEADER> imyMGolSoHeaderList = salesDao.getSalesOrderHeader(customerId);
		if(imyMGolSoHeaderList.size() > 0){
			for(IMY_MGOL_SO_HEADER imyMGolSoHeader:imyMGolSoHeaderList){
				List<IMY_MGOL_SO_DETAIL> imyMGolSoDetailList = getSalesOrderDetail(imyMGolSoHeader.getORDER_NBR());
				List<IMY_MGOL_SO_HEADER_COMMENT> soHeaderComments = getSOHeaderCommentDetails(imyMGolSoHeader.getORDER_NBR());
				imyMGolSoHeader.setIMY_MGOL_SO_DETAIL(imyMGolSoDetailList);
				imyMGolSoHeader.setIMY_MGOL_SO_HEADER_COMMENT(soHeaderComments);
			}
		}		
		logger.debug("END");
		return imyMGolSoHeaderList;
	}
	
	public IMY_MGOL_SO_ITEM_ATTACHM getSODetailAttachement(String soNum) {
		logger.debug("BEGIN");
		IMY_MGOL_SO_ITEM_ATTACHM soDetailItemAttachments = new  IMY_MGOL_SO_ITEM_ATTACHM();
		soDetailItemAttachments = salesDao.getSODetailAttachement(soNum);
		logger.debug("END");
		return soDetailItemAttachments;
	}
	
	public List<IMY_MGOL_SO_HEADER_COMMENT> getSOHeaderCommentDetails(String soNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_HEADER_COMMENT> soHeaderComments = salesDao.getSOHeaderCommentDetails(soNum);
		logger.debug("END");
		return soHeaderComments;
	}
	
	public List<IMY_MGOL_SO_DETAIL_COMMENT> getSODetailComment(String soNum,String soLineNum) {
		logger.debug("BEGIN");
		List<IMY_MGOL_SO_DETAIL_COMMENT> imyMGolSoDetailComments = salesDao.getSODetailComment(soNum,soLineNum);
		logger.debug("END");
		return imyMGolSoDetailComments;
	}
	
	
}
