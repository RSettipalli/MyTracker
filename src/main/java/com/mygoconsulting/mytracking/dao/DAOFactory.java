package com.mygoconsulting.mytracking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.LogFactory;
import com.mygoconsulting.mytracking.batch.util.MygoLogger;
import com.mygoconsulting.mytracking.model.IDOC;

@Component
public class DAOFactory {
	
	private final static MygoLogger logger = LogFactory.getMygoLogger();
	
	@Autowired
	@Qualifier("CompanyCode")
	IDAO companyCodeDAO;
	
	@Autowired
	@Qualifier("Customer")
	IDAO customerDao;
	
	@Autowired
	@Qualifier("Material")
	IDAO materialDao;
	
	@Autowired
	@Qualifier("DeliveryDAO")
	IDAO deliveryDao;
	
	@Autowired
	@Qualifier("SalesDAO")
	IDAO salesDao;
	
	@Autowired
	@Qualifier("InvoiceDAO")
	IDAO invoiceDao;
	
	
	public void persistCompanyCodeData(IDOC doc){
		logger.debug("BEGIN");
		companyCodeDAO.persist(doc);
		logger.debug("END");
	}

	public void persistCustomerData(IDOC doc) {
		logger.debug("BEGIN");
		customerDao.persist(doc);
		logger.debug("END");
	}

	public void persistMaterialData(IDOC doc) {
		logger.debug("BEGIN");
		materialDao.persist(doc);
		logger.debug("END");
	}
	
	public void persistDeliveryData(IDOC doc) {
		logger.debug("BEGIN");
		deliveryDao.persist(doc);
		logger.debug("END");
	}
	
	public void persistSalesData(IDOC doc) {
		logger.debug("BEGIN");
		salesDao.persist(doc);
		logger.debug("END");
	}
	
	public void persistInvoiceData(IDOC doc) {
		logger.debug("BEGIN");
		invoiceDao.persist(doc);
		logger.debug("END");
	}
	
}
